/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Modelo;

/**
 *
 * @author Dan
 */
public class DetallePedido {
    
    private int cantidad;
    private Producto producto;
    

    public DetallePedido() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
   
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}
