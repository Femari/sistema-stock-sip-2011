package Negocio.Modelo;

public class StockComprometidoDetallePedidoCliente {

    private PedidoProveedor pedidoProveedor;
    private int tipoCompromiso;
    private int cantidad;

    public int getTipoCompromiso() {
        return tipoCompromiso;
    }

    public void setTipoCompromiso(int tipoCompromiso) {
        this.tipoCompromiso = tipoCompromiso;
    }

    public PedidoProveedor getPedidoProveedor() {
        return pedidoProveedor;
    }

    public void setPedidoProveedor(PedidoProveedor pedido) {
        pedidoProveedor = pedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
