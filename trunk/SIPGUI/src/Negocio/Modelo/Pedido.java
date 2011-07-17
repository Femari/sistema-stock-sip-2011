/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dan
 */
public class Pedido {

    protected int nroPedido;
    protected Date fechaPedido;
    protected Date fechaEntrega;
    protected int estado;
    protected ArrayList<DetallePedido> arrayDetallePedido = new ArrayList<DetallePedido>();
    protected int total;

    public Pedido() {
    }

    public int getNroPedido() {
        return nroPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public int getEstado() {
        return estado;
    }

    public DetallePedido getDetalle(Producto producto) {
        for (DetallePedido detalle : arrayDetallePedido) {
            if (detalle.getProducto().equals(producto)) {
                return detalle;
            }
        }
        return null;
    }

    public int getTotal() {
        return total;
    }

    public void setNroPedido(int nroPedido) {
        this.nroPedido = nroPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
