package Negocio.Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetallePedidoCliente extends DetallePedido {
	private double precioUnitario;

	/**
	 * Detalle de stock comprometido del deposito o de pedidos de proveedor
	 */
	private ArrayList<StockComprometidoDetallePedidoCliente> stockComprometido = new ArrayList<StockComprometidoDetallePedidoCliente>();
	
        
        public DetallePedidoCliente(){
            super();
        }
        
    @Override
	public void setProducto(Producto producto) {
		this.producto = producto;
		this.precioUnitario = producto.getPrecioCompra();
	}
	
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	public List<StockComprometidoDetallePedidoCliente> getStockComprometido()
	{
		return stockComprometido;
	}
	public void setStockComprometido(ArrayList<StockComprometidoDetallePedidoCliente> stockComprometido)
	{
		this.stockComprometido = stockComprometido; 
	}
	
	/**
	 * Compromete stock del deposito
	 * @param cantidad
	 */
	public void ComprometerStock(int cantidad)
	{
		StockComprometidoDetallePedidoCliente comprometido = new StockComprometidoDetallePedidoCliente();
		comprometido.setCantidad(cantidad);
		comprometido.setEsComprometidoDeposito(true);
		
		stockComprometido.add(comprometido);
	}
	
	/**
	 * Compromete stock de un pedido a proveedor
	 * @param pedido
	 * @param cantidad
	 */
	public void ComprometerStock(PedidoProveedor pedido, int cantidad)
	{
            DetallePedido detalle = pedido.getDetalle(producto);
		if(detalle != null){
			StockComprometidoDetallePedidoCliente comprometido = new StockComprometidoDetallePedidoCliente();
			comprometido.setCantidad(cantidad);
			comprometido.setEsComprometidoDeposito(false);
			comprometido.setPedidoProveedor(pedido);
			
			stockComprometido.add(comprometido);
		}else{
			System.err.println("El pedido seleccionado no posee el producto seleccionado.");
		}
	}
	
	public boolean ValidarStockComprometido()
	{
		int cantidadComprometida = 0;
		for(StockComprometidoDetallePedidoCliente comprometido : stockComprometido)
		{
			cantidadComprometida += comprometido.getCantidad();
		}
		
		return (cantidadComprometida == cantidad);
	}
	
	public Date calcularFechaEstimadaEntrega() {
		Date date = new Date();
		for(StockComprometidoDetallePedidoCliente comprometido: stockComprometido){
			if(!comprometido.isEsComprometidoDeposito() && (comprometido.getPedidoProveedor().getFechaEntrega().compareTo(date) > 0)){	//Compromete stock de un pedido mas futuro
				date = comprometido.getPedidoProveedor().getFechaEntrega();
			}
		}
		
		return date;
	}
}
