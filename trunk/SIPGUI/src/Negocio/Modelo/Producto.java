package Negocio.Modelo;

import java.util.Calendar;

public class Producto {

    private String nombre;
    private String codigo;
    private Proveedor proveedor;
    private int stockMinimo;
    private String descripcion;
    private int tiempoReposicion;

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    public int getTiempoReposicion() {
        return tiempoReposicion;
    }

    public void setTiempoReposicion(int tiempoReposicion) {
        this.tiempoReposicion = tiempoReposicion;
    }
    
    public boolean equals(Producto producto) {
        return codigo.equalsIgnoreCase(producto.codigo);
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    
            
}
