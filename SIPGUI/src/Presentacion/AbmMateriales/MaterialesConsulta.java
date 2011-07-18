/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MaterialesConsulta.java
 *
 * Created on 15-jul-2011, 23:02:44
 */
package Presentacion.AbmMateriales;

import Negocio.Modelo.Producto;
import Negocio.ProcesarProductos;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dan
 */
public class MaterialesConsulta extends javax.swing.JPanel {

    /** Creates new form MaterialesConsulta */
    private DefaultTableModel dtm;
    private String[] columnas = {"codigo", "Nombre", "Descripcion", "Precio Compra", "Stock Minimo", "Proveedor"};

    public MaterialesConsulta() {
        dtm = new DefaultTableModel(columnas, 0);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaterialCodigo = new javax.swing.JTextField();
        txtMaterialNombre = new javax.swing.JTextField();
        btnMaterialBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBusquedaMaterial = new javax.swing.JTable();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Presentacion.SIPGUIApp.class).getContext().getResourceMap(MaterialesConsulta.class);
        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtMaterialCodigo.setText(resourceMap.getString("txtMaterialCodigo.text")); // NOI18N
        txtMaterialCodigo.setName("txtMaterialCodigo"); // NOI18N

        txtMaterialNombre.setText(resourceMap.getString("txtMaterialNombre.text")); // NOI18N
        txtMaterialNombre.setName("txtMaterialNombre"); // NOI18N

        btnMaterialBuscar.setText(resourceMap.getString("btnMaterialBuscar.text")); // NOI18N
        btnMaterialBuscar.setName("btnMaterialBuscar"); // NOI18N
        btnMaterialBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaterialBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(3, 3, 3)
                        .addComponent(txtMaterialCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaterialNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
                    .addComponent(btnMaterialBuscar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaterialCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaterialNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMaterialBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setName("tblBusquedaMaterial"); // NOI18N

        tblBusquedaMaterial.setModel(this.dtm);
        tblBusquedaMaterial.setName("jscroll"); // NOI18N
        jScrollPane1.setViewportView(tblBusquedaMaterial);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setCompletarTabla(String codigo, String nombre) {

        ArrayList<Producto> productos = new ProcesarProductos().getProductos(codigo, nombre);
        Iterator<Producto> itrProd = productos.iterator();
        //Limpio la tabla
        this.limpiarTabla();
        //Agrego los productos encontrados a la tabla
        while (itrProd.hasNext()) {
            Producto producto = itrProd.next();
            dtm.addRow(new Object[]{producto.getCodigo(),
                        producto.getNombre(),
                        producto.getDescripcion(),
                        "$" + producto.getPrecioCompra(),
                        producto.getStockMinimo(),
                        producto.getProveedor().getRazonSocial()
                    });
        }
    }

    public void limpiarTabla() {
        dtm.setRowCount(0);
    }
    private void btnMaterialBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaterialBuscarActionPerformed
        if (txtMaterialCodigo.getText().isEmpty() && txtMaterialNombre.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Debe ingresar al menos una opcion", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            this.setCompletarTabla(txtMaterialCodigo.getText(), txtMaterialNombre.getText());
        }
    }//GEN-LAST:event_btnMaterialBuscarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMaterialBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBusquedaMaterial;
    private javax.swing.JTextField txtMaterialCodigo;
    private javax.swing.JTextField txtMaterialNombre;
    // End of variables declaration//GEN-END:variables
}