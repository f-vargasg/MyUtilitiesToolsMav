
package com.fvgprinc.tools.utilities.swing;

import com.fvgprinc.tools.utilities.MySwingUtil;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author garfi
 */
public class JTableUtil {

        public static double sumarColumna(String nombreColumna, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Double res = 0.00;

        // Obtener el índice de la columna por su nombre
        int selectedColumnIndex = model.findColumn(nombreColumna);

        // Verificar si la columna seleccionada es válida
        if (selectedColumnIndex >= 0) {
            // Sumar la columna seleccionada
            double suma = 0.0;
            for (int row = 0; row < model.getRowCount(); row++) {
                Object valor = model.getValueAt(row, selectedColumnIndex);
                if (valor instanceof Number) {
                    suma += ((Number) valor).doubleValue();
                }
            }
            res = suma;
        } else {

            MySwingUtil.mostrarMensaje("Por favor, selecciona una columna válida.", MySwingUtil.TD_ERROR, "Error");
        }
        return res;
    }
}
