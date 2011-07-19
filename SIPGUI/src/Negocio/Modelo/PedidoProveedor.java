package Negocio.Modelo;

import java.util.ArrayList;

public class PedidoProveedor extends Pedido {

    private ArrayList<DetallePedidoProveedor> arrayDetallePedidoProveedor = new ArrayList<DetallePedidoProveedor>();
    private Proveedor proveedor;

    public PedidoProveedor() {
        super();
        this.arrayDetallePedidoProveedor = new ArrayList<DetallePedidoProveedor>();
    }

    public ArrayList<DetallePedidoProveedor> getArrayDetallePedido() {
        return this.arrayDetallePedidoProveedor;
    }

    public Proveedor getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(Proveedor proved) {
        this.proveedor = proved;
    }

    public ArrayList<DetallePedidoProveedor> getArrayDetallePedidoProveedor() {
        return this.arrayDetallePedidoProveedor;
    }

    public DetallePedidoProveedor getDetallePedidoProveedor(Producto producto) {
        for (DetallePedidoProveedor detalle : arrayDetallePedidoProveedor) {
            if (detalle.getProducto().equals(producto)) {
                return detalle;
            }
        }

        return null;
    }
}
