package Persistencia;

import java.sql.*;
import Negocio.Modelo.DetalleDisponibilidadProducto;
import Negocio.Modelo.Producto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ProductoMapper {

    private static ProductoMapper instanciaProductoMapper;

    public static ProductoMapper getInstancia() {
        if (instanciaProductoMapper == null) {
            instanciaProductoMapper = new ProductoMapper();
        }
        return instanciaProductoMapper;
    }

    private ProductoMapper() {
    }

    public Producto Cargar(String codigo) {
        try {

            PreparedStatement selectProducto;
            //String sqlString = "SELECT Producto.IdProducto, descripcion, nombre, idProveedor, Cantidad FROM Producto INNER JOIN StockDeposito ON Producto.IdProducto = StockDeposito.IdProducto WHERE Producto.IdProducto = ?";
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

    public ArrayList<Producto> Cargar(String codigo, String descripcion) {
        ArrayList<Producto> productos = new ArrayList<Producto>();

        try {
            PreparedStatement selectProductos;
            String sqlString = "SELECT * FROM Producto WHERE ";
            if (!codigo.isEmpty()) {
                sqlString += "Producto.IdProducto LIKE '%" + codigo + "%' ";
            }
            if (!descripcion.isEmpty()) {
                if (!codigo.isEmpty()) {
                    sqlString += "OR ";
                }
                sqlString += "Producto.Descripcion LIKE '%" + descripcion + "%'";
            }
            System.out.println("Cargar con filtro:" + sqlString);
            selectProductos = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);
            Producto producto = new Producto();
            ResultSet rs = selectProductos.executeQuery();
            while (MapearEntidad(producto, rs)) {
                productos.add(producto);
                producto = new Producto();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public ArrayList<Producto> CargarTodos() {
        ArrayList<Producto> productos = new ArrayList<Producto>();

        try {
            PreparedStatement selectCliente;
            String sqlString = "SELECT * FROM Producto ORDER BY Nombre";
            selectCliente = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);

            Producto producto = new Producto();
            ResultSet rs = selectCliente.executeQuery();
            while (MapearEntidad(producto, rs)) {
                productos.add(producto);
                producto = new Producto();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public void Modificar(Producto producto) {
        try {
            PreparedStatement updateProducto;
            String sqlString = "UPDATE Producto SET nombre = ? , descripcion = ? , idProveedor = ? , stockMinimo = ? WHERE IdProducto = ?";
            updateProducto = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);

            MapearUpdatePreparedStatement(producto, updateProducto);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el nivel de Stock libre del producto idnicado
     * @param producto a consultar stock
     * @return Cantidad de stock libre (puede ser negativo)
     */
    public int ObtenerStockLibreDeposito(Producto producto) {
        try {
            PreparedStatement consultaStockLibre;
            String sqlString = "SELECT StockLibre FROM StockDepositoLibre WHERE IdProducto = ?";
            consultaStockLibre = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);

            MapearSelectPreparedStatement(producto.getCodigo(), consultaStockLibre);

            ResultSet rs = consultaStockLibre.executeQuery();
            int stock = 0;
            if (rs.next()) {
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
    
    public HashMap<Integer, StockLibrePorPedido> ObtenerStockLibrePorPedido(Producto producto){
        HashMap<Integer, StockLibrePorPedido> stockLibrePedidos = new HashMap<Integer, StockLibrePorPedido>();
        try {
            PreparedStatement consultaStockLibrePedidos;
            String sqlString = "SELECT * FROM StockLibrePorPedido WHERE IdProducto = ? ORDER BY FechaDisponibilidad";
            consultaStockLibrePedidos = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);

            MapearSelectPreparedStatement(producto.getCodigo(), consultaStockLibrePedidos);
            
            ResultSet rs = consultaStockLibrePedidos.executeQuery();
            int i = 0;
            while (rs.next()) {
                stockLibrePedidos.put(i, new StockLibrePorPedido(rs.getInt("NroPedido"), rs.getInt("StockLibre")));
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stockLibrePedidos;
    
    }

    public DetalleDisponibilidadProducto ObtenerDetallesDisponibilidadFutura(Producto producto, int cantidadSolicitada) {
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
            if (rs.next()) {
                detalle.setCantidadDisponible(rs.getInt("StockLibre"));
                detalle.setFechaDisponibilidad(rs.getDate("FechaDisponibilidad"));
            } else {
                detalle.setCantidadDisponible(0);
                detalle.setFechaDisponibilidad(new java.util.Date());
            }

            if (cantidadSolicitada > detalle.getCantidadDisponible()) {
                detalle.setCantidadFaltante(cantidadSolicitada - detalle.getCantidadDisponible());

                Calendar fec = Calendar.getInstance();
                fec.setTime(new java.util.Date());
                fec.add(Calendar.DAY_OF_YEAR, producto.getTiempoReposicion() + 7); // se suma 7 para darle una semana de changui, ya que los pedidos a proveedor se realizan semanalmente

                detalle.setFechaDisponibilidadNuevoPedido(fec.getTime());
            }

            rs.close();
            consultaStockLibre.close();

            return detalle;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean MapearEntidad(Producto producto, ResultSet rs) {
        try {
            if (rs.next()) {
                producto.setCodigo(rs.getString("IdProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setProveedor(ProveedorMapper.getInstancia().Cargar(rs.getLong("idProveedor")));
                producto.setStockMinimo(rs.getInt("StockMinimo"));
                producto.setTiempoReposicion(rs.getInt("TiempoReposicion"));

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
        } catch (SQLException ex) {
            System.err.println("UsePreparedStatement: " + ex.getMessage());
        }
    }

    static void MapearUpdatePreparedStatement(Producto producto, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getDescripcion());
            preparedStatement.setLong(3, producto.getProveedor().getCuit());
            preparedStatement.setString(4, producto.getCodigo());
            preparedStatement.setInt(5, producto.getStockMinimo());
            preparedStatement.setInt(6, producto.getTiempoReposicion());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("UsePreparedStatement: " + ex.getMessage());
        }
    }
    
    
    public class StockLibrePorPedido{
        private int nroPedido;
        private int stockLibre;

        public StockLibrePorPedido(int nroPedido, int stockLibre) {
            this.nroPedido = nroPedido;
            this.stockLibre = stockLibre;
        }
        
        public int getNroPedido() {
            return nroPedido;
        }

        public void setNroPedido(int nroPedido) {
            this.nroPedido = nroPedido;
        }

        public int getStockLibre() {
            return stockLibre;
        }

        public void setStockLibre(int stockLibre) {
            this.stockLibre = stockLibre;
        }
        
        
    }
    
}
