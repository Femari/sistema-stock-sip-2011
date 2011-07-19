package Negocio;

import java.util.ArrayList;
import java.util.Date;

import Negocio.Modelo.Cliente;
import Negocio.Modelo.DetalleDisponibilidadProducto;
import Negocio.Modelo.DetallePedidoCliente;
import Negocio.Modelo.PedidoCliente;
import Negocio.Modelo.PedidoProveedor;
import Negocio.Modelo.Prioridad;
import Negocio.Modelo.Producto;
import Persistencia.ClienteMapper;
import Persistencia.PedidoClienteMapper;
import Persistencia.PedidoProveedorMapper;
import Persistencia.PrioridadMapper;
import Persistencia.ProductoMapper;
import java.util.HashMap;

public class ProcesarPedidoCliente {

    private PedidoCliente pedidoCliente = new PedidoCliente();

    public PedidoCliente getPedidoCliente() {
        return pedidoCliente;
    }

    public void SetearPrioridadPedido(int prioridad) {
        PrioridadMapper prioridadMapper = PrioridadMapper.getInstancia();
        pedidoCliente.setPrioridad(prioridadMapper.Cargar(prioridad));
    }

    /**
     * 4to paso UC:Procesar Pedido Cliente
     */
    public Cliente IdentificarCliente(Long cuit) {
        ClienteMapper clienteMapper = ClienteMapper.getInstancia();

        if (clienteMapper.existeCliente(cuit)) {
            pedidoCliente.setCliente(clienteMapper.Cargar(cuit));
            return pedidoCliente.getCliente();
        } else {
            return null;
        }
    }

    /**
     * 6to paso UC:Procesar Pedido Cliente
     */
    public void AgregarDetallePedidoCliente(Producto producto, int cantidad) {
        DetallePedidoCliente detallePedidoCliente = new DetallePedidoCliente();
        detallePedidoCliente.setProducto(producto);
        detallePedidoCliente.setCantidad(cantidad);
        pedidoCliente.agregarDetallePedidoCliente(detallePedidoCliente);
    }

    /**
     * 7mo Paso: Verificar la disponibilidad de stock actual y futuro para cada producto del pedidoActual
     * @return Lista de DetalleDisponibilidadProducto
     */
    public ArrayList<DetalleDisponibilidadProducto> VerificarDisponibilidadProductos() {
        ProductoMapper mapper = ProductoMapper.getInstancia();
        ArrayList<DetalleDisponibilidadProducto> arrayDetalleDisponibilidad = new ArrayList<DetalleDisponibilidadProducto>();

        for (DetallePedidoCliente sDetallePedidoCliente : pedidoCliente.getArrayDetallePedido()) {
            Producto producto = sDetallePedidoCliente.getProducto();
            DetalleDisponibilidadProducto detalleDisponibilidadProd;
            int stockLibre = ObtenerStockLibreDeposito(producto);

            //si no alcanza con el stock q hay en deposito:
            if (stockLibre < sDetallePedidoCliente.getCantidad()) {
                //La cantidad solicitada a futuro es la diferencia entre el stockLibre de depósito y la cantidad solicitada del pedido
                int cantidadSolicitadaFutura = sDetallePedidoCliente.getCantidad() - stockLibre;
                detalleDisponibilidadProd = mapper.ObtenerDetallesDisponibilidadFutura(producto, cantidadSolicitadaFutura);

                // regla: siempre hay que usar primero los del stock real, y luego los de los pedidos por orden de fecha de llegada al deposito.

            } else // si es cubierto con el stock de deposito
            {
                detalleDisponibilidadProd = new DetalleDisponibilidadProducto();
                detalleDisponibilidadProd.setProducto(producto);
                detalleDisponibilidadProd.setCantidadDisponible(stockLibre);
                detalleDisponibilidadProd.setFechaDisponibilidad(new java.util.Date()); //setea la fecha actual
                detalleDisponibilidadProd.setCantidadSolicitada(sDetallePedidoCliente.getCantidad());
                detalleDisponibilidadProd.setCantidadFaltante(0);

            }
            arrayDetalleDisponibilidad.add(detalleDisponibilidadProd);
        }

        return arrayDetalleDisponibilidad;
    }

