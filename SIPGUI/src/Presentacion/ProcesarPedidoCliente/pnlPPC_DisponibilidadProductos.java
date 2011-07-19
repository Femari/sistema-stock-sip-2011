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
    private DefaultTableModel tableModel2;

    /** Creates new form pnlPPC_DisponibilidadProductos */
    public pnlPPC_DisponibilidadProductos(pnlProcesarPedidoCliente parent, ProcesarPedidoCliente proceso, int nroPaso) {
         tableModel = new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Cantidad", "Stock deposito", "Stock pedidos", "Disponibilidad", "Disponibilidad faltante" 
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
         
        tableModel2 = new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Cantidad", "Stock deposito", "Stock pedidos", "Disponibilidad", "Disponibilidad faltante" 
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        initComponents();
        
        tblDetalleDisponibilidadActual.setDefaultRenderer( Object.class, new ColoreaCeldas());
        tblDetalleDisponibilidadFutura.setDefaultRenderer( Object.class, new ColoreaCeldas());
        
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(0).setPreferredWidth(120); //producto
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(1).setPreferredWidth(30); //cantidad
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(2).setPreferredWidth(30); //stock deposito
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(3).setPreferredWidth(30); //stock pedidos
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(4).setPreferredWidth(200); //disponibilidad
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(5).setPreferredWidth(200); //disponibilidad faltante
        
        tblDetalleDisponibilidadFutura.getColumnModel().getColumn(0).setPreferredWidth(120); //producto
        tblDetalleDisponibilidadFutura.getColumnModel().getColumn(1).setPreferredWidth(30); //cantidad
        tblDetalleDisponibilidadFutura.getColumnModel().getColumn(2).setPreferredWidth(30); //stock deposito
        tblDetalleDisponibilidadFutura.getColumnModel().getColumn(3).setPreferredWidth(30); //stock pedidos
        tblDetalleDisponibilidadFutura.getColumnModel().getColumn(4).setPreferredWidth(200); //disponibilidad
        tblDetalleDisponibilidadActual.getColumnModel().getColumn(5).setPreferredWidth(200); //disponibilidad faltante
        
        this.proceso = proceso;
        this.parent = parent;
        this.nroPaso = nroPaso;
        
    }

    @Override
    public void setVisible(boolean visible){
        super.setVisible(visible);
        
        if(visible){
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleDisponibilidadActual = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblFechaDisp1 = new javax.swing.JLabel();
        btnPasarAPedidoFuturo = new javax.swing.JButton();
        btnEliminarItem = new javax.swing.JButton();
        btnDividirPorDisponibilidad = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalleDisponibilidadFutura = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblFechaDisp2 = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Presentacion.SIPGUIApp.class).getContext().getResourceMap(pnlPPC_DisponibilidadProductos.class);
        btnSiguiente.setText(resourceMap.getString("btnSiguiente.text")); // NOI18N
        btnSiguiente.setName("btnSiguiente"); // NOI18N
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblDetalleDisponibilidadActual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblDetalleDisponibilidadActual.setModel(tableModel);
        tblDetalleDisponibilidadActual.setName("tblDetalleDisponibilidadActual"); // NOI18N
        jScrollPane1.setViewportView(tblDetalleDisponibilidadActual);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        lblFechaDisp1.setFont(resourceMap.getFont("lblFechaDisp1.font")); // NOI18N
        lblFechaDisp1.setText(resourceMap.getString("lblFechaDisp1.text")); // NOI18N
        lblFechaDisp1.setName("lblFechaDisp1"); // NOI18N

        btnPasarAPedidoFuturo.setText(resourceMap.getString("btnPasarAPedidoFuturo.text")); // NOI18N
        btnPasarAPedidoFuturo.setName("btnPasarAPedidoFuturo"); // NOI18N
        btnPasarAPedidoFuturo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasarAPedidoFuturoActionPerformed(evt);
            }
        });

        btnEliminarItem.setText(resourceMap.getString("btnEliminarItem.text")); // NOI18N
        btnEliminarItem.setName("btnEliminarItem"); // NOI18N
        btnEliminarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarItemActionPerformed(evt);
            }
        });

        btnDividirPorDisponibilidad.setText(resourceMap.getString("btnDividirPorDisponibilidad.text")); // NOI18N
        btnDividirPorDisponibilidad.setName("btnDividirPorDisponibilidad"); // NOI18N
        btnDividirPorDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDividirPorDisponibilidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaDisp1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                        .addComponent(btnDividirPorDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPasarAPedidoFuturo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarItem)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPasarAPedidoFuturo)
                            .addComponent(btnEliminarItem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblFechaDisp1)))
                    .addComponent(btnDividirPorDisponibilidad))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tblDetalleDisponibilidadFutura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblDetalleDisponibilidadFutura.setModel(tableModel2);
        tblDetalleDisponibilidadFutura.setName("tblDetalleDisponibilidadFutura"); // NOI18N
        jScrollPane2.setViewportView(tblDetalleDisponibilidadFutura);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        lblFechaDisp2.setText(resourceMap.getString("lblFechaDisp2.text")); // NOI18N
        lblFechaDisp2.setName("lblFechaDisp2"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaDisp2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblFechaDisp2))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(635, Short.MAX_VALUE)
                        .addComponent(btnSiguiente))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSiguiente)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        this.SiguientePaso();
}//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemActionPerformed
        lblFechaDisp1.setText(new SimpleDateFormat("dd-MM-yyyy").format(proceso.calcularFechaMinimaDisponibilidad()));
    }//GEN-LAST:event_btnEliminarItemActionPerformed

    private void btnPasarAPedidoFuturoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasarAPedidoFuturoActionPerformed
        lblFechaDisp1.setText(new SimpleDateFormat("dd-MM-yyyy").format(proceso.calcularFechaMinimaDisponibilidad()));
    }//GEN-LAST:event_btnPasarAPedidoFuturoActionPerformed

    private void btnDividirPorDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDividirPorDisponibilidadActionPerformed
        lblFechaDisp1.setText(new SimpleDateFormat("dd-MM-yyyy").format(proceso.calcularFechaMinimaDisponibilidad()));
    }//GEN-LAST:event_btnDividirPorDisponibilidadActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDividirPorDisponibilidad;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnPasarAPedidoFuturo;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblFechaDisp1;
    private javax.swing.JLabel lblFechaDisp2;
    private javax.swing.JTable tblDetalleDisponibilidadActual;
    private javax.swing.JTable tblDetalleDisponibilidadFutura;
    // End of variables declaration//GEN-END:variables

    private void SiguientePaso(){
        proceso.ComprometerStock();
        String error = proceso.GrabarPedido();
        if (!error.equals("")){
            System.out.println(error);
        }
        parent.SiguientePaso(nroPaso);
        
    }

    public void CargarGrilla() {
        ArrayList<DetalleDisponibilidadProducto> arrayDetalleDisponibilidad = proceso.VerificarDisponibilidadProductos();
        //Collections.sort(arrayDetalleDisponibilidad, new OrdenadorLista());
        for (DetallePedidoCliente sDetallePedidoCliente: proceso.getPedidoCliente().getArrayDetallePedido()){
            
            tableModel.addRow(ArmarFila(sDetallePedidoCliente, BuscarDetalleDisponibilidadPorProducto(sDetallePedidoCliente.getProducto(), arrayDetalleDisponibilidad)));
        }
        
    }
    
    private Object[] ArmarFila(DetallePedidoCliente xDetallePedidoCliente, DetalleDisponibilidadProducto xDetalleDisponibilidadProducto){
        String colProducto = xDetallePedidoCliente.getProducto().getNombre();
        int colCantidad = xDetallePedidoCliente.getCantidad();
        int colStockDeposito = proceso.ObtenerStockLibreDeposito(xDetallePedidoCliente.getProducto());
        int colStockPedidos = 0;
        //DetalleDisponibilidadProducto sDetalleDisponibilidadProducto = BuscarDetalleDisponibilidadPorProducto(xDetallePedidoCliente.getProducto(), xArrayDetalleDisponibilidad);
        if (colStockDeposito < colCantidad){
            colStockPedidos = xDetalleDisponibilidadProducto.getCantidadDisponible();
        }
        String colDisponibilidad = "";
        if (proceso.getPedidoCliente().getPrioridad().getNivel() == 0){
            colDisponibilidad = xDetalleDisponibilidadProducto.getDescripcionEstado();
        }
        else{
            colDisponibilidad = xDetalleDisponibilidadProducto.getDescripcionEstado(proceso.getPedidoCliente().getPrioridad().calcularFechaEsperadaDeEntrega(new Date()));
        }
        String colDisponibilidadFaltante = xDetalleDisponibilidadProducto.getDisponibilidadFaltante();
        
        
        return new Object[]{colProducto, colCantidad, colStockDeposito, colStockPedidos, colDisponibilidad, colDisponibilidadFaltante};
    }
    
    private DetalleDisponibilidadProducto BuscarDetalleDisponibilidadPorProducto(Producto producto, ArrayList<DetalleDisponibilidadProducto> xArrayDetalleDisponibilidad){
        for(DetalleDisponibilidadProducto detalle : xArrayDetalleDisponibilidad)
        {
            if(detalle.getProducto().equals(producto))
                return detalle;
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
    
    public class ColoreaCeldas extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row,int col){

            String s = table.getModel().getValueAt(row,col).toString();

            if(s.startsWith("No disponible")) {
                 setBackground(new Color(255, 150, 150));
            }
            else if ( s.startsWith("Stock")){
                if (table.getModel().getValueAt(row, col - 1).toString().equalsIgnoreCase("0")){
                    setBackground(new Color(150, 255, 150));
                }
                else {
                    setBackground(new Color(255, 255, 150));
                }
            }
            else {
                setBackground(null);
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        }
   }
   
}