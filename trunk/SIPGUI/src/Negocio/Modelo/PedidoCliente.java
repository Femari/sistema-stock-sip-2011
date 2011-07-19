package Negocio.Modelo;

import java.util.Date;
import java.util.ArrayList;

public class PedidoCliente extends Pedido {

    private Cliente cliente;
    private ArrayList<DetallePedidoCliente> arrayDetallePedidoCliente = new ArrayList<DetallePedidoCliente>();
    private int nroRemito;
    private Prioridad prioridad;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(int nroRemito) {
        this.nroRemito = nroRemito;
    }

    public ArrayList<DetallePedidoCliente> getArrayDetallePedido() {
        return arrayDetallePedidoCliente;
    }

    public void setArrayDetallePedidoCliente(ArrayList<DetallePedidoCliente> arrayDetallePedidoCliente) {
        this.arrayDetallePedidoCliente = arrayDetallePedidoCliente;
    }

    public void agregarDetallePedidoCliente(DetallePedidoCliente xDetallePedidoCliente) {
        arrayDetallePedidoCliente.add(xDetallePedidoCliente);

    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Date calcularFechaEstimadaEntrega() {
        Date date = new Date();
        for (DetallePedidoCliente detalle : arrayDetallePedidoCliente) {
            Date dateDetalle = detalle.calcularFechaEstimadaEntrega();
            date = (dateDetalle.compareTo(date) > 0 ? dateDetalle : date);
        }

        return date;
    }
}
