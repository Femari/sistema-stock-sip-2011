/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Negocio.Modelo.Producto;
import Negocio.Modelo.Proveedor;
import Persistencia.ProductoMapper;
import Persistencia.ProveedorMapper;
import java.util.ArrayList;

/**
 *
 * @author NicolasM
 */
public class GenerarReporteStock {

    private long filtroProveedor;
    private String filtroProductos;

    public void SetearFiltros(long filtroProveedor, String filtroProductos) {
        this.filtroProductos = filtroProductos;
        this.filtroProveedor = filtroProveedor;
    }

    public ArrayList<Proveedor> getProveedores() {
        return ProveedorMapper.getInstancia().CargarTodos();
    }

    public ArrayList<Producto> getProductos() {
        return ProductoMapper.getInstancia().CargarTodos();
    }
}