    public Date calcularFechaMinimaDisponibilidad() {
        ArrayList<DetalleDisponibilidadProducto> arrayDetalleDisponibilidad = VerificarDisponibilidadProductos();
        Date date = new Date();
        for (DetalleDisponibilidadProducto detalle : arrayDetalleDisponibilidad) {
            Date dateDetalle;
            if (detalle.getCantidadFaltante() == 0) {
                dateDetalle = detalle.getFechaDisponibilidad();
            } else {
                dateDetalle = detalle.getFechaDisponibilidadNuevoPedido();
            }
            date = (dateDetalle.compareTo(date) > 0 ? dateDetalle : date);
        }
        return date;
    }

    public int ObtenerStockLibreDeposito(Producto xProducto) {
        ProductoMapper mapper = ProductoMapper.getInstancia();
        return mapper.ObtenerStockLibreDeposito(xProducto);
    }

    public HashMap<Integer, ProductoMapper.StockLibrePorPedido> ObtenerStockLibrePorPedido(Producto xProducto) {
        ProductoMapper mapper = ProductoMapper.getInstancia();
        return mapper.ObtenerStockLibrePorPedido(xProducto);
    }

    public void ComprometerStock() {
        int stockLibreDeposito = 0;
        HashMap<Integer, ProductoMapper.StockLibrePorPedido> stockLibrePedidos = new HashMap<Integer, ProductoMapper.StockLibrePorPedido>();
        int cantidadRestante = 0;
        boolean comprometidoTotalmente = false;
        for (DetallePedidoCliente sDetallePedidoCliente : pedidoCliente.getArrayDetallePedido()) {
            comprometidoTotalmente = false;
            cantidadRestante = sDetallePedidoCliente.getCantidad();

            //COMPROMETE DEPOSITO
            stockLibreDeposito = ObtenerStockLibreDeposito(sDetallePedidoCliente.getProducto());
            if (stockLibreDeposito > 0) {
                if (stockLibreDeposito >= cantidadRestante) {
                    ComprometerStockDeposito(sDetallePedidoCliente.getProducto(), cantidadRestante);
                    comprometidoTotalmente = true;
                } else {
                    ComprometerStockDeposito(sDetallePedidoCliente.getProducto(), stockLibreDeposito);
                    cantidadRestante -= stockLibreDeposito;
                }
            }

            //COMPROMETE PEDIDOS PENDIENTE DE ENTREGA
            if (!comprometidoTotalmente) {
                stockLibrePedidos = ObtenerStockLibrePorPedido(sDetallePedidoCliente.getProducto());
                for (int i = 0; i < stockLibrePedidos.size(); i++) {
                    if (stockLibrePedidos.get(i).getStockLibre() > 0) {
                        if (stockLibrePedidos.get(i).getStockLibre() >= cantidadRestante) {
                            ComprometerStockPedidoProveedor(sDetallePedidoCliente.getProducto(), PedidoProveedorMapper.getInstancia().Cargar(stockLibrePedidos.get(i).getNroPedido()), cantidadRestante);
                            comprometidoTotalmente = true;
                            break;
                        } else {
                            ComprometerStockPedidoProveedor(sDetallePedidoCliente.getProducto(), PedidoProveedorMapper.getInstancia().Cargar(stockLibrePedidos.get(i).getNroPedido()), stockLibrePedidos.get(i).getStockLibre());
                            cantidadRestante -= stockLibrePedidos.get(i).getStockLibre();
                        }
                    }
                }
            }

            //COMPROMETE PEDIDOS A FUTURO (NO REALIZADOS AUN)
            if (!comprometidoTotalmente) {
                ComprometerStockPedidoProveedorFuturo(sDetallePedidoCliente.getProducto(), cantidadRestante);
            }

        }

    }

