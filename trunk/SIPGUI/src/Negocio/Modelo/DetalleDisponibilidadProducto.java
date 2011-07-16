package Negocio.Modelo;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalleDisponibilidadProducto {
	
	private Producto producto;
	private int cantidadDisponible;
	private int cantidadSolicitada;
	private Date fechaDisponibilidad;
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}
	public void setCantidadDisponible(int cantidad) {
		this.cantidadDisponible = cantidad;
	}
	
	public int getCantidadSolicitada()
	{
		return cantidadSolicitada;
	}
	public void setCantidadSolicitada(int cantidad)
	{
		this.cantidadSolicitada = cantidad;
	}
	
	
	public Date getFecha() {
		return fechaDisponibilidad;
	}
	public void setFecha(Date fecha) {
		this.fechaDisponibilidad = fecha;
	}
	
	public String getDescripcionEstado()
	{
		if(cantidadSolicitada > cantidadDisponible)
		{	//No existe stock a futuro
			return "No disponible (Faltante al {DATE}: {CANTIDAD})".replace("{DATE}", new SimpleDateFormat("dd-MM-yyyy").format(fechaDisponibilidad)).replace("{CANTIDAD}", Integer.toString(cantidadSolicitada - cantidadDisponible));
		}
		else
		{	//Existe stock a futuro
			return "Stock disponible para el " + new SimpleDateFormat("dd-MM-yyyy").format(fechaDisponibilidad);
		}
	}
}
