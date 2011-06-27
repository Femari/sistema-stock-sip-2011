package Negocio.Modelo;

import java.util.*;

public class Recepcion {
	private int nroRecepcion;
	private Date fechaRecepcion;
	private DetalleRecepcion detalleRecepcion;
	private PedidoProveedor pedidoProveedor;

	public int getNroRecepcion() {
		return nroRecepcion;
	}
	public void setNroRecepcion(int nroRecepcion) {
		this.nroRecepcion = nroRecepcion;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	public DetalleRecepcion getDetalleRecepcion() {
		return detalleRecepcion;
	}
	public void setDetalleRecepcion(DetalleRecepcion detalleRecepcion) {
		this.detalleRecepcion = detalleRecepcion;
	}
	public PedidoProveedor getPedidoProveedor() {
		return pedidoProveedor;
	}
	public void setPedidoProveedor(PedidoProveedor pedidoProveedor) {
		this.pedidoProveedor = pedidoProveedor;
	}

	
}
