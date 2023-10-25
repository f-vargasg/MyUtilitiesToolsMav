/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities.swing;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author fvargas
 * @param <T>
 */
public class MyDataTableModel<T> extends AbstractTableModel {

    private ArrayList<T> myData;
    private final DefiCustomColumnHeaderTable[] defColumnsTable; //TODO: Pasar
    //private String[] header;
    //private String[] idHeader;

    public ArrayList<T> getMyData() {
        return myData;
    }

    public void setMyData(ArrayList<T> myData) {
        this.myData = myData;
        fireTableChanged(null);
    }

    public MyDataTableModel(DefiCustomColumnHeaderTable[] pDefColumsTable) {
        this.myData = new ArrayList<T>();
        this.defColumnsTable = pDefColumsTable;
    }

    @Override
    public int getRowCount() {
        return this.myData.size();
    }

    @Override
    public int getColumnCount() {
        int tam = this.defColumnsTable.length;
        return tam;
    }

    @Override
    public Class getColumnClass(int c) {
        Class res = Integer.class;
        switch (defColumnsTable[c].getType()) {
            case INTTYPE:
                res = Integer.class;
                break;
            case LONGTYPE:
                res = Long.class;
                break;
            case DOUBLETYPE:
                res = Double.class;
                break;
            case NUMBERFIXEDTYPE:
                res = NumberFixed.class;
                break;
            case STRINGTYPE:
                res = String.class;
                break;
            case SQLDATETYPE:
                res = java.sql.Date.class;
                break;
            case UTILDATETYPE:
                res = java.util.Date.class;
                break;
            case CUSTOMSQLDATETYPE:
                res = CustomJavaSqlDate.class;
                break;
            case CUSTOMUTILDATETYPE:
                res = CustomJavaUtilDate.class;
                break;
        }

        return res;
    }

    @Override
    public String getColumnName(int c) {
        return this.defColumnsTable[c].getLabelColumn();
    }

    @Override
    public Object getValueAt(int row, int col) {
        Displayable disp;
        disp = (Displayable) myData.get(row);
        Object[] res = disp.getDataArrayObject();
        return res[col];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Displayable disp;
        disp = (Displayable) myData.get(row);
        disp.setDataObject(col, value);
        fireTableCellUpdated(row, col);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return this.defColumnsTable[columnIndex].isEditable();
    }
    
    

    public void setValueAtByColumName(int row, String idColumn, Object value) {
        int col;

        col = this.getColumnNumber(idColumn);
        setValueAt(value, row, col);
    }

    public void setValueByRow(int row, T e) {
        this.myData.set(row, e);
        fireTableRowsUpdated(row, row);
    }

    public void addValue(T pValue) {
        boolean res;
//    public void addValue(Object pValue) {
        res = this.myData.add(pValue);
        //fireTableDataChanged();
        fireTableChanged(null);
    }

    /**
     * Return entity row
     *
     * @param row
     * @return
     */
    public T getValueByRow(int row) {
        return myData.get(row);
    }

    public void deleteValueAt(int pos) {
        //this.cache.remove(pos);
        this.myData.remove(pos);
        fireTableRowsDeleted(pos, pos);
    }

    public void deleteAll() {
        this.myData.clear();
        fireTableChanged(null);
    }

    public int getColumnNumber(String idColumn) {
        int ind = 0;
        boolean wf = false;

        while (!wf) {
            if (this.defColumnsTable[ind].getIdColumn().compareToIgnoreCase(idColumn) == 0) {
                wf = true;
            } else {
                ++ind;
            }
        }
        return ind;
    }

    public DefiCustomColumnHeaderTable[] getDefColumnsTable() {
        return defColumnsTable;
    }
    
    
    /**
     * Given a idColumn returns the column index number of this
     *
     * @param idColumn
     * @return the number of column of the id column
     */
    /*
     public int getColumnNumber(String idColumn) {
     /*
     int ind = 0;
     boolean wf = false;

     while (!wf) {
     if (this.defColumnsTable[ind].getIdColumn().compareToIgnoreCase(idColumn) == 0) {
     wf = true;
     } else {
     ++ind;
     }
     }
     return ind;
     * */
    /*  }

     public int getColumnWidth(int i) {
     int res;

     res = this.defColumnsTable[i].getLength();
     return res;
     }

     private String searchColumnNameByNumCol(int col) {
     int ind = 0;
     boolean f = false;

     while (!f && ind < this.defColumnsTable.length) {
     f = (col == ind);
     ind = (f ? ind : ++ind);
     }
     return this.defColumnsTable[ind].getDbColName();
     }
     */
    /**
     * public Object getValueByColName(int row, String columnName) { Displayable
     * disp; // search in the header vector to which column // is mapped the
     * name disp = (Displayable) myData.get(row); String res =
     * disp.getDataMap(this.idHeader).get(columnName); return res; // return
     * ((String[]) cache.elementAt(row))[col]; }
     *
     *
     */
}
