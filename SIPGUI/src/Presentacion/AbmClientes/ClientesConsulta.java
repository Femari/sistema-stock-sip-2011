/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MaterialesConsulta.java
 *
 * Created on 15-jul-2011, 23:02:44
 */
package Presentacion.AbmClientes;

import Negocio.Modelo.Cliente;
import Negocio.ProcesarClientes;
import Negocio.ProcesarPedidoCliente;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dan
 */
public class ClientesConsulta extends javax.swing.JPanel {

    /** Creates new form MaterialesConsulta */
    private DefaultTableModel dtm;
    private String[] columnas = {"Cuit", "Nombre", "Direccion", "Codigo Postal", "Telefono", "Fax"};
    private Cliente cliente;
    private ArrayList<Cliente> arrayClientesActuales;
    private ArrayList<Cliente> arrayClientesModificados;
    /*Indica la etapa en que esta la operacion
     *0 = Iniciada
     *1 = Para dar de alta
     *2 = 
     */
    private Integer etapa = 0;

    public ClientesConsulta() {
        dtm = new DefaultTableModel(columnas, 0);
        initComponents();
        btnClienteModificar.setEnabled(false);
        btnClienteAlta.setEnabled(false);
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
        txtClienteCuit = new javax.swing.JTextField();
        txtClienteNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtClienteDireccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtClienteCodPost = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtClienteTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtClienteFax = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBusquedaCliente = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        btnClienteBuscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnClienteAlta = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnClienteModificar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnClienteCancelar = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Presentacion.SIPGUIApp.class).getContext().getResourceMap(ClientesConsulta.class);
        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtClienteCuit.setText(resourceMap.getString("txtClienteCuit.text")); // NOI18N
        txtClienteCuit.setName("txtClienteCuit"); // NOI18N

