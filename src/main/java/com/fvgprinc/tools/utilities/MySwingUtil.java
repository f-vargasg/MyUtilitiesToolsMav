package com.fvgprinc.tools.utilities;

import com.fvgprinc.tools.string.MyCommonString;
import com.fvgprinc.tools.utilities.swing.ThemeManager;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
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
import javax.swing.table.TableModel;

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

    /**
     * @deprecated Este método ya no se usa Use
     * {@link #initLookAndFeel(String, Component()} que sí funciona
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

    public static void initUiLookAndFeel(String lafName, Component root) {
        ThemeManager.setSkin(lafName, root);
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

    /**
     * Retamaña las columnas de un Jtable
     *
     * @param table
     */
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

    /**
     * Muestra un JFrame sin necesidad de tanto protocolo.
     *
     * @param jfrm
     */
    public static void showJFrame(JFrame jfrm) {
        jfrm.setLocationRelativeTo(null);
        jfrm.setVisible(true);
    }

    /**
     * Muestra un JFrame sin necesidad tanto protocolo, Se puede puede poner un
     * titulo (title) al Jframe
     *
     * @param jfrm
     * @param title
     */
    public static void showJFrame(JFrame jfrm, String title) {
        // jfrm.setTitle(title);
        jfrm.setTitle(title + " (" + jfrm.getName() + ")");
        jfrm.setLocationRelativeTo(null);
        jfrm.setVisible(true);
    }

    /**
     * @deprecated Este método está obsoleto. Se recomienda utilizar
     * {@link #llenarCombo} en su lugar.
     */
    @Deprecated
    public static <T> void fillCombo(JComboBox jcb, List lst) {
        JComboBox<T> cmb = jcb;
        DefaultComboBoxModel<T> modelCombo = (DefaultComboBoxModel<T>) jcb.getModel();
        for (Object t : lst) {
            if (modelCombo.getIndexOf(t) == -1) {
                cmb.addItem((T) t);
            }
        }
    }

    /**
     * Llena un JComboBox con una lista de elementos de forma genérica, evitando
     * duplicados basados en el método equals() del objeto.
     *
     * * @param <T> El tipo de entidad (Compania, Rol, Usuario, etc.)
     * @param comboBox El JComboBox que se desea llenar
     * @param items La lista de elementos con los que se llenará el combo
     */
    public static <T> void llenarComboBox(JComboBox<T> comboBox, List<T> items) {
        if (comboBox == null || items == null) {
            return;
        }
        comboBox.removeAllItems();
        // Obtenemos el modelo actual del combo, casteado de forma segura
        DefaultComboBoxModel<T> model = (DefaultComboBoxModel<T>) comboBox.getModel();

        for (T item : items) {
            // equals() debe estar correctamente implementado en tu entidad <T>
            if (model.getIndexOf(item) == -1) {
                comboBox.addItem(item);
            }
        }
    }

    /**
     * }
     * Encuentra, en un JCombo un objetio de tipo T, y retorna el indice donde
     * se encuentra el objeto
     *
     * @param <T> El tipo del objeto que tiene el modelo asociado con el combo
     * @param jcb El combo asociado
     * @param obj1 El objeto a buscar
     * @return El indice de la primer ocurrencia de obj1 en el modelo asociado
     * con el combo jcb
     */
    public static <T> int findItemIndex(JComboBox jcb, T obj1) {
        DefaultComboBoxModel<T> dcbm = (DefaultComboBoxModel<T>) jcb.getModel();
        for (int i = 0; i < dcbm.getSize(); i++) {
//             if (model.getElementAt(i).equals(item)) {
            T obj2 = dcbm.getElementAt(i);
            if (!obj2.equals(obj1)) {
            } else {
                return i;  // Retorna el índice del ítem si lo encuentra
            }
        }
        return -1; // Retorna -1 si no se encuentra el ítem
    }

    /**
     * Posiciona un JComboBox en el elemento que coincida con el ID buscado.
     *
     * @param <T> El tipo de objeto que contiene el combo (ej. Compania, Rol)
     * @param combo El JComboBox físico de la pantalla
     * @param idBuscado El ID que queremos encontrar (ej. 5)
     * @param extractorDeId Una función que le dice al método cómo obtener el ID
     * del objeto
     */
    public static <T> void findInComboById(JComboBox<T> combo, int idBuscado, Function<T, Integer> extractorDeId) {

        // Si el combo es nulo o no tiene items, no hacemos nada
        if (combo == null || combo.getItemCount() == 0) {
            return;
        }

        // Recorremos el combo
        for (int i = 0; i < combo.getItemCount(); i++) {
            T item = combo.getItemAt(i);

            // Verificamos que el item no sea nulo y usamos el extractor para sacar su ID
            if (item != null && extractorDeId.apply(item) == idBuscado) {
                combo.setSelectedIndex(i);
                return; // Encontramos el objeto, salimos del método
            }
        }

        // Opcional: Si el ID no existe en la lista, lo dejamos en el índice 0 ("--Seleccione--")
        combo.setSelectedIndex(0);
    }
    
    /**
     * Extrae un valor (como un ID) del elemento seleccionado en un JComboBox.
     * 
     * @param <T> El tipo de objeto guardado en el JComboBox (ej: Aplicacion, Menu, Persona)
     * @param <R> El tipo de dato que quieres extraer (ej: Integer, Long, String)
     * @param combo El JComboBox a evaluar.
     * @param idExtractor Expresión lambda o método de referencia que obtiene el ID (ej: Aplicacion::getIdApp).
     * @return El ID extraído o null si no hay nada seleccionado.
     */
    public static <T, R> R getSelectedId(JComboBox<T> combo, Function<T, R> idExtractor) {
        if (combo != null && combo.getSelectedItem() != null) {
            // Intentamos convertir de forma segura el ítem seleccionado al tipo T
            Object selectedItem = combo.getSelectedItem();
            try {
                @SuppressWarnings("unchecked")
                T item = (T) selectedItem;
                return idExtractor.apply(item);
            } catch (ClassCastException e) {
                return null;
            }
        }
        return null;
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

    public static class ColumnaNoNumericaException extends Exception {

        public ColumnaNoNumericaException(int fila) {
            super("La celda en la fila " + fila + " no contiene un valor numérico.");
        }
    }

    /**
     * Performs the sum of the column called column, and returns the result of
     * the sum. If any of the values ​​in the column are NOT numeric, an
     * exception is returned
     *
     * @param tabla la tabla con que se va a trabajar
     * @param columna la columna a sumar
     * @return la sumatoria de la columna en cuestión
     * @throws
     * com.fvgprinc.tools.utilities.MySwingUtil.ColumnaNoNumericaException
     */
    public static double columnSum(JTable tabla, int columna) throws ColumnaNoNumericaException {
        if (tabla == null || columna < 0 || columna >= tabla.getColumnCount()) {
            throw new IllegalArgumentException("Parámetros inválidos");
        }

        TableModel modelo = tabla.getModel();
        double suma = 0;

        for (int fila = 0; fila < modelo.getRowCount(); fila++) {
            Object valor = modelo.getValueAt(fila, columna);
            if (valor instanceof Number) {
                suma += ((Double) valor); // Sumamos como double para mayor precisión
            } else {
                // Manejar el caso en que el valor no sea un número (por ejemplo, mostrar un mensaje de error)
                throw new ColumnaNoNumericaException(fila);
            }
        }
        return suma;
    }

    public static <T> void initCombo(List<T> lst, JComboBox<T> cmb) {
        DefaultComboBoxModel<T> model = (DefaultComboBoxModel<T>) cmb.getModel();
        for (T tipoLugar : lst) {
            model.addElement(tipoLugar);
        }
    }

}
