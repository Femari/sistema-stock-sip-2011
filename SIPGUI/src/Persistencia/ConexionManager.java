package Persistencia;
import java.sql.*;

public class ConexionManager {
    //Acceso Singleton
    private Connection conexion;
    private static ConexionManager instanciaConexionManager;

    private void CrearConexion() throws Exception{
        //Creacion de una conexion con la clase DriverManager
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://localhost\\SqlExpress;database=SIPbd;user=sa;password=sa2008";
        conexion = DriverManager.getConnection(connectionUrl);
    }

    public static ConexionManager getInstancia(){
        if (instanciaConexionManager == null){
            instanciaConexionManager = new ConexionManager();
        }
        return instanciaConexionManager;
    }

    public Connection getConexion(){
        return conexion;
    }

    private ConexionManager(){
        try {
            CrearConexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void beginTransaction() throws SQLException{
        conexion.setAutoCommit(false);
    }

    public void rollBackTransaction(){
        try {
            conexion.rollback();
            conexion.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commitTransaction() throws SQLException{
        conexion.commit();
        conexion.setAutoCommit(true);
    }
}
