/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities.swing;

import com.fvgprinc.tools.string.MyFormatStr;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;


/**
 * Esta clase sirve para mapear un campo de la entidad con este tipo
 * para que el jTable lo exprese como un numero fijo de x cantidad de
 * de decimales.
 * @author fvargas
 */
public class NumberFixedRenderer  implements TableCellRenderer {

  
    private TableCellRenderer delegate;
    private NumberFormat formatter;

    public NumberFixedRenderer(TableCellRenderer delegate) {
        this.delegate = delegate;
        this.formatter = NumberFormat.getCurrencyInstance();
    }
    
    
    

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (c instanceof JLabel) {
            // String fmt = "#,###,###,###,###,##0.00";
            int precision = (((MyDefaultTableColumnModel) table.getColumnModel()).getDefColumnsTable())[column].getPrecision();
            int scale = (((MyDefaultTableColumnModel) table.getColumnModel()).getDefColumnsTable())[column].getScale();
            String fmt = MyFormatStr.getFormatNumberFixed(precision, scale, ",", ".");
            DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
            df.applyPattern(fmt);
            Double valor = ((NumberFixed) value).getValor();
            String salida = df.format(valor);
            //salida = formatter.format((valor));
            ((JLabel)c).setText(salida);
            ((JLabel)c).setHorizontalAlignment(SwingConstants.RIGHT);
        }

        return c;

    }
}
