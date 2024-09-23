package com.fvgprinc.tools.utilities;

import com.fvgprinc.tools.string.MyCommonString;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author garfi
 */
public class MySwingUtil {

    // Tipos enumerados para los estados 
    // de mis forms
    public static enum FrmStates {
        FRMINSERT, FRMUPDATE, FRMDELELTE, FRMBROWSE
    }

    // Tipos de mensajes para los dialogos
    public static final int TD_INFO = 0;   // informacion
    public static final int TD_ERROR = 1;
    public static final int TD_WARNING = 2;

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
                    break;
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

        switch (tipo) {
            case TD_INFO:
                optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                break;
            case TD_ERROR:
                optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                break;
            case TD_WARNING:
                optionPane.setMessage(JOptionPane.WARNING_MESSAGE);
                break;
            default:
                break;
        }

        JDialog dialog = optionPane.createDialog(titulo);

        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
            double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
        }
    }

    public static void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public static void showJFrame(JFrame jfrm) {
        jfrm.setLocationRelativeTo(null);
        jfrm.setVisible(true);
    }

    public static void showJFrame(JFrame jfrm, String title) {
        // jfrm.setTitle(title);
        jfrm.setTitle(title + " (" + jfrm.getName() + ")" );
        jfrm.setLocationRelativeTo(null);
        jfrm.setVisible(true);
    }

    public static <T> int findItemIndex(JComboBox jcb, T obj1) {
        DefaultComboBoxModel<T> dcbm = (DefaultComboBoxModel<T>) jcb.getModel();
        for (int i = 0; i < dcbm.getSize(); i++) {
//             if (model.getElementAt(i).equals(item)) {
            T obj2 = dcbm.getElementAt(i);
            if (obj2.equals(obj1)) {
            } else {
                return i;  // Retorna el índice del ítem si lo encuentra
            }
        }
        return -1; // Retorna -1 si no se encuentra el ítem
    }

    /**
     *
     * @param container Define el componente a deshabilitar
     * @param activar Indica si se activa (true) o si se desactiva (false)
     * @param nameExceptions es una lista de excepciones, es decir la que está
     * en esta lista no se deshabilita. Recordar Poner el atributo Name en el
     * componente para que esta rutina funcione bien
     */
    public static void activarPanel(Container container, boolean activar,
            ArrayList<String> nameExceptions) {
        String name = MyCommonString.EMPTYSTR;
        boolean isException = false;
        if (container.getName() != null) {
            name = container.getName();
            isException = nameExceptions.contains(name);
        }

        container.setEnabled(activar && !isException);

        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                activarPanel((Container) component, activar, nameExceptions);
            }
            if (component.getName() != null) {
                name = component.getName();
                isException = nameExceptions.contains(name);
            }
            component.setEnabled(activar && !isException);

        }
    }

    /**
     * Dado el nombre de un contenedor (Panel), toma todos los objetos
     * inputeables y los inicializa en vacio
     *
     * @param container
     */
    public static void cleanPanelFlds(Container container) {
        for (Component componente : container.getComponents()) {
            if (componente instanceof JTextField) {
                JTextField textField = (JTextField) componente;
                textField.setText(MyCommonString.EMPTYSTR);
            } else if (componente instanceof JTextArea) {
                JTextArea textArea = (JTextArea) componente;
                textArea.setText(MyCommonString.EMPTYSTR);
            } else if (componente instanceof JSpinner) {
                JSpinner spinner = (JSpinner) componente;
                spinner.setValue(new Date());
            } else if (componente instanceof Container) {   // se llama recursivamente
                cleanPanelFlds((Container) componente);
            }
        }
    }

}
