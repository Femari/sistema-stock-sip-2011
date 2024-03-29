package Negocio.Modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetalleDisponibilidadProducto {

    private Producto producto;
    private int cantidadDisponible;
    private int cantidadSolicitada;
    private Date fechaDisponibilidad;
    private int cantidadFaltante;
    private Date fechaDisponibilidadNuevoPedido;

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

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidad) {
        this.cantidadSolicitada = cantidad;
    }

    public Date getFechaDisponibilidad() {
        return fechaDisponibilidad;
    }

    public void setFechaDisponibilidad(Date fecha) {
        this.fechaDisponibilidad = fecha;
    }

    public int getCantidadFaltante() {
        return cantidadFaltante;
    }

    public void setCantidadFaltante(int cantidadfaltante) {
        this.cantidadFaltante = cantidadfaltante;
    }

    public Date getFechaDisponibilidadNuevoPedido() {
        return fechaDisponibilidadNuevoPedido;
    }

    public void setFechaDisponibilidadNuevoPedido(Date fechaDisponibilidadNuevoPedido) {
        this.fechaDisponibilidadNuevoPedido = fechaDisponibilidadNuevoPedido;
    }

    public String getDescripcionEstado() {
        if (cantidadSolicitada > cantidadDisponible) {	//No hay suficiente stock a futuro
            return "No disponible (Faltante al {DATE}: {CANTIDAD})".replace("{DATE}", new SimpleDateFormat("dd-MM-yyyy").format(fechaDisponibilidad)).replace("{CANTIDAD}", Integer.toString(cantidadSolicitada - cantidadDisponible));
        } else {	//Existe stock a futuro
            return "Stock disponible para el " + new SimpleDateFormat("dd-MM-yyyy").format(fechaDisponibilidad);
        }
    }

    public String getDescripcionEstado(Date fechaSolicitada) {
        if (cantidadSolicitada > cantidadDisponible) {	//No hay suficiente stock a futuro
            return "No disponible (Faltante al {DATE}: {CANTIDAD})".replace("{DATE}", new SimpleDateFormat("dd-MM-yyyy").format(fechaDisponibilidad)).replace("{CANTIDAD}", Integer.toString(cantidadSolicitada - cantidadDisponible));
        } else {	//Existe stock a futuro
            if (fechaSolicitada.compareTo(fechaDisponibilidad) >= 0) {
                return "Stock disponible para el " + new SimpleDateFormat("dd-MM-yyyy").format(fechaDisponibilidad);
            } else {
                return "No disponible al {DATE}. Entrante el día {DATE2}".replace("{DATE}", new SimpleDateFormat("dd-MM-yyyy").format(fechaSolicitada)).replace("{DATE2}", new SimpleDateFormat("dd-MM-yyyy").format(fechaDisponibilidad));
            }
        }
    }

    public String getDisponibilidadFaltante() {
        if (cantidadSolicitada > cantidadDisponible) {
            return "Faltante disponible al {DATE}".replace("{DATE}", new SimpleDateFormat("dd-MM-yyyy").format(fechaDisponibilidadNuevoPedido));
        } else {
            return "";
        }
    }
}
