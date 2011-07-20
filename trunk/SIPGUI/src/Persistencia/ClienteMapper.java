package Persistencia;

import java.sql.*;

import Negocio.Modelo.Cliente;
import java.util.ArrayList;

public class ClienteMapper {

    private static ClienteMapper instanciaClienteMapper;

    public static ClienteMapper getInstancia() {
        if (instanciaClienteMapper == null) {
            instanciaClienteMapper = new ClienteMapper();
        }
        return instanciaClienteMapper;
    }

    private ClienteMapper() {
    }

    public Cliente Cargar(long cuit) {
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

    public void Modificar(Cliente cliente) {
        try {

            PreparedStatement updateCliente;
            String sqlString = "UPDATE Cliente SET nombre = ? , direccion = ? , codigoPostal = ? , telefono = ? , fax = ? WHERE cuit = ?";
            System.out.println(sqlString);
            updateCliente = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);
            MapearUpdatePreparedStatement(cliente, updateCliente);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Agregar(Cliente cliente) {
        try {
            PreparedStatement insertCliente;
            String sqlString = "INSERT INTO Cliente values (?,?,?,?,?,?,?)";
            System.out.println(sqlString);
            insertCliente = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);
            MapearInsertPreparedStatement(cliente, insertCliente);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean MapearEntidad(Cliente cliente, ResultSet rs) {
        try {
            if (rs.next()) {
                cliente.setCuit(rs.getLong("cuit"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setCodigoPostal(rs.getString("codigoPostal"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setFax(rs.getString("fax"));
                cliente.setHabilitado(rs.getBoolean("Habilitado"));

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    static void MapearSelectPreparedStatement(long cuit, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, Long.toString(cuit));
        } catch (SQLException ex) {
            System.err.println("UsePreparedStatement: " + ex.getMessage());
        }
    }

    static void MapearUpdatePreparedStatement(Cliente cliente, PreparedStatement preparedStatement) {
        try {
            
            preparedStatement.setLong(1, cliente.getCuit());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setString(4, cliente.getCodigoPostal());
            preparedStatement.setString(5, cliente.getTelefono());
            preparedStatement.setString(6, cliente.getFax());
            preparedStatement.setBoolean(7, cliente.getHabilitado());
            preparedStatement.executeUpdate();


        } catch (SQLException ex) {
            System.err.println("UsePreparedStatement: " + ex.getMessage());
        }
    }

    static void MapearInsertPreparedStatement(Cliente cliente, PreparedStatement preparedStatement) {
        try {
            
            preparedStatement.setLong(1, cliente.getCuit());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setString(4, cliente.getCodigoPostal());
            preparedStatement.setString(5, cliente.getTelefono());
            preparedStatement.setString(6, cliente.getFax());
            preparedStatement.setBoolean(7, cliente.getHabilitado());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("UsePreparedStatement: " + ex.getMessage());
        }
    }

    /******* Validaciones *******************/
    public boolean existeCliente(long cuit) {
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
            String sqlString = "SELECT * FROM Cliente ORDER BY Nombre";
            selectCliente = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);
            Cliente cliente = new Cliente();
            ResultSet rs = selectCliente.executeQuery();
            while (MapearEntidad(cliente, rs)) {
                clientes.add(cliente);
                cliente = new Cliente();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public ArrayList<Cliente> CargarTodos(Cliente cli) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        try {
            PreparedStatement selectCliente;
            String sqlString;
            sqlString = "SELECT * FROM Cliente WHERE ";
            if (cli.getCuit() != 0) {
                sqlString += "Cuit=" + cli.getCuit();
            }
            if (cli.getCuit() != 0 && !cli.getNombre().isEmpty()) {
                sqlString += " AND ";
            }
            if (!cli.getNombre().isEmpty()) {
                sqlString += "Nombre LIKE '%" + cli.getNombre() + "%'";
            }
            if ((cli.getCuit() != 0 || !cli.getNombre().isEmpty()) && !cli.getDireccion().isEmpty()) {
                sqlString += " AND ";
            }
            if (!cli.getDireccion().isEmpty()) {
                sqlString += " Direccion LIKE '%" + cli.getDireccion() + "%'";
            }
            if ((cli.getCuit() != 0 || !cli.getNombre().isEmpty() || !cli.getDireccion().isEmpty())
                    && !cli.getCodigoPostal().isEmpty()) {
                sqlString += " AND ";
            }
            if (!cli.getCodigoPostal().isEmpty()) {
                sqlString += " CodigoPostal LIKE '%" + cli.getCodigoPostal() + "%'";
            }
            if ((cli.getCuit() != 0 || !cli.getNombre().isEmpty() || !cli.getDireccion().isEmpty() || !cli.getCodigoPostal().isEmpty())
                    && !cli.getTelefono().isEmpty()) {
                sqlString += " AND ";
            }
            if (!cli.getTelefono().isEmpty()) {
                sqlString += " Telefono=" + cli.getTelefono();
            }
            if ((cli.getCuit() != 0 || !cli.getNombre().isEmpty() || !cli.getDireccion().isEmpty() || !cli.getCodigoPostal().isEmpty() || !cli.getTelefono().isEmpty())
                    && !cli.getFax().isEmpty()) {
                sqlString += " AND ";
            }
            if (!cli.getFax().isEmpty()) {
                sqlString += " Fax=" + cli.getFax();
            }
            System.out.println("Cargar con filtro:" + sqlString);
            selectCliente = ConexionManager.getInstancia().getConexion().prepareStatement(sqlString);
            Cliente cliente = new Cliente();
            ResultSet rs = selectCliente.executeQuery();
            while (MapearEntidad(cliente, rs)) {
                clientes.add(cliente);
                cliente = new Cliente();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
