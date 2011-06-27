package Persistencia;

import java.sql.*;

import Negocio.Modelo.Cliente;
import java.util.ArrayList;

public class ClienteMapper {
	private static ClienteMapper instanciaClienteMapper;
	
	public static ClienteMapper getInstancia(){
		if (instanciaClienteMapper == null){
			instanciaClienteMapper = new ClienteMapper();
		}
		return instanciaClienteMapper;
	}
	
	private ClienteMapper(){
		
	}
	
	public Cliente Cargar(String cuit){
		try {
        	
        	PreparedStatement selectCliente;
            String sqlString = "SELECT * FROM Cliente WHERE cuit = ?";
			selectCliente = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);
			
			MapearSelectPreparedStatement(cuit, selectCliente);
			Cliente cliente = new Cliente();
			MapearEntidad(cliente, selectCliente.executeQuery());
			return cliente;
		
        } catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void Modificar(Cliente cliente){
        try {
        	
        	PreparedStatement updateCliente;
            String sqlString = "UPDATE Cliente SET nombre = ? , direccion = ? , codigoPostal = ? , telefono = ? , fax = ? WHERE cuit = ?";
			updateCliente = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);
			
			MapearUpdatePreparedStatement( cliente, updateCliente);
		
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean MapearEntidad(Cliente cliente, ResultSet rs){
            try {
                if(rs.next()){
                    cliente.setCuit(rs.getInt("cuit"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setCodigoPostal(rs.getString("codigoPostal"));
                    cliente.setTelefono(rs.getInt("telefono"));
                    cliente.setFax(rs.getInt("fax"));
                    
                    return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;
	}
	
	static void MapearSelectPreparedStatement(String cuit, PreparedStatement preparedStatement) {
        try {
        	preparedStatement.setString(1, cuit);
        }catch(SQLException ex) {
            System.err.println("UsePreparedStatement: " + ex.getMessage());
        }
    }
	
	static void MapearUpdatePreparedStatement(Cliente cliente, PreparedStatement preparedStatement) {
        try {
        	preparedStatement.setString(1, cliente.getNombre());
        	preparedStatement.setString(2, cliente.getDireccion());
        	preparedStatement.setString(3, cliente.getCodigoPostal());
        	preparedStatement.setInt(4, cliente.getTelefono());
        	preparedStatement.setInt(5, cliente.getFax());
        	
        	preparedStatement.executeUpdate();
            
        }catch(SQLException ex) {
            System.err.println("UsePreparedStatement: " + ex.getMessage());
       }
    }

	/******* Validaciones *******************/
	
	
	public boolean existeCliente(String cuit){
		try {
        	
        	PreparedStatement selectCliente;
            String sqlString = "SELECT count(*) FROM Cliente WHERE cuit = ?";
			selectCliente = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);
			
			MapearSelectPreparedStatement(cuit, selectCliente);
			
			ResultSet rs;
			rs = selectCliente.executeQuery();
			rs.next();
			return rs.getInt(1) > 0;
		
        } catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

    public ArrayList<Cliente> CargarTodos() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        
        try {
            PreparedStatement selectCliente;
            String sqlString = "SELECT * FROM Cliente";
            selectCliente = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);

            Cliente cliente = new Cliente();
            ResultSet rs = selectCliente.executeQuery();
            while(MapearEntidad(cliente, rs)){
                clientes.add(cliente);
                cliente = new Cliente();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clientes;
    }

}