        txtClienteNombre.setText(resourceMap.getString("txtClienteNombre.text")); // NOI18N
        txtClienteNombre.setName("txtClienteNombre"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtClienteDireccion.setName("txtClienteDireccion"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtClienteCodPost.setName("txtClienteCodPost"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtClienteTelefono.setName("txtClienteTelefono"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtClienteFax.setName("txtClienteFax"); // NOI18N
        txtClienteFax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteFaxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtClienteCodPost, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtClienteTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(txtClienteCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtClienteDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                    .addComponent(txtClienteFax, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtClienteCuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtClienteCodPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtClienteTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jScrollPane1.setName("tblBusquedaMaterial"); // NOI18N

        tblBusquedaCliente.setModel(this.dtm);
        tblBusquedaCliente.setName("jscroll"); // NOI18N
        jScrollPane1.setViewportView(tblBusquedaCliente);

        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        btnClienteBuscar.setText(resourceMap.getString("btnClienteBuscar.text")); // NOI18N
        btnClienteBuscar.setName("btnClienteBuscar"); // NOI18N
        btnClienteBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteBuscarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnClienteBuscar);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jToolBar1.add(jSeparator1);

        btnClienteAlta.setText(resourceMap.getString("btnClienteAlta.text")); // NOI18N
        btnClienteAlta.setFocusable(false);
        btnClienteAlta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClienteAlta.setName("btnClienteAlta"); // NOI18N
        btnClienteAlta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClienteAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteAltaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnClienteAlta);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBar1.add(jSeparator2);

        btnClienteModificar.setText(resourceMap.getString("btnClienteModificar.text")); // NOI18N
        btnClienteModificar.setFocusable(false);
        btnClienteModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClienteModificar.setName("btnClienteModificar"); // NOI18N
        btnClienteModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnClienteModificar);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jToolBar1.add(jSeparator3);

        btnClienteCancelar.setText(resourceMap.getString("btnClienteCancelar.text")); // NOI18N
        btnClienteCancelar.setName("btnClienteCancelar"); // NOI18N
        btnClienteCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteCancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnClienteCancelar);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jToolBar1.add(jSeparator4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setCompletarTabla(Cliente cli) {

        ArrayList<Cliente> clientes = new ProcesarClientes().getCliente(cli);
        Iterator<Cliente> itrCli = clientes.iterator();
        //Limpio la tabla
        this.limpiarTabla();
        //Agrego los clientes encontrados a la tabla
        while (itrCli.hasNext()) {
            Cliente client = itrCli.next();
            dtm.addRow(new Object[]{client.getCuit(),
                        client.getNombre(),
                        client.getDireccion(),
                        client.getCodigoPostal(),
                        client.getTelefono(),
                        client.getFax()
                    });
        }
    }

    public void limpiarTabla() {
        dtm.setRowCount(0);
    }

    private void cargarNuevoCliente() {
        //Indico que estoy en la etapa de Alta del cliente
        this.etapa = 1;

        //Deshabilito el boton para buscar y habilito el de alta
        btnClienteBuscar.setEnabled(false);
        btnClienteAlta.setEnabled(false);
        btnClienteAlta.setEnabled(true);
        /*Deshabilito el txt cuit para que no lo modifiquen (solo en caso que lo
         * hayan cargado anteriormente
         */
        if (cliente.getCuit() != 0) {
            txtClienteCuit.setEditable(false);
            txtClienteNombre.requestFocus();
        }

    }

    private void limpiarCampos() {
        txtClienteCodPost.setText("");
        txtClienteCuit.setText("");
        txtClienteCuit.requestFocus();
        txtClienteDireccion.setText("");
        txtClienteFax.setText("");
        txtClienteNombre.setText("");
        txtClienteTelefono.setText("");
        this.limpiarTabla();
        this.etapa = 0;
    }

    private void setTextEnObCliente() {
        if (!txtClienteCuit.getText().isEmpty()) {
            cliente.setCuit(Long.parseLong(txtClienteCuit.getText()));
        }
        cliente.setNombre(txtClienteNombre.getText());
        cliente.setDireccion(txtClienteDireccion.getText());
        cliente.setCodigoPostal(txtClienteCodPost.getText());
        if (!txtClienteTelefono.getText().isEmpty()) {
            cliente.setTelefono(Integer.parseInt(txtClienteTelefono.getText()));
        }
        if ((!txtClienteFax.getText().isEmpty())) {
            cliente.setFax(Integer.parseInt(txtClienteFax.getText()));
        }
    }

    private boolean validarEtapa() {
        String message = "Se detectaron los siguientes errores:\n";
        Boolean error = false;
        switch (this.etapa) {
            //Validaciones para la busqueda de un cliente
            case 0: {
                message = "Debe ingresar al menos una opcion";
                error = true;
                JOptionPane.showConfirmDialog(this,message ,"Buscar Cliente", JOptionPane.WARNING_MESSAGE);
            }
            break;
            //Validaciones para dar de alta un cliente
            case 1: {
                this.setTextEnObCliente();
                if (cliente.getCuit() == 0) {
                    message += "-El Cuit es un campo obligatorio.\n";
                    error = true;
                }
                
                if (cliente.getNombre().isEmpty()) {
                    message += "-El Nombre es un campo obligatorio.\n";
                    error = true;
                }
                
                if (cliente.getCodigoPostal().isEmpty()) {
                    message += "-El CP es un campo obligatorio.\n";
                    error = true;
                }
                
                if (cliente.getDireccion().isEmpty()) {
                    message += "-La Direccion es un campo obligatorio.\n";
                    error = true;
                }
                
                if (cliente.getTelefono() == 0) {
                    message += "-El Telefono es un campo obligatorio.\n";
                    error = true;
                }
                
                if (cliente.getFax() == 0) {
                    message += "-El Fax es un campo obligatorio.\n";
                    error = true;
                }
                
                if (error) {
                    JOptionPane.showConfirmDialog(this, message, "Alta Cliente", JOptionPane.WARNING_MESSAGE);
                }
            }
            break;
            //Validaciones para la modificacion de un cliente
            case 2: {
                System.out.println("Valido la modificacion");
                error = true;
            }
            break;
        }
        return error;
    }

    private void btnClienteBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteBuscarActionPerformed
        cliente = new Cliente();
        Integer option = null;
        this.setTextEnObCliente();

        if (cliente.isEmpty()) {
            this.validarEtapa();
        } else {
            if (new ProcesarClientes().existeCliente(cliente)) {
                this.setCompletarTabla(cliente);
            } else {
                String message = "El cliente solicitado no existe. Quiere darlo de alta?";
                option = JOptionPane.showConfirmDialog(this,message,"Clientes", JOptionPane.WARNING_MESSAGE);
                //El sistema actua de acuerdo a la seleccion del usuario
                if (option == 0) {
                    this.cargarNuevoCliente();
                } else {
                    this.limpiarCampos();
                }
            }
        }
    }//GEN-LAST:event_btnClienteBuscarActionPerformed

    private void txtClienteFaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteFaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteFaxActionPerformed

    private void btnClienteCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteCancelarActionPerformed
        Integer option;
        switch (this.etapa) {
            case 0: {
                this.limpiarCampos();
                this.limpiarTabla();
            }
            break;
            //Cancelacion del alta
            case 1: {
                //Pregunto si quiere cancelar el alta
                String message = "Esta seguro que quiere cancelar el alta de este Cliente?";
                option = JOptionPane.showConfirmDialog(this, message, "Alta Cliente", JOptionPane.WARNING_MESSAGE);
                //El sistema actua de acuerdo a la seleccion del usuario
                if (option == 0) {
                    this.etapa = 0;
                    this.limpiarCampos();
                    txtClienteCuit.setEditable(true);
                    txtClienteCuit.requestFocus();
                    btnClienteBuscar.setEnabled(true);
                    btnClienteAlta.setEnabled(false);
                }

            }
            break;
            case 2: {
                this.etapa = 1;
            }
            break;
        }
    }//GEN-LAST:event_btnClienteCancelarActionPerformed

    private void btnClienteAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteAltaActionPerformed
        if(!this.validarEtapa()){
            this.setTextEnObCliente();
            ProcesarClientes proCli = new ProcesarClientes();
            proCli.agregarCliente(cliente);
            if(proCli.existeCliente(cliente)){
                String message = "El cliente "+cliente.getCuit()+" se dio de alta satisfactoriamente.";
                JOptionPane.showConfirmDialog(this, message, "Alta Cliente", JOptionPane.WARNING_MESSAGE);
            }else {
                String message = "El cliente "+cliente.getCuit()+" no pudo darse de alta satisfactoriamente.";
                JOptionPane.showConfirmDialog(this, message, "Alta Cliente", JOptionPane.WARNING_MESSAGE);
            }
        }
            
        
    }//GEN-LAST:event_btnClienteAltaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClienteAlta;
    private javax.swing.JButton btnClienteBuscar;
    private javax.swing.JButton btnClienteCancelar;
    private javax.swing.JButton btnClienteModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblBusquedaCliente;
    private javax.swing.JTextField txtClienteCodPost;
    private javax.swing.JTextField txtClienteCuit;
    private javax.swing.JTextField txtClienteDireccion;
    private javax.swing.JTextField txtClienteFax;
    private javax.swing.JTextField txtClienteNombre;
    private javax.swing.JTextField txtClienteTelefono;
    // End of variables declaration//GEN-END:variables
}