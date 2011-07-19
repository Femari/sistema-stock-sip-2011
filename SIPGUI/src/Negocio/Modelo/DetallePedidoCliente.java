package Negocio.Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetallePedidoCliente extends DetallePedido {

    /**
     * Detalle de stock comprometido del deposito o de pedidos de proveedor
     */
    private ArrayList<StockComprometidoDetallePedidoCliente> stockComprometido = new ArrayList<StockComprometidoDetallePedidoCliente>();

    public DetallePedidoCliente() {
        super();
    }

    public List<StockComprometidoDetallePedidoCliente> getStockComprometido() {
        return stockComprometido;
    }

    public void setStockComprometido(ArrayList<StockComprometidoDetallePedidoCliente> stockComprometido) {
        this.stockComprometido = stockComprometido;
    }

    /**
     * Compromete stock del deposito
     * @param cantidad
     */
    public void ComprometerStock(int cantidad) {
        StockComprometidoDetallePedidoCliente comprometido = new StockComprometidoDetallePedidoCliente();
        comprometido.setCantidad(cantidad);
        comprometido.setTipoCompromiso(0);

        stockComprometido.add(comprometido);
    }

    /**
     * Compromete stock de un pedido a proveedor
     * @param pedido
     * @param cantidad
     */
    public void ComprometerStock(PedidoProveedor pedido, int cantidad) {
        DetallePedidoProveedor detalle = pedido.getDetallePedidoProveedor(producto);
        //DetallePedidoProveedor detalle = pedido.getDetalle(producto);
        if (detalle != null) {
            StockComprometidoDetallePedidoCliente comprometido = new StockComprometidoDetallePedidoCliente();
            comprometido.setCantidad(cantidad);
            comprometido.setTipoCompromiso(1);
            comprometido.setPedidoProveedor(pedido);

            stockComprometido.add(comprometido);
        } else {
            System.err.println("El pedido seleccionado no posee el producto seleccionado.");
        }
    }

    /**
     * Compromete stock de un pedido a proveedor futuro (que no se ah realizado aun)
     * @param pedido
     * @param cantidad
     */
    public void ComprometerStockFuturo(int cantidad) {
        StockComprometidoDetallePedidoCliente comprometido = new StockComprometidoDetallePedidoCliente();
        comprometido.setCantidad(cantidad);
        comprometido.setTipoCompromiso(2);

        stockComprometido.add(comprometido);
    }

    public boolean ValidarStockComprometido() {
        int cantidadComprometida = 0;
        for (StockComprometidoDetallePedidoCliente comprometido : stockComprometido) {
            cantidadComprometida += comprometido.getCantidad();
        }

        return (cantidadComprometida == cantidad);
    }

    public Date calcularFechaEstimadaEntrega() {
        Date date = new Date();
        //TODO: cambiar esto por la fecha estimada que tarda ese producto en entregarse si no esta pedido.
        Date fechaPedidoNoRealizado = new Date(date.getYear(), date.getMonth() + 2, date.getDay());
        for (StockComprometidoDetallePedidoCliente comprometido : stockComprometido) {
            if (comprometido.getTipoCompromiso() == 1 && (comprometido.getPedidoProveedor().getFechaEntrega().compareTo(date) > 0)) {	//Compromete stock de un pedido mas futuro
                date = comprometido.getPedidoProveedor().getFechaEntrega();
            } //TODO: cambiar esto por la fecha estimada que tarda ese producto en entregarse si no esta pedido.
            else if (comprometido.getTipoCompromiso() == 2 && (date.compareTo(fechaPedidoNoRealizado) > 0)) {
                date = fechaPedidoNoRealizado;
            }
        }

        return date;
    }
}
