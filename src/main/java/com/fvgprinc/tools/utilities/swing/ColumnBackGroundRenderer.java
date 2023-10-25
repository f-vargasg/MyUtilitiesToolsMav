/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities.swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author fvargas
 */
public class ColumnBackGroundRenderer implements TableCellRenderer {

    private TableCellRenderer delegate;
    private Color alternateColor;

    public ColumnBackGroundRenderer(TableCellRenderer defaultRenderer) {
        this.delegate = defaultRenderer;
        this.alternateColor = UIManager.getColor("Table.alternateRowColor");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!isSelected) {
            int modelColum = table.convertColumnIndexToModel(column);
            
            Color defaultBackGround = (row % 2 == 0 && this.alternateColor != null) ? this.alternateColor : table.getBackground();
            //c.setBackground(modelColum == 7 ? new Color(0xFFF0E0) : table.getBackground());
            //c.setBackground(row == 2 ? new Color(0xDDD7FF) : c.getBackground());
        }
        return c;
    }
}
