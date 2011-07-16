package Negocio.Modelo;

import java.util.ArrayList;

public class PedidoProveedor extends Pedido {

    private ArrayList<DetallePedidoProveedor> arrayDetallePedido;
    private Proveedor proveedor;

    public PedidoProveedor() {
        super();
        this.arrayDetallePedido = new ArrayList<DetallePedidoProveedor>();
    }

    public ArrayList<DetallePedidoProveedor> getArrayDetallePedido() {
        return this.arrayDetallePedido;
    }
    
    public Proveedor getProveedor(){
        return this.proveedor;
    }
    
    public void setProveedor(Proveedor proved){
        this.proveedor = proved;
    }
}
