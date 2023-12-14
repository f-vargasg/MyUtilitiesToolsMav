
package com.fvgprinc.tools.utilities.swing.model;


import com.fvgprinc.tools.utilities.swing.interfaces.DisplayableInTable;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author garfi
 * @param <T>
 */
public class PickListTableModel<T extends DisplayableInTable> extends AbstractTableModel {

    private List<T> data;
    private String[] columnNames;

    public PickListTableModel(List<T> data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T item = data.get(rowIndex);

        return item.getDisplayValue(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
