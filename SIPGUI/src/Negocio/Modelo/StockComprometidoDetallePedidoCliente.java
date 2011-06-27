package Negocio.Modelo;

public class StockComprometidoDetallePedidoCliente {
	
	private PedidoProveedor pedidoProveedor;
	private boolean esComprometidoDeposito;
	private int cantidad;

	public PedidoProveedor getPedidoProveedor()
	{
		return pedidoProveedor;
	}
	public void setPedidoProveedor(PedidoProveedor pedido)
	{
		pedidoProveedor = pedido;
	}
		
	public boolean isEsComprometidoDeposito() {
		return esComprometidoDeposito;
	}
	public void setEsComprometidoDeposito(boolean esComprometidoDeposito) {
		this.esComprometidoDeposito = esComprometidoDeposito;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
