package Persistencia;

import java.sql.*;
import java.util.List;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import Negocio.Modelo.DetalleDisponibilidadProducto;
import Negocio.Modelo.Producto;
import java.util.ArrayList;

public class ProductoMapper {
		
    private static ProductoMapper instanciaProductoMapper;

    public static ProductoMapper getInstancia(){
        if (instanciaProductoMapper == null){
                instanciaProductoMapper = new ProductoMapper();
        }
        return instanciaProductoMapper;
    }

    private ProductoMapper(){

    }


    public Producto Cargar(String codigo){
        try {

        PreparedStatement selectProducto;
        //String sqlString = "SELECT Producto.IdProducto, descripcion, nombre, idProveedor, Cantidad, precioCompra FROM Producto INNER JOIN StockDeposito ON Producto.IdProducto = StockDeposito.IdProducto WHERE Producto.IdProducto = ?";
        String sqlString = "SELECT * FROM Producto WHERE Producto.IdProducto = ?";
        selectProducto = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);

        MapearSelectPreparedStatement(codigo, selectProducto);
        Producto producto = new Producto();
        MapearEntidad(producto, selectProducto.executeQuery());
        return producto;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }	
    }
    
     public ArrayList<Producto> CargarTodos() {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        
        try {
            PreparedStatement selectCliente;
            String sqlString = "SELECT * FROM Producto ORDER BY Nombre";
            selectCliente = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);

            Producto producto = new Producto();
            ResultSet rs = selectCliente.executeQuery();
            while(MapearEntidad(producto, rs)){
                productos.add(producto);
                producto = new Producto();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return productos;
    }

    public void Modificar(Producto producto){
        try {
            PreparedStatement updateProducto;
            String sqlString = "UPDATE Producto SET nombre = ? , descripcion = ? , idProveedor = ? , precioCompra = ?, stockMinimo = ? WHERE IdProducto = ?";
            updateProducto = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);

            MapearUpdatePreparedStatement( producto, updateProducto);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el nivel de Stock libre del producto idnicado
     * @param producto a consultar stock
     * @return Cantidad de stock libre (puede ser negativo)
     */
    public int ObtenerStockLibre(Producto producto)	{
        try {
            PreparedStatement consultaStockLibre;
            String sqlString = "SELECT StockLibre FROM StockDepositoLibre WHERE IdProducto = ?";
            consultaStockLibre = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);

            MapearSelectPreparedStatement(producto.getCodigo(), consultaStockLibre);

            ResultSet rs = consultaStockLibre.executeQuery();
            int stock = 0;
            if(rs.next()){
                stock = rs.getInt("StockLibre"); //Execute scalar
            }
            
            rs.close();
            consultaStockLibre.close();

            return stock;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public DetalleDisponibilidadProducto ObtenerDetallesDisponibilidadFutura(Producto producto, int cantidadSolicitada){
        try {
            PreparedStatement consultaStockLibre;

            //Busco la fecha mas cercana en el futuro para la que hay stock disponible (sin comprometer) u obtengo la fecha mas lejana si ninguno puede cubrirlo 
            String sqlString = "SELECT TOP 1 StockFuturo.StockLibre, StockFuturo.FechaDisponibilidad FROM DisponibilidadStockAFuturo StockFuturo WHERE StockFuturo.IdProducto = ? AND ({1}) ORDER BY StockFuturo.StockLibre ASC";
            sqlString = sqlString.replace("{1}", "StockFuturo.StockLibre >= ? OR StockFuturo.FechaDisponibilidad = (SELECT MAX(FechaDisponibilidad) FROM DisponibilidadStockAFuturo WHERE IdProducto = StockFuturo.IdProducto)");

            consultaStockLibre = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);

            MapearSelectPreparedStatement(producto.getCodigo(), consultaStockLibre);
            consultaStockLibre.setInt(2, cantidadSolicitada);

            DetalleDisponibilidadProducto detalle = new DetalleDisponibilidadProducto();
            detalle.setProducto(producto);
            detalle.setCantidadSolicitada(cantidadSolicitada);
            ResultSet rs = consultaStockLibre.executeQuery();
            if(rs.next()){
            detalle.setCantidadDisponible(rs.getInt("StockLibre"));
            detalle.setFecha(rs.getDate("FechaDisponibilidad"));
            }
            else
            {
                detalle.setCantidadDisponible(0);
                detalle.setFecha(new java.util.Date());
            }    
            
            rs.close();
            consultaStockLibre.close();

            return detalle;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean MapearEntidad(Producto producto, ResultSet rs){
        try {
            if(rs.next()){
                producto.setCodigo(rs.getString("IdProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setProveedor(ProveedorMapper.getInstancia().Cargar(rs.getLong("idProveedor")));
                producto.setPrecioCompra(rs.getDouble("precioCompra"));
                producto.setStockMinimo(rs.getInt("StockMinimo"));
                
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    static void MapearSelectPreparedStatement(String codigo, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, codigo);
        }catch(SQLException ex) {
            System.err.println("UsePreparedStatement: " + ex.getMessage());
        }
}

    static void MapearUpdatePreparedStatement(Producto producto, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getDescripcion());
            preparedStatement.setLong(3, producto.getProveedor().getCuit());
            preparedStatement.setDouble(4, producto.getPrecioCompra());
            preparedStatement.setString(5, producto.getCodigo());
            preparedStatement.setInt(6, producto.getStockMinimo());

            preparedStatement.executeUpdate();

        }catch(SQLException ex) {
            System.err.println("UsePreparedStatement: " + ex.getMessage());
        }
    }
	
	
}
