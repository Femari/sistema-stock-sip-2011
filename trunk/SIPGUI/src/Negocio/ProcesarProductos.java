/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Negocio.Modelo.PedidoCliente;
import Negocio.Modelo.PedidoProveedor;
import Negocio.Modelo.Producto;
import Persistencia.ProductoMapper;
import java.util.ArrayList;

/**
 *
 * @author Dan
 */
public class ProcesarProductos implements IProcesarProductos {

    private ArrayList<Producto> productos;
    private Producto producto;

    public ArrayList<Producto> getProductos() {
        productos = ProductoMapper.getInstancia().CargarTodos();
        return productos;
    }

    public ArrayList<Producto> getProductos(String idProducto, String Descripcion) {
        
        return productos;
    }

    public Producto getProducto(String idProducto) {
        producto = ProductoMapper.getInstancia().Cargar(idProducto);
        return producto;
    }

    public void agregarProducto(Producto producto) {
    }

    public void actualizarStockOut(PedidoCliente pedidoCliente) {
    }

    public void actualizarStockIn(PedidoProveedor pedidoProveedor) {
    }

    public void modificarProducto(Producto productoActual, Producto productoModificado) {
    }
}
