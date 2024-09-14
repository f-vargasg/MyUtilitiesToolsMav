/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.fvgprinc.tools.utilities.swing;

import com.fvgprinc.tools.string.MyCommonString;

/**
 *
 * @author Ryzen9-Gaming
 */
public class JpnlRecordDataSimple extends javax.swing.JPanel {

    /**
     * Creates new form JpnlRecordDataSimple
     */
    public JpnlRecordDataSimple() {
        initComponents();
    }

    public void cleanPanelRegistro() {
        jLblCodEstadoN.setText(MyCommonString.EMPTYSTR);
        jLblCodRegistroN.setText(MyCommonString.EMPTYSTR);
        jLblUsuIngreso.setText(MyCommonString.EMPTYSTR);
        jLblFecIngreso.setText(MyCommonString.EMPTYSTR);
    }

    public void setTextJLblCodEstado(String text) {
        jLblCodEstadoN.setText(text);
    }

    public void setTextJLblCodRegistroN(String text) {
        jLblCodRegistroN.setText(text);
    }

    public void setTextJLblUsuIngreso(String text) {
        jLblUsuIngreso.setText(text);
    }

    public void setTextjFecIngreso(String text) {
        jLblFecIngreso.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLblCodEstadoN = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLblFecIngreso = new javax.swing.JLabel();
        jLblUsuIngreso = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLblCodRegistroN = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Cod.Estado:");

        jLblCodEstadoN.setText("codEstadoN");
        jLblCodEstadoN.setName("jLblCodEstadoN"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Usu.ingreso:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Fec.Ingreso:");

        jLblFecIngreso.setText("jLblFecIngreso");
        jLblFecIngreso.setName("jLblFecIngreso"); // NOI18N

        jLblUsuIngreso.setText("jLblUsuIngreso");
        jLblUsuIngreso.setName("jLblUsuIngreso"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Cod.Registro:");

        jLblCodRegistroN.setText("codRegistroN");
        jLblCodRegistroN.setName("jLblCodRegistroN"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblUsuIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblCodEstadoN, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblCodRegistroN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblFecIngreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(297, 297, 297))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLblCodEstadoN)
                    .addComponent(jLabel4)
                    .addComponent(jLblCodRegistroN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLblUsuIngreso)
                        .addComponent(jLabel6)
                        .addComponent(jLblFecIngreso)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLblCodEstadoN;
    private javax.swing.JLabel jLblCodRegistroN;
    private javax.swing.JLabel jLblFecIngreso;
    private javax.swing.JLabel jLblUsuIngreso;
    // End of variables declaration//GEN-END:variables
}