/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fvgprinc.tools.utilities;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author garfi
 */
public class MySwingUtil {

    // Tipos de mensajes para los dialogos
    public static final int TD_INFO = 0;   // informacion
    public static final int TD_ERROR = 1;  // Error

    // Defaults look and feel 
    public static final String LFMETAL = "Metal";
    public static final String LFNIMBUS = "Nimbus";
    public static final String LFCDEMOTIF = "CDE/Motif";
    public static final String LFWINDOWS = "Windows";
    public static final String LFWINDOWSCLASSIC = "Windows Classic";

    /*
    
     */
    public static void initUiLookAndFeel(String lafName) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */

        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if (lafName.equals(info.getName())) {

                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MySwingUtil.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(MySwingUtil.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(MySwingUtil.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(MySwingUtil.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        }
    }

    public static void mostrarMensaje(String mensaje, int tipo, String titulo) {
        JOptionPane optionPane = new JOptionPane(mensaje);

        if (tipo == TD_INFO) {
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        } else if (tipo == TD_ERROR) {
            optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
        }
        JDialog dialog = optionPane.createDialog(titulo);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

}
