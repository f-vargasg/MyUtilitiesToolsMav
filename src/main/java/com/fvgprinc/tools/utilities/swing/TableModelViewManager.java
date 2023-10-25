/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities.swing;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author fvargas
 * @param <T>
 */
public class TableModelViewManager<T> {

    private MyDataTableModel<T> tblModel;
    private JTable jtbl;
    //String colDef;

    public TableModelViewManager(JTable jtbl, String colDef) {
        this.jtbl = jtbl;
        MyDefaultTableColumnModel myDefaultTableColumnModelI = new MyDefaultTableColumnModel(colDef);
        this.tblModel = new MyDataTableModel<T>(myDefaultTableColumnModelI.getDefColumnsTable());
        this.jtbl.setModel(tblModel);
        this.jtbl.setColumnModel(myDefaultTableColumnModelI);
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
