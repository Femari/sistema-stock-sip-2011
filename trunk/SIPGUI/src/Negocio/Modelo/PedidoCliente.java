package Negocio.Modelo;
import java.util.Date;
import java.util.ArrayList;

public class PedidoCliente {
    private int nroPedido;
    private Cliente cliente;
    private Date fechaPedido;
    private Date fechaEntrega;
    private int estado;
    private ArrayList<DetallePedidoCliente> arrayDetallePedidoCliente = new ArrayList<DetallePedidoCliente>();
    private int nroRemito;
    private int prioridad;
	
    public int getNroPedido() {
		return nroPedido;
	}
	public void setNroPedido(int nroPedido) {
		this.nroPedido = nroPedido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public int getNroRemito() {
		return nroRemito;
	}
	public void setNroRemito(int nroRemito) {
		this.nroRemito = nroRemito;
	}
	public ArrayList<DetallePedidoCliente> getArrayDetallePedidoCliente() {
		return arrayDetallePedidoCliente;
	}
	public void setArrayDetallePedidoCliente(
			ArrayList<DetallePedidoCliente> arrayDetallePedidoCliente) {
		this.arrayDetallePedidoCliente = arrayDetallePedidoCliente;
	}
	public void agregarDetallePedidoCliente(DetallePedidoCliente xDetallePedidoCliente){
		arrayDetallePedidoCliente.add(xDetallePedidoCliente);
		
	}
		public int getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	public Date calcularFechaEstimadaEntrega() {
		Date date = new Date();
		for(DetallePedidoCliente detalle: arrayDetallePedidoCliente){
			Date dateDetalle = detalle.calcularFechaEstimadaEntrega();
			date = (dateDetalle.compareTo(date) > 0?dateDetalle:date);
		}
		
		return date;
	}
    
    
}
