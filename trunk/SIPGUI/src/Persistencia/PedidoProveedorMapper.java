package Persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Negocio.Modelo.DetallePedidoCliente;
import Negocio.Modelo.DetallePedidoProveedor;
import Negocio.Modelo.PedidoCliente;
import Negocio.Modelo.PedidoProveedor;
import Negocio.Modelo.Producto;
import Negocio.Modelo.Proveedor;

public class PedidoProveedorMapper {

	private static PedidoProveedorMapper instanciaProductoMapper;
	
	public static PedidoProveedorMapper getInstancia(){
		if (instanciaProductoMapper == null){
			instanciaProductoMapper = new PedidoProveedorMapper();
		}
		return instanciaProductoMapper;
	}
	
	private PedidoProveedorMapper(){
		
	}


	public void Grabar(PedidoProveedor pedido)
	{
		String query = "INSERT INTO PedidoProveedor(Cuit, Estado, FechaInicio, FechaEntrega) VALUES (?,?,?,?)";
		String queryDetalle = "INSERT INTO DetallePedidoProveedor(NroPedido, IdProducto, Cantidad) VALUES (?,?,?)";
		
		PreparedStatement statement;
		
		try {

			ConexionManager.getInstancia().beginTransaction();
			statement = ConexionManager.getInstancia().getConexion().prepareStatement(query);
			MapearStatementInsert(pedido, statement);
			statement.execute();
			
			statement = ConexionManager.getInstancia().getConexion().prepareStatement("SELECT @@IDENTITY NroPedido");
			ResultSet rs = statement.executeQuery(); 
			rs.next(); pedido.setNroPedido(rs.getInt("NroPedido"));
			rs.close(); statement.close();
			
			statement = ConexionManager.getInstancia().getConexion().prepareStatement(queryDetalle);
			for(DetallePedidoProveedor detalle : pedido.getArrayDetallePedidoProveedor())
			{
				MapearStatementInsert(pedido, detalle, statement);
				statement.execute();
			}
			
			ConexionManager.getInstancia().commitTransaction();

		} catch (SQLException e) {
			e.printStackTrace();
			ConexionManager.getInstancia().rollBackTransaction();
		}
	}

	public PedidoProveedor Cargar(int nroPedido){
		String query = "SELECT NroPedido, Cuit, FechaInicio, FechaEntrega, Estado FROM PedidoProveedor WHERE NroPedido = ?";
		String queryDetalle = "SELECT NroPedido, IdProducto, Cantidad FROM DetallePedidoProveedor WHERE NroPedido = ?";
		
		PedidoProveedor pedido = new PedidoProveedor();
		PreparedStatement statement;

		try{
			statement = ConexionManager.getInstancia().getConexion().prepareStatement(query);
			MapearStatementSelect(nroPedido, statement);
			
			ResultSet rs = statement.executeQuery(); 
			rs.next(); MapearEntidad(pedido, rs);
			rs.close(); statement.close();

			//Carga de detalle de pedido
			statement = ConexionManager.getInstancia().getConexion().prepareStatement(queryDetalle);
			MapearStatementSelect(nroPedido, statement);
			
			ArrayList<DetallePedidoProveedor> detalles = pedido.getArrayDetallePedidoProveedor();
			rs = statement.executeQuery(); 
			while(rs.next()){
				DetallePedidoProveedor detalle = new DetallePedidoProveedor();
				MapearEntidad(detalle, rs);
				detalles.add(detalle);
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return pedido;
	}
	
	private static void MapearStatementInsert(PedidoProveedor pedido, PreparedStatement statement) throws SQLException
	{
		statement.setInt(1, pedido.getProveedor().getCuit());
		statement.setInt(2, pedido.getEstado());
		statement.setDate(3, new Date(pedido.getFechaPedido().getTime()));
		statement.setDate(4, new Date(pedido.getFechaEntrega().getTime()));
	}
	
	private static void MapearStatementInsert(PedidoProveedor pedido, DetallePedidoProveedor detalle, PreparedStatement statement) throws SQLException
	{
		statement.setInt(1, pedido.getNroPedido());
		statement.setString(2, detalle.getProducto().getCodigo());
		statement.setInt(3, detalle.getCantidad());
	}

	private static void MapearStatementSelect(int nroPedido, PreparedStatement statement) throws SQLException {
		statement.setInt(1, nroPedido);
	}

	private static void MapearEntidad(PedidoProveedor pedido, ResultSet rs) throws SQLException{
		Proveedor proveedor = ProveedorMapper.getInstancia().Cargar(rs.getInt("Cuit"));

		pedido.setNroPedido(rs.getInt("NroPedido"));
		pedido.setProveedor(proveedor);
		pedido.setEstado(rs.getInt("Estado"));
		pedido.setFechaPedido(rs.getDate("FechaInicio"));
		pedido.setFechaEntrega(rs.getDate("FechaEntrega"));
	}

	private static void MapearEntidad(DetallePedidoProveedor detalle, ResultSet rs) throws SQLException{
		Producto producto = ProductoMapper.getInstancia().Cargar(rs.getString("IdProducto"));
		
		detalle.setCantidad(rs.getInt("cantidad"));
		detalle.setProducto(producto);
	}
}
