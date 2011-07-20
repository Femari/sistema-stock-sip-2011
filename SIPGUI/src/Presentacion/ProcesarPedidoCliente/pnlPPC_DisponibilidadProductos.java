/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * pnlPPC_DisponibilidadProductos.java
 *
 * Created on 16/07/2011, 00:44:29
 */
package Presentacion.ProcesarPedidoCliente;

import Negocio.Modelo.DetalleDisponibilidadProducto;
import Negocio.Modelo.DetallePedidoCliente;
import Negocio.Modelo.Producto;
import Negocio.ProcesarPedidoCliente;
import Presentacion.SIPGUIView;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NicolasM
 */
public class pnlPPC_DisponibilidadProductos extends javax.swing.JPanel {
    
    private int nroPaso;
    private pnlProcesarPedidoCliente parent;
    private ProcesarPedidoCliente proceso;
    private DefaultTableModel tableModel;

    /** Creates new form pnlPPC_DisponibilidadProductos */
    public pnlPPC_DisponibilidadProductos(pnlProcesarPedidoCliente parent, ProcesarPedidoCliente proceso, int nroPaso) {
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Producto", "Cantidad", "Stock deposito", "Stock pedidos", "Disponibilidad", "Disponibilidad faltante"
                }) {
            
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        
        initComponents();
        
        tblDetalleDisponibilidadActual.setDefaultRenderer(Object.class, new ColoreaCeldas());
        
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(0).setPreferredWidth(120); //producto
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(1).setPreferredWidth(30); //cantidad
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(2).setPreferredWidth(30); //stock deposito
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(3).setPreferredWidth(30); //stock pedidos
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(4).setPreferredWidth(200); //disponibilidad
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(5).setPreferredWidth(200); //disponibilidad faltante

        this.proceso = proceso;
        this.parent = parent;
        this.nroPaso = nroPaso;
        
    }
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        
        if (visible) {
            CargarGrilla();
            lblFechaDisp1.setText(new SimpleDateFormat("dd-MM-yyyy").format(proceso.calcularFechaMinimaDisponibilidad()));
            SIPGUIView.getInstance().SeleccionarNumeroPaso(nroPaso);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblFechaDisp1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleDisponibilidadActual = new javax.swing.JTable();
        btnEliminarItem = new javax.swing.JButton();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Presentacion.SIPGUIApp.class).getContext().getResourceMap(pnlPPC_DisponibilidadProductos.class);
        btnSiguiente.setText(resourceMap.getString("btnSiguiente.text")); // NOI18N
        btnSiguiente.setName("btnSiguiente"); // NOI18N
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAnterior.setText(resourceMap.getString("btnAnterior.text")); // NOI18N
        btnAnterior.setName("btnAnterior"); // NOI18N
        btnAnterior.setOpaque(false);
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        lblFechaDisp1.setFont(resourceMap.getFont("lblFechaDisp1.font")); // NOI18N
        lblFechaDisp1.setText(resourceMap.getString("lblFechaDisp1.text")); // NOI18N
        lblFechaDisp1.setName("lblFechaDisp1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblDetalleDisponibilidadActual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblDetalleDisponibilidadActual.setModel(tableModel);
        tblDetalleDisponibilidadActual.setName("tblDetalleDisponibilidadActual"); // NOI18N
        jScrollPane1.setViewportView(tblDetalleDisponibilidadActual);

        btnEliminarItem.setText(resourceMap.getString("btnEliminarItem.text")); // NOI18N
        btnEliminarItem.setName("btnEliminarItem"); // NOI18N
        btnEliminarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 458, Short.MAX_VALUE)
                        .addComponent(btnSiguiente))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaDisp1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
                        .addComponent(btnEliminarItem)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarItem)
                    .addComponent(jLabel1)
                    .addComponent(lblFechaDisp1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior)
                    .addComponent(btnSiguiente))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        this.SiguientePaso();
}//GEN-LAST:event_btnSiguienteActionPerformed
    
    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        this.PasoAnterior();
    }//GEN-LAST:event_btnAnteriorActionPerformed
    
    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemActionPerformed
