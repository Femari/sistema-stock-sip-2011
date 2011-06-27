package Persistencia;

import java.sql.*;

import Negocio.Modelo.Proveedor;

public class ProveedorMapper {
	private static ProveedorMapper instanciaProveedorMapper;
	
	public static ProveedorMapper getInstancia(){
		if (instanciaProveedorMapper == null){
			instanciaProveedorMapper = new ProveedorMapper();
		}
		return instanciaProveedorMapper;
	}
	
	private ProveedorMapper(){
		
	}
	
	public Proveedor Cargar(int cuit){
		try {
        	
        	PreparedStatement selectCliente;
            String sqlString = "SELECT * FROM Proveedor WHERE cuit = ?";
			selectCliente = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);
			
			MapearSelectPreparedStatement(cuit, selectCliente);
			Proveedor proveedor = new Proveedor();
			MapearEntidad(proveedor, selectCliente.executeQuery());
			return proveedor;
		
        } catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void Modificar(Proveedor proveedor){
        try {
        	
        	PreparedStatement updateProveedor;
            String sqlString = "UPDATE Proveedor SET nombre = ? , direccion = ? , codigoPostal = ? , telefono = ? , fax = ? WHERE cuit = ?";
			updateProveedor = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);
			
			MapearUpdatePreparedStatement( proveedor, updateProveedor);
		
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void MapearEntidad(Proveedor proveedor, ResultSet rs){
		try {
			rs.next();
			proveedor.setCuit(rs.getInt("cuit"));
			proveedor.setDireccion(rs.getString("direccion"));
			proveedor.setRazonSocial(rs.getString("nombre"));
			proveedor.setCodigoPostal(rs.getString("codigoPostal"));
			proveedor.setTelefono(rs.getInt("telefono"));
			proveedor.setFax(rs.getInt("fax"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void MapearSelectPreparedStatement(int cuit, PreparedStatement preparedStatement) {
        try {
        	preparedStatement.setInt(1, cuit);
        }catch(SQLException ex) {
            System.err.println("UsePreparedStatement: " + ex.getMessage());
        }
    }
	
	static void MapearUpdatePreparedStatement(Proveedor proveedor, PreparedStatement preparedStatement) {
        try {
        	preparedStatement.setString(1, proveedor.getRazonSocial());
        	preparedStatement.setString(2, proveedor.getDireccion());
        	preparedStatement.setString(3, proveedor.getCodigoPostal());
        	preparedStatement.setInt(4, proveedor.getTelefono());
        	preparedStatement.setInt(5, proveedor.getFax());
        	
        	preparedStatement.executeUpdate();
            
        }catch(SQLException ex) {
            System.err.println("UsePreparedStatement: " + ex.getMessage());
       }
    }

	/******* Validaciones *******************/
	
	
	public boolean existeProveedor(int cuit){
		try {
        	
        	PreparedStatement selectProveedor;
            String sqlString = "SELECT count(*) FROM Proveedor WHERE cuit = ?";
			selectProveedor = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);
			
			MapearSelectPreparedStatement(cuit, selectProveedor);
			
			ResultSet rs;
			rs = selectProveedor.executeQuery();
			rs.next();
			return rs.getInt(1) > 0;
		
        } catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
