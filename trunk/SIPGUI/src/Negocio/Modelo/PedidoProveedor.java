package Negocio.Modelo;

import java.util.ArrayList;
import java.util.Date;

public class PedidoProveedor {
	private int nroPedido;
    private Proveedor proveedor;
    private Date fechaPedido;
    private Date fechaEntrega;
    private int total;
    private ArrayList<DetallePedidoProveedor> detallesPedido = new ArrayList<DetallePedidoProveedor>();
    private int estado;
	
    public int getNroPedido() {
		return nroPedido;
	}
	public void setNroPedido(int nroPedido) {
		this.nroPedido = nroPedido;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public ArrayList<DetallePedidoProveedor> getArrayDetallePedidoProveedor()
	{
		return this.detallesPedido;
	}
	
	public DetallePedidoProveedor getDetalle(Producto producto) {
		for(DetallePedidoProveedor detalle: detallesPedido){
			if(detalle.getProducto().equals(producto)){
				return detalle;
			}
		}
		
		return null;
	}
}
