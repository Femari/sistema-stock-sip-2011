package Persistencia;

import java.sql.*;
import java.util.List;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import Negocio.Modelo.DetalleDisponibilidadProducto;
import Negocio.Modelo.Producto;

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
        String sqlString = "SELECT Producto.IdProducto, descripcion, nombre, idProveedor, Cantidad, precioCompra FROM Producto INNER JOIN StockDeposito ON Producto.IdProducto = StockDeposito.IdProducto WHERE Producto.IdProducto = ?";
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

    public void Modificar(Producto producto){
        try {
            PreparedStatement updateProducto;
            String sqlString = "UPDATE Producto SET nombre = ? , descripcion = ? , idProveedor = ? , precioCompra = ? WHERE IdProducto = ?";
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
            rs.next(); int stock = rs.getInt("StockLibre"); //Execute scalar

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

            ResultSet rs = consultaStockLibre.executeQuery();
            rs.next();

            DetalleDisponibilidadProducto detalle = new DetalleDisponibilidadProducto();
            detalle.setProducto(producto);
            detalle.setCantidadDisponible(rs.getInt("StockLibre"));
            detalle.setFecha(rs.getDate("FechaDisponibilidad"));
            detalle.setCantidadSolicitada(cantidadSolicitada);

            rs.close();
            consultaStockLibre.close();

            return detalle;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void MapearEntidad(Producto producto, ResultSet rs){
        try {
            rs.next();
            producto.setCodigo(rs.getString("IdProducto"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setNombre(rs.getString("nombre"));
            producto.setProveedor(ProveedorMapper.getInstancia().Cargar(rs.getInt("idProveedor")));
            producto.setStock(rs.getInt("Cantidad"));
            producto.setPrecioCompra(rs.getDouble("precioCompra"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            preparedStatement.setInt(3, producto.getProveedor().getCuit());
            preparedStatement.setDouble(4, producto.getPrecioCompra());
            preparedStatement.setString(5, producto.getCodigo());

            preparedStatement.executeUpdate();

        }catch(SQLException ex) {
            System.err.println("UsePreparedStatement: " + ex.getMessage());
        }
    }
	
	
}
