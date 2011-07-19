package Persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Negocio.Modelo.DetallePedidoCliente;
import Negocio.Modelo.PedidoCliente;
import Negocio.Modelo.StockComprometidoDetallePedidoCliente;

public class PedidoClienteMapper {

    private static PedidoClienteMapper instanciaProductoMapper;

    public static PedidoClienteMapper getInstancia() {
        if (instanciaProductoMapper == null) {
            instanciaProductoMapper = new PedidoClienteMapper();
        }
        return instanciaProductoMapper;
    }

    private PedidoClienteMapper() {
    }

    public void Grabar(PedidoCliente pedido) {
        String query = "INSERT INTO PedidoCliente(Cuit, Estado, FechaInicio, FechaEntrega, NroRemito, Prioridad) VALUES (?,?,?,?,?,?)";
        String queryDetalle = "INSERT INTO DetallePedidoCliente(NroPedido, IdProducto, Cantidad, Precio) VALUES (?,?,?,?)";
        String queryStock = "INSERT INTO StockComprometidoDetallePedidoCliente(NroPedido, IdProducto, NroPedidoProveedor, CantidadComprometida, TipoCompromiso) VALUES (?,?,?,?,?)";

        PreparedStatement statement;

        try {
            ConexionManager.getInstancia().beginTransaction();

            statement = ConexionManager.getInstancia().getConexion().prepareStatement(query);
            MapearStatementInsert(pedido, statement);
            statement.execute();

            statement = ConexionManager.getInstancia().getConexion().prepareStatement("SELECT @@IDENTITY NroPedido");
            ResultSet rs = statement.executeQuery();
            rs.next();
            pedido.setNroPedido(rs.getInt("NroPedido"));
            rs.close();
            statement.close();

            statement = ConexionManager.getInstancia().getConexion().prepareStatement(queryDetalle);
            for (DetallePedidoCliente detalle : pedido.getArrayDetallePedido()) {
                MapearStatementInsert(pedido, detalle, statement);
                statement.execute();
            }

            statement = ConexionManager.getInstancia().getConexion().prepareStatement(queryStock);
            for (DetallePedidoCliente detalle : pedido.getArrayDetallePedido()) {
                for (StockComprometidoDetallePedidoCliente comprometido : detalle.getStockComprometido()) {
                    MapearStatementInsert(pedido, detalle, comprometido, statement);
                    statement.execute();
                }
            }

            statement.close();
            ConexionManager.getInstancia().commitTransaction();

        } catch (SQLException e) {
            e.printStackTrace();
            ConexionManager.getInstancia().rollBackTransaction();
        }
    }

    private static void MapearStatementInsert(PedidoCliente pedido, PreparedStatement statement) throws SQLException {
        statement.setLong(1, pedido.getCliente().getCuit());
        statement.setInt(2, pedido.getEstado());
        statement.setDate(3, new Date(pedido.getFechaPedido().getTime()));
        statement.setDate(4, new Date(pedido.getFechaEntrega().getTime()));
        statement.setInt(5, pedido.getNroRemito());
        statement.setInt(6, pedido.getPrioridad().getNivel());
    }

    private static void MapearStatementInsert(PedidoCliente pedido, DetallePedidoCliente detalle, PreparedStatement statement) throws SQLException {
        statement.setInt(1, pedido.getNroPedido());
        statement.setString(2, detalle.getProducto().getCodigo());
        statement.setInt(3, detalle.getCantidad());
        statement.setDouble(4, detalle.getPrecioUnitario());
    }

    private static void MapearStatementInsert(PedidoCliente pedido, DetallePedidoCliente detalle, StockComprometidoDetallePedidoCliente comprometido, PreparedStatement statement) throws SQLException {
        statement.setInt(1, pedido.getNroPedido());
        statement.setString(2, detalle.getProducto().getCodigo());
        if (comprometido.getPedidoProveedor() != null) {
            statement.setInt(3, comprometido.getPedidoProveedor().getNroPedido());
        } else {
            statement.setString(3, null);
        }
        statement.setInt(4, comprometido.getCantidad());
        statement.setInt(5, comprometido.getTipoCompromiso());
    }
}
