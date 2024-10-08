/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.fvgprinc.tools.utilities.swing;

import com.fvgprinc.tools.string.MyCommonString;

/**
 *
 * @author garfi
 */
public final class JpnlRecordData extends javax.swing.JPanel {

    /**
     * Creates new form JPanelData
     */
    public JpnlRecordData() {
        initComponents();
        cleanPanelRegistro();
    }

    public void cleanPanelRegistro() {
        jLblCodEstadoN.setText(MyCommonString.EMPTYSTR);
        jLblCodRegistroN.setText(MyCommonString.EMPTYSTR);
        jLblUsuIngreso.setText(MyCommonString.EMPTYSTR);
        jLblFecIngreso.setText(MyCommonString.EMPTYSTR);
        JLblUsuModatos.setText(MyCommonString.EMPTYSTR);
        jLblFecModatos.setText(MyCommonString.EMPTYSTR);
        jLblUsuModesta.setText(MyCommonString.EMPTYSTR);
        jLblFecModesta.setText(MyCommonString.EMPTYSTR);
    }
    
    public void setTextJLblCodEstado(String text )   {
        jLblCodEstadoN.setText(text);
    }
    
    public void setTextJLblCodRegistroN (String text)   {
        jLblCodRegistroN.setText(text);
    }
    
    public void setTextJLblUsuIngreso(String text) {
        jLblUsuIngreso.setText(text);
    }

    public void setTextjFecIngreso(String text) {
        jLblFecIngreso.setText(text);
    }
    
    public void setTextJLblUsuModatos(String text) {
        JLblUsuModatos.setText(text);
    }
    
    public void setTextJblFecModatos(String text) {
        jLblFecModatos.setText(text);
    }
    
    public void setTextJLblUsuModesta(String text) {
        jLblUsuModesta.setText(text);
    }
    
    public void setTextJlblFecModesta(String text) {
        jLblFecModesta.setText(text);
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
        jLabel4 = new javax.swing.JLabel();
        jLblCodRegistroN = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLblFecIngreso = new javax.swing.JLabel();
        jLblUsuIngreso = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JLblUsuModatos = new javax.swing.JLabel();
        jLblFecModatos = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLblUsuModesta = new javax.swing.JLabel();
        jLblFecModesta = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Cod.Estado:");

        jLblCodEstadoN.setText("codEstadoN");
        jLblCodEstadoN.setName("jLblCodEstadoN"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Cod.Registro:");

        jLblCodRegistroN.setText("codRegistroN");
        jLblCodRegistroN.setName("jLblCodRegistroN"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Usu.ingreso:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Fec.Ingreso:");

        jLblFecIngreso.setText("jLblFecIngreso");
        jLblFecIngreso.setName("jLblFecIngreso"); // NOI18N

        jLblUsuIngreso.setText("jLblUsuIngreso");
        jLblUsuIngreso.setName("jLblUsuIngreso"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Usu.Modatos:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Fec.Modatos:");

        JLblUsuModatos.setText("jLblUsuModatos");
        JLblUsuModatos.setName("JLblUsuModatos"); // NOI18N

        jLblFecModatos.setText("jLblFecModatos");
        jLblFecModatos.setName("jLblFecModatos"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Usu.Mod.Estado:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Fec.Mod.Estado:");

        jLblUsuModesta.setText("jLblUsuModEsta");
        jLblUsuModesta.setName("jLblUsuModEstado"); // NOI18N

        jLblFecModesta.setText("jLblFecModesa");
        jLblFecModesta.setName("jLblFecModEstado"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLblUsuIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLblFecIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblCodEstadoN, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblFecModatos, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLblUsuModatos, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLblUsuModesta, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblFecModesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLblCodRegistroN, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                        .addComponent(jLabel8)
                        .addComponent(JLblUsuModatos)
                        .addComponent(jLabel9))
                    .addComponent(jLblUsuModesta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLblFecIngreso)
                        .addComponent(jLabel7)
                        .addComponent(jLblFecModatos))
                    .addComponent(jLblFecModesta))
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLblUsuModatos;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblCodEstadoN;
    private javax.swing.JLabel jLblCodRegistroN;
    private javax.swing.JLabel jLblFecIngreso;
    private javax.swing.JLabel jLblFecModatos;
    private javax.swing.JLabel jLblFecModesta;
    private javax.swing.JLabel jLblUsuIngreso;
    private javax.swing.JLabel jLblUsuModesta;
    // End of variables declaration//GEN-END:variables

}
