/*
 * SIPGUIView.java
 */

package Presentacion;

import Presentacion.AbmClientes.ClientesConsulta;
import Presentacion.AbmMateriales.MaterialesConsulta;
import Presentacion.ProcesarPedidoCliente.pnlProcesarPedidoCliente;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The application's main frame.
 */
public class SIPGUIView extends FrameView {
    private static SIPGUIView instance;
    public static SIPGUIView getInstance(){
        return instance;
    }
    
    public SIPGUIView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                }
            }
        });

        instance = this;
        mainPanel.setLayout(new BorderLayout());
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = SIPGUIApp.getApplication().getMainFrame();
            aboutBox = new SIPGUIAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        SIPGUIApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        mnuClienteNuevoPedido = new javax.swing.JMenuItem();
        mnuClienteAbm = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        mnuMateriales = new javax.swing.JMenu();
        mnuMaterialesConsulta = new javax.swing.JMenuItem();
        mnuMaterialesPlanifStock = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();

        mainPanel.setName("mainPanel"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Presentacion.SIPGUIApp.class).getContext().getResourceMap(SIPGUIView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N
        fileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuActionPerformed(evt);
            }
        });

        mnuClienteNuevoPedido.setLabel(resourceMap.getString("itmProcesarPedidoCliente.label")); // NOI18N
        mnuClienteNuevoPedido.setName("itmProcesarPedidoCliente"); // NOI18N
        mnuClienteNuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuClienteNuevoPedidoActionPerformed(evt);
            }
        });
        fileMenu.add(mnuClienteNuevoPedido);
        mnuClienteNuevoPedido.getAccessibleContext().setAccessibleName(resourceMap.getString("jMenuItem1.AccessibleContext.accessibleName")); // NOI18N

        mnuClienteAbm.setText(resourceMap.getString("mnuClienteAbm.text")); // NOI18N
        mnuClienteAbm.setName("mnuClienteAbm"); // NOI18N
        mnuClienteAbm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuClienteAbmActionPerformed(evt);
            }
        });
        fileMenu.add(mnuClienteAbm);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(Presentacion.SIPGUIApp.class).getContext().getActionMap(SIPGUIView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        mnuMateriales.setText(resourceMap.getString("mnuMateriales.text")); // NOI18N
        mnuMateriales.setName("mnuMateriales"); // NOI18N

        mnuMaterialesConsulta.setText(resourceMap.getString("mnuMaterialesConsulta.text")); // NOI18N
        mnuMaterialesConsulta.setName("mnuMaterialesConsulta"); // NOI18N
        mnuMaterialesConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMaterialesConsultaActionPerformed(evt);
            }
        });
        mnuMateriales.add(mnuMaterialesConsulta);

        mnuMaterialesPlanifStock.setText(resourceMap.getString("mnuMaterialesPlanifStock.text")); // NOI18N
        mnuMaterialesPlanifStock.setName("mnuMaterialesPlanifStock"); // NOI18N
        mnuMateriales.add(mnuMaterialesPlanifStock);

        menuBar.add(mnuMateriales);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 380, Short.MAX_VALUE)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void mnuClienteNuevoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClienteNuevoPedidoActionPerformed
        mainPanel.add(new pnlProcesarPedidoCliente());
        System.out.println("Nuevo pedido cliente");
        mainPanel.revalidate(); //(sino no lo muestra)
    }//GEN-LAST:event_mnuClienteNuevoPedidoActionPerformed

    private void mnuMaterialesConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMaterialesConsultaActionPerformed
        mainPanel.add(new MaterialesConsulta());
        System.out.println("ABM Materiales");
        mainPanel.revalidate(); //(sino no lo muestra)
    }//GEN-LAST:event_mnuMaterialesConsultaActionPerformed

    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuActionPerformed
        
    }//GEN-LAST:event_fileMenuActionPerformed

    private void mnuClienteAbmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClienteAbmActionPerformed
        mainPanel.add(new ClientesConsulta());
        mainPanel.revalidate();
    }//GEN-LAST:event_mnuClienteAbmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnuClienteAbm;
    private javax.swing.JMenuItem mnuClienteNuevoPedido;
    private javax.swing.JMenu mnuMateriales;
    private javax.swing.JMenuItem mnuMaterialesConsulta;
    private javax.swing.JMenuItem mnuMaterialesPlanifStock;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    
    private JLabel[] pasosProceso = new JLabel[0];
    
    //Cargo los pasos indicados en el indicador de pasos de la barra de estado
    public void RegistrarPasosProceso(String[] nombres){
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        //statusPanel.setVisible(true);
        
        ArrayList<JLabel> pasos = new ArrayList<JLabel>();
        for(String paso:nombres){
            JLabel labelPaso = new JLabel(paso);
            pasos.add(labelPaso); labelPaso.setVisible(true);
            statusPanel.add(labelPaso); labelPaso.setEnabled(false);

            labelPaso = new JLabel(" > ");
            statusPanel.add(labelPaso); labelPaso.setVisible(true);
        }
        
        JLabel pasoFinal = new JLabel("Fin");
        statusPanel.add(pasoFinal); pasoFinal.setVisible(true);
        pasoFinal.setEnabled(false);
        
        pasosProceso = pasos.toArray(pasosProceso);
        statusPanel.repaint();
    }
    
    public void EliminarProceso(){
        pasosProceso = new JLabel[0];
        statusPanel.removeAll();
        mainPanel.removeAll();
    }
    
    public void SeleccionarNumeroPaso(int numeroPaso){
        int index = 0;
        for(JLabel label : pasosProceso){
            label.setEnabled(index == numeroPaso);
            index++;
        }
    }
}
