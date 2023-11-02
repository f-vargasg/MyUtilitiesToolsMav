/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities.swing;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author fvargas
 * @param <T>
 */
public class TableModelViewManager<T> {

    private MyDataTableModel<T> tblModel;
    private TableColumnDef tcd;
    private JTable jtbl;
    private TableCellRenderer dateRenderer = new DefaultTableCellRenderer() {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Date) {
                value = f.format(value);
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                    row, column);
        }
    };
    //String colDef;

    public TableModelViewManager(JTable jtbl, String colDef) {
        this.jtbl = jtbl;
        tcd = new TableColumnDef(colDef);
        this.tblModel = new MyDataTableModel<T>(tcd.getDefColumnsTable());
        this.jtbl.setModel(tblModel);
        this.jtbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        /* 
         * Si existieran otros tipos de renderizaod aqui se ponen y se crea la clase asociada
         * se esta utilizando delegados, una forma elegante y fina de utilizar estos objetos.
         */
 /*TableCellRenderer defaultRenderer = this.jtbl.getDefaultRenderer(Object.class);
        TableCellRenderer backGroundRenderer = new ColumnBackGroundRenderer(defaultRenderer);
        TableCellRenderer numberFixedRenderer = new NumberFixedRenderer(backGroundRenderer);
        this.jtbl.setDefaultRenderer(NumberFixed.class, numberFixedRenderer);*/
    }

    //TODO Hacer el render para date fixed
    public void refreshRenderColumns() {
        // Ahora para los campos fixed y date se hará un formato
        TableColumnModel columnModel = this.jtbl.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            DefiCustomColumnHeaderTable.DataTypes colType = tcd.getDefColumnsTable()[i].getType();
            if (colType == DefiCustomColumnHeaderTable.DataTypes.SQLDATETYPE
                    || colType == DefiCustomColumnHeaderTable.DataTypes.UTILDATETYPE) {
                int colActual = i;
                this.jtbl.getColumnModel().getColumn(i).setCellRenderer(dateRenderer);
            }
        }
    }

    public TableModelViewManager(JTable pJtbl) {
        this.jtbl = pJtbl;
        this.tblModel = (MyDataTableModel<T>) pJtbl.getModel();
        this.jtbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        /* 
         * Si existieran otros tipos de renderizaod aqui se ponen y se crea la clase asociada
         * se esta utilizando delegados, una forma elegante y fina de utilizar estos objetos.
         */
        TableCellRenderer defaultRenderer = this.jtbl.getDefaultRenderer(Object.class);
        TableCellRenderer backGroundRenderer = new ColumnBackGroundRenderer(defaultRenderer);
        TableCellRenderer numberFixedRenderer = new NumberFixedRenderer(backGroundRenderer);
        this.jtbl.setDefaultRenderer(NumberFixed.class, numberFixedRenderer);
    }

    public MyDataTableModel<T> getTblModel() {
        return tblModel;
    }

    public JTable getJtbl() {
        return jtbl;
    }
}
