/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * pnlReporteStock.java
 *
 * Created on 20/07/2011, 00:44:13
 */
package Presentacion.ReporteStock;

import Negocio.GenerarReporteStock;
import Presentacion.SIPGUIView;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author NicolasM
 */
public class pnlReporteStock extends javax.swing.JPanel {
    private static final String[] pasosProceso = {"Filtros", "Reporte de Stock"};
    private GenerarReporteStock proceso = new GenerarReporteStock();
    private ArrayList<JPanel> panelesProceso;
    
    
    
    /** Creates new form pnlReporteStock */
    public pnlReporteStock() {
        initComponents();
        
        this.setLayout(new BorderLayout());
        SIPGUIView.getInstance().RegistrarPasosProceso(pasosProceso);

        ArrayList<JPanel> pasos = new ArrayList<JPanel>();
        pasos.add(new pnlRS_Filtros(this, proceso, 0));
        //pasos.add(new pnlPPC_SeleccionProductosCantidad(this, proceso, 1));
        
        panelesProceso = pasos;

        this.add(panelesProceso.get(0));
        panelesProceso.get(0).setVisible(true);
    }
    
    public void SiguientePaso(int pasoActual) {
        panelesProceso.get(pasoActual).setVisible(false);
        this.remove(panelesProceso.get(pasoActual));

        int proximoPaso = pasoActual + 1;
        if (panelesProceso.size() == proximoPaso) {
            //Alcanzó el último paso, elimina el detalle de pasos
            SIPGUIView.getInstance().EliminarProceso();
            //TODO: quitar this del mainPanel de SIPGUIView
        } else {
            panelesProceso.get(proximoPaso).setVisible(true);
            this.add(panelesProceso.get(proximoPaso));
        }
    }

    public void PasoAnterior(int pasoActual) {
        panelesProceso.get(pasoActual).setVisible(false);
        this.remove(panelesProceso.get(pasoActual));

        panelesProceso.get(pasoActual -1).setVisible(true);
        this.add(panelesProceso.get(pasoActual -1));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setName("Form"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
