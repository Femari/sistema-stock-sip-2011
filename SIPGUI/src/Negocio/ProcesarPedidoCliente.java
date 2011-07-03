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
import Persistencia.PrioridadMapper;
import Persistencia.ProductoMapper;
import java.util.List;

public class ProcesarPedidoCliente {
    private PedidoCliente pedidoCliente = new PedidoCliente();
	
	
    public void SetearPrioridadPedido(int prioridad){
        pedidoCliente.setPrioridad(prioridad);
    }
	
    /**
     * 4to paso UC:Procesar Pedido Cliente
     */
    public Cliente IdentificarCliente(String cuit){
        ClienteMapper clienteMapper = ClienteMapper.getInstancia();

        if (clienteMapper.existeCliente(cuit)){
            pedidoCliente.setCliente(clienteMapper.Cargar(cuit));
            return pedidoCliente.getCliente();
        }else{
            return null;
        }
    }

    /**
     * 6to paso UC:Procesar Pedido Cliente
     */
    public void AgregarDetallePedidoCliente(Producto producto, int cantidad){
        DetallePedidoCliente detallePedidoCliente = new DetallePedidoCliente();
        detallePedidoCliente.setProducto(producto);
        detallePedidoCliente.setCantidad(cantidad);
        pedidoCliente.agregarDetallePedidoCliente(detallePedidoCliente);
    }

    /**
     * 7mo Paso: Verificar la disponibilidad de stock actual y futuro para cada producto del pedidoActual
     * @return Lista de DetalleDisponibilidadProducto con los productos con stock actual insuficiente
     */
    public ArrayList<DetalleDisponibilidadProducto> VerificarDisponibilidadProductos(){
        ProductoMapper mapper = ProductoMapper.getInstancia(); 
        ArrayList<DetalleDisponibilidadProducto> arrayDetalleDisponibilidad = new ArrayList<DetalleDisponibilidadProducto>();

        for (DetallePedidoCliente sDetallePedidoCliente: pedidoCliente.getArrayDetallePedidoCliente()){
            Producto producto = sDetallePedidoCliente.getProducto();
            int stockLibre = mapper.ObtenerStockLibre(producto);

            //si no alcanza
            if(stockLibre < sDetallePedidoCliente.getCantidad())
            {
                //La cantidad solicitada a futuro es la diferencia entre el stockLibre y la cantidad solicitada del pedido
                int cantidadSolicitadaFutura = sDetallePedidoCliente.getCantidad() - stockLibre;
                DetalleDisponibilidadProducto detalleDisponibilidadProd = mapper.ObtenerDetallesDisponibilidadFutura(producto, cantidadSolicitadaFutura);

                arrayDetalleDisponibilidad.add(detalleDisponibilidadProd);

                //maper pedidoProvedor, verificar pedidos pendientes, si estan ya comprometidos , y ver que me sobra de ese pedido.
                // regla: siempre hay que usar primero los del stock real, y luego los de los pedidos por orden de fecha de llegada al deposito.
                // ver si hay que hacer un nuevo pedido
                // calcular fecha
                // comprometer stock
                //detalleDisponibilidadProd.
            }
        }

        return arrayDetalleDisponibilidad;
    }

    /**
     * 8vo paso: Compromete stock futuro de un pedido a proveedor
     * @param Producto del pedido
     * @param Pedido de proveedor a comprometer
     * @param Cantidad a comprometer del pedido a proveedor
     */
    public void ComprometerStockPedidoProveedor(Producto producto, PedidoProveedor pedido, int cantidad){
        DetallePedidoCliente detalle = BuscarDetallePorProducto(producto);
        if(detalle != null)
        {   //TODO: Agregar validacion de que todavia tenga stock para comprometer
            detalle.ComprometerStock(pedido, cantidad);
        }
        else
        {   //TODO: Analizar si es necesario implementar un manejo de errores mas robusto
            System.err.println("El producto {1} no se encuentra registrado en el pedido actual.".replace("{1}", producto.getNombre()));
        }
    }

    /**
     * 8vo paso: Compromete stock disponible en deposito
     * @param Producto del pedido
     * @param Cantidad a comprometer del deposito
     */
    public void ComprometerStockDeposito(Producto producto, int cantidad){
        DetallePedidoCliente detalle = BuscarDetallePorProducto(producto);
        if(detalle != null)
        {   //TODO: Agregar validacion de que todavia tenga stock para comprometer
            detalle.ComprometerStock(cantidad);
        }
        else
        {   //TODO: Analizar si es necesario implementar un manejo de errores mas robusto
            System.err.println("El producto {1} no se encuentra registrado en el pedido actual.".replace("{1}", producto.getNombre()));
        }
    }

    /**
     * 9no paso: Valida el pedido y lo persiste a la base de datos
     * @return Detalle de errores en caso de que hayan surgido o string vacio si esta todo ok
     */
    public String GrabarPedido()
    {
        String error = "";
        for(DetallePedidoCliente detalle : pedidoCliente.getArrayDetallePedidoCliente())
        {
            if(!detalle.ValidarStockComprometido())
            {	//Valido que cada detalle haya comprometido todo el stock solicitado
                error += "Falta comprometer stock para el producto {1}.\n".replace("{1}", detalle.getProducto().getNombre());
            }
        }

        if(error == "")
        {
            CargarParametrosAutomaticosPedido();

            PedidoClienteMapper mapper = PedidoClienteMapper.getInstancia();
            mapper.Grabar(pedidoCliente);
        }

        return error;
    }

    protected void CargarParametrosAutomaticosPedido() {
        pedidoCliente.setEstado(0); //pendiente
        pedidoCliente.setFechaPedido(new Date());
        pedidoCliente.setFechaEntrega(pedidoCliente.calcularFechaEstimadaEntrega());	//TODO: Fecha de entrega del pedido tiene que poder ser definida por el cliente
    }

    /**
     * Busca un detalle de pedido por producto en el pedido actual
     * @param Producto por el cual buscar el detalle
     * @return Detalle para el producto indicado o null si no existe detalle alguno
     */
    private DetallePedidoCliente BuscarDetallePorProducto(Producto producto){
        for(DetallePedidoCliente detalle : pedidoCliente.getArrayDetallePedidoCliente())
        {
            if(detalle.getProducto().equals(producto))
                return detalle;
        }

        return null;
    }
        
    public ArrayList<Cliente> getClientes() {
        return ClienteMapper.getInstancia().CargarTodos();
    }

    public ArrayList<Prioridad> getPrioridades() {
        return PrioridadMapper.getInstancia().CargarTodos();
    }

}
