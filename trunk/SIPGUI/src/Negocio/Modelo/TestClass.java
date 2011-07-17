package Negocio.Modelo;

import java.util.Date;

import Negocio.ProcesarPedidoCliente;
import Persistencia.ClienteMapper;
import Persistencia.PedidoClienteMapper;
import Persistencia.PedidoProveedorMapper;
import Persistencia.ProductoMapper;
import Persistencia.ProveedorMapper;

public class TestClass {
	
    public static void main(String args[]){
        PruebaProcesarPedidoCliente();
        //GrabarPedidoCliente();
        //CargarPedidoProveedor();
        //GrabarPedidoProveedor();
    }

    private static void PruebaProcesarPedidoCliente(){
        ProductoMapper productoMapper = ProductoMapper.getInstancia();

        ProcesarPedidoCliente procesar = new ProcesarPedidoCliente();
        procesar.SetearPrioridadPedido(1);
        procesar.IdentificarCliente((long)1);

        Producto producto1 = productoMapper.Cargar("PROD1");
        procesar.AgregarDetallePedidoCliente(producto1, 230);

        Producto producto2 = productoMapper.Cargar("PROD2");
        procesar.AgregarDetallePedidoCliente(producto2, 150);

        for(DetalleDisponibilidadProducto detalle : procesar.VerificarDisponibilidadProductos())
        {//Imprimo el detalle de los productos que estan faltando, y la fecha para la que existe stock a futuro 
            System.out.println(detalle.getProducto().getNombre() +" - " + detalle.getDescripcionEstado());
        }

        PedidoProveedor pedido = PedidoProveedorMapper.getInstancia().Cargar(7);
        procesar.ComprometerStockPedidoProveedor(producto1, pedido, 30);

        procesar.ComprometerStockDeposito(producto1, 200);
        procesar.ComprometerStockDeposito(producto2, 150);

        System.out.println(procesar.GrabarPedido());
    }

    private static void GrabarPedidoCliente(){
        Cliente cliente = ClienteMapper.getInstancia().Cargar(1);

        PedidoCliente pedido = new PedidoCliente();
        pedido.setCliente(cliente);
        pedido.setEstado(0);	//Estado "Pendiente"
        pedido.setFechaEntrega(new Date("2013/01/01"));
        pedido.setFechaPedido(new Date());
        pedido.setNroRemito(1);
        pedido.setPrioridad(1);

        ProductoMapper productoMapper = ProductoMapper.getInstancia();
        Producto producto = productoMapper.Cargar("1");

        DetallePedidoCliente detalle = new DetallePedidoCliente();
        detalle.setCantidad(10);
        detalle.setPrecioUnitario(producto.getPrecioCompra());
        detalle.setProducto(producto);

        pedido.getArrayDetallePedido().add(detalle);

        PedidoProveedor pedProv = new PedidoProveedor();
        pedProv.setNroPedido(5);

        StockComprometidoDetallePedidoCliente comprometido = new StockComprometidoDetallePedidoCliente();
        detalle.getStockComprometido().add(comprometido);
        comprometido.setCantidad(10);
        comprometido.setEsComprometidoDeposito(false);
        comprometido.setPedidoProveedor(pedProv);

        PedidoClienteMapper.getInstancia().Grabar(pedido);
    }

    private static void GrabarPedidoProveedor(){
        Proveedor proveedor = ProveedorMapper.getInstancia().Cargar(1);

        PedidoProveedor pedido = new PedidoProveedor();
        pedido.setProveedor(proveedor);
        pedido.setEstado(0);	//Pendiente
        pedido.setFechaEntrega(new Date("2013/01/01"));
        pedido.setFechaPedido(new Date());

        ProductoMapper productoMapper = ProductoMapper.getInstancia();
        Producto producto = productoMapper.Cargar("PROD1");

        DetallePedidoProveedor detalle = new DetallePedidoProveedor();
        detalle.setCantidad(10);
        //detalle.setPrecioUnitario(producto.getPrecioCompra());
        detalle.setProducto(producto);

        pedido.getArrayDetallePedido().add(detalle);

        PedidoProveedorMapper.getInstancia().Grabar(pedido);
    }

    private static void ModificarProducto(){
        ProductoMapper productoMapper = ProductoMapper.getInstancia();
        Producto producto = productoMapper.Cargar("1");

        //producto.setCodigo("1");
        producto.setNombre("Ba�aderas clase A TEST3");
        producto.setDescripcion("Ba�aderas de Clase A TEST3");
        producto.setPrecioCompra(119.50);

        productoMapper.Modificar(producto);
    }

    private static void CargarPedidoProveedor(){
        PedidoProveedor pedido = PedidoProveedorMapper.getInstancia().Cargar(7);
        System.out.println("Pedido cargado para Proveedor {1}, pedido el {2}, con {3} productos"
                            .replace("{1}", Long.toString(pedido.getProveedor().getCuit()))
                            .replace("{2}", pedido.getFechaPedido().toString())
                            .replace("{3}", Integer.toString(pedido.getArrayDetallePedido().size())));
    }
}
