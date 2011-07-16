/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Negocio.Modelo.PedidoCliente;
import Negocio.Modelo.PedidoProveedor;
import Negocio.Modelo.Producto;
import java.util.ArrayList;

/**
 *
 * @author Dan
 */
public interface IProcesarProductos {

    public abstract ArrayList<Producto> getProductos();

    public abstract ArrayList<Producto> getProductos(String idProducto, String Descripcion);

    public abstract Producto getProducto(String idProducto);

    public abstract void agregarProducto(Producto producto);

    public abstract void actualizarStockOut(PedidoCliente pedidoCliente);

    public abstract void actualizarStockIn(PedidoProveedor pedidoProveedor);

    public abstract void modificarProducto(Producto productoActual, Producto productoModificado);
}