    /**
     * 8vo paso: Compromete stock de un pedido a proveedor pendiente de entrega
     * @param Producto del pedido
     * @param Pedido de proveedor a comprometer
     * @param Cantidad a comprometer del pedido a proveedor
     */
    public void ComprometerStockPedidoProveedor(Producto producto, PedidoProveedor pedido, int cantidad) {
        DetallePedidoCliente detalle = BuscarDetallePorProducto(producto);
        if (detalle != null) {
            detalle.ComprometerStock(pedido, cantidad);
        } else {
            System.err.println("El producto {1} no se encuentra registrado en el pedido actual.".replace("{1}", producto.getNombre()));
        }
    }

    /**
     * 8vo paso: Compromete stock disponible en deposito
     * @param Producto del pedido
     * @param Cantidad a comprometer del deposito
     */
    public void ComprometerStockDeposito(Producto producto, int cantidad) {
        DetallePedidoCliente detalle = BuscarDetallePorProducto(producto);
        if (detalle != null) {
            detalle.ComprometerStock(cantidad);
        } else {
            System.err.println("El producto {1} no se encuentra registrado en el pedido actual.".replace("{1}", producto.getNombre()));
        }
    }

    /**
     * 8vo paso: Compromete stock disponible en deposito
     * @param Producto del pedido
     * @param Cantidad a comprometer
     */
    public void ComprometerStockPedidoProveedorFuturo(Producto producto, int cantidad) {
        DetallePedidoCliente detalle = BuscarDetallePorProducto(producto);
        if (detalle != null) {
            detalle.ComprometerStockFuturo(cantidad);
        } else {
            System.err.println("El producto {1} no se encuentra registrado en el pedido actual.".replace("{1}", producto.getNombre()));
        }
    }

    /**
     * 9no paso: Valida el pedido y lo persiste a la base de datos
     * @return Detalle de errores en caso de que hayan surgido o string vacio si esta todo ok
     */
    public String GrabarPedido() {
        String error = "";
        for (DetallePedidoCliente detalle : pedidoCliente.getArrayDetallePedido()) {
            if (!detalle.ValidarStockComprometido()) {	//Valido que cada detalle haya comprometido todo el stock solicitado
                error += "Falta comprometer stock para el producto {1}.\n".replace("{1}", detalle.getProducto().getNombre());
            }
        }

        if (error.equals("")) {
            CargarParametrosAutomaticosPedido();

            PedidoClienteMapper mapper = PedidoClienteMapper.getInstancia();
            mapper.Grabar(pedidoCliente);
        }

        return error;
    }

    protected void CargarParametrosAutomaticosPedido() {
        pedidoCliente.setEstado(0); //pendiente
        pedidoCliente.setFechaPedido(new Date());
        //pedidoCliente.setFechaEntrega(pedidoCliente.calcularFechaEstimadaEntrega());	//TODO: Fecha de entrega del pedido tiene que poder ser definida por el cliente
        pedidoCliente.setFechaEntrega(calcularFechaMinimaDisponibilidad());

    }

    /**
     * Busca un detalle de pedido por producto en el pedido actual
     * @param Producto por el cual buscar el detalle
     * @return Detalle para el producto indicado o null si no existe detalle alguno
     */
    private DetallePedidoCliente BuscarDetallePorProducto(Producto producto) {
        for (DetallePedidoCliente detalle : pedidoCliente.getArrayDetallePedido()) {
            if (detalle.getProducto().equals(producto)) {
                return detalle;
            }
        }

        return null;
    }

    public ArrayList<Cliente> getClientes() {
        return ClienteMapper.getInstancia().CargarTodos();
    }

    public ArrayList<Prioridad> getPrioridades() {
        return PrioridadMapper.getInstancia().CargarTodos();
    }

    public ArrayList<Producto> getProductos() {
        return ProductoMapper.getInstancia().CargarTodos();
    }
}