//        if (lstProductosAgregados.getSelectedIndex() == -1) {
//            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la lista primero.", "Procesar pedido cliente", JOptionPane.WARNING_MESSAGE);
//        } else {
//            ProductoCantidad prodcant = (ProductoCantidad)listModel.remove(lstProductosAgregados.getSelectedIndex());
//            proceso.BorrarDetallePedidoCliente(prodcant.getProducto());
//            cboProductos.addItem(new ComboProductosItem(prodcant.getProducto()));
//        }
        if (tblDetalleDisponibilidadActual.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la lista primero.", "Procesar pedido cliente", JOptionPane.WARNING_MESSAGE);
        } else {
            Producto prod = (Producto) tableModel.getValueAt(tblDetalleDisponibilidadActual.getSelectedRow(), 0);
            tableModel.removeRow(tblDetalleDisponibilidadActual.getSelectedRow());
            proceso.BorrarDetallePedidoCliente(prod);
            lblFechaDisp1.setText(new SimpleDateFormat("dd-MM-yyyy").format(proceso.calcularFechaMinimaDisponibilidad()));
        }
}//GEN-LAST:event_btnEliminarItemActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFechaDisp1;
    private javax.swing.JTable tblDetalleDisponibilidadActual;
    // End of variables declaration//GEN-END:variables

    private void SiguientePaso() {
        proceso.ComprometerStock();
        String error = proceso.GrabarPedido();
        if (!error.equals("")) {
            JOptionPane.showMessageDialog(this, error, "Procesar pedido cliente", JOptionPane.WARNING_MESSAGE);
            System.out.println(error);
        } else {
            JOptionPane.showMessageDialog(this, "Pedido guardado correctamente.", "Procesar pedido cliente", JOptionPane.WARNING_MESSAGE);
            System.out.println("Pedido guardado correctamente");
            parent.SiguientePaso(nroPaso);
        }
    }
    
    private void PasoAnterior() {
        parent.PasoAnterior(nroPaso);
    }
    
    public void CargarGrilla() {
        tableModel.setRowCount(0);
        ArrayList<DetalleDisponibilidadProducto> arrayDetalleDisponibilidad = proceso.VerificarDisponibilidadProductos();
        //Collections.sort(arrayDetalleDisponibilidad, new OrdenadorLista());
        for (DetallePedidoCliente sDetallePedidoCliente : proceso.getPedidoCliente().getArrayDetallePedido()) {
            tableModel.addRow(ArmarFila(sDetallePedidoCliente, BuscarDetalleDisponibilidadPorProducto(sDetallePedidoCliente.getProducto(), arrayDetalleDisponibilidad)));
        }
        
    }
    
    private Object[] ArmarFila(DetallePedidoCliente xDetallePedidoCliente, DetalleDisponibilidadProducto xDetalleDisponibilidadProducto) {
        Producto colProducto = xDetallePedidoCliente.getProducto();
        int colCantidad = xDetallePedidoCliente.getCantidad();
        int colStockDeposito = proceso.ObtenerStockLibreDeposito(xDetallePedidoCliente.getProducto());
        int colStockPedidos = 0;
        //DetalleDisponibilidadProducto sDetalleDisponibilidadProducto = BuscarDetalleDisponibilidadPorProducto(xDetallePedidoCliente.getProducto(), xArrayDetalleDisponibilidad);
        if (colStockDeposito < colCantidad) {
            colStockPedidos = xDetalleDisponibilidadProducto.getCantidadDisponible();
        }
        String colDisponibilidad = "";
        if (proceso.getPedidoCliente().getPrioridad().getNivel() == 0) {
            colDisponibilidad = xDetalleDisponibilidadProducto.getDescripcionEstado();
        } else {
            colDisponibilidad = xDetalleDisponibilidadProducto.getDescripcionEstado(proceso.getPedidoCliente().getPrioridad().calcularFechaEsperadaDeEntrega(new Date()));
        }
        String colDisponibilidadFaltante = xDetalleDisponibilidadProducto.getDisponibilidadFaltante();
        
        
        return new Object[]{colProducto, colCantidad, colStockDeposito, colStockPedidos, colDisponibilidad, colDisponibilidadFaltante};
    }
    
    private DetalleDisponibilidadProducto BuscarDetalleDisponibilidadPorProducto(Producto producto, ArrayList<DetalleDisponibilidadProducto> xArrayDetalleDisponibilidad) {
        for (DetalleDisponibilidadProducto detalle : xArrayDetalleDisponibilidad) {
            if (detalle.getProducto().equals(producto)) {
                return detalle;
            }
        }
        return null;
    }

    /*
    private class OrdenadorLista implements Comparator<DetalleDisponibilidadProducto>{
    
    public int compare(DetalleDisponibilidadProducto o1, DetalleDisponibilidadProducto o2) {
    return  o1.getFecha().compareTo(o1.getFecha());
    }
    
    }
     */
    public class ColoreaCeldas extends DefaultTableCellRenderer {
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            
            String s = table.getModel().getValueAt(row, col).toString();
            
            if (s.startsWith("No disponible")) {
                setBackground(new Color(255, 150, 150));
            } else if (s.startsWith("Stock")) {
                if (table.getModel().getValueAt(row, col - 1).toString().equalsIgnoreCase("0")) {
                    setBackground(new Color(150, 255, 150));
                } else {
                    setBackground(new Color(255, 255, 150));
                }
            } else {
                setBackground(null);
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        }
    }
}
