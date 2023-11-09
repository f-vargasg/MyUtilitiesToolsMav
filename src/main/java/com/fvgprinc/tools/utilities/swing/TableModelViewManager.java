/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities.swing;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
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
    //String colDef;
    private TableCellRenderer dateRenderer;

    public TableModelViewManager(JTable jtbl, String colDef) {
        tcd = new TableColumnDef(colDef);
        this.dateRenderer = new DefaultTableCellRenderer() {
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Alternar colores de fondo
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);

                if (row % 2 == 0) {
                    comp.setBackground(Color.WHITE);
                } else {
                    comp.setBackground(new Color(240, 240, 240)); // Gris suave
                }

                DefiCustomColumnHeaderTable dccht = tcd.getDefColumnsTable()[column];
                // Pone el Render (formato) de la columna
                DefiCustomColumnHeaderTable.DataTypes colType = dccht.getType();
                if (colType == DefiCustomColumnHeaderTable.DataTypes.SQLDATETYPE
                        || colType == DefiCustomColumnHeaderTable.DataTypes.UTILDATETYPE) {
                    value = f.format(value);
                } else if  (colType == DefiCustomColumnHeaderTable.DataTypes.NUMBERFIXEDTYPE) {
                    Locale locale = new Locale("en", "US");
                    // DecimalFormat formato = new DecimalFormat("#,###,##0.00");
                    DecimalFormat formato = (DecimalFormat)NumberFormat.getNumberInstance (locale);
                    formato.applyPattern("#,###,##0.00");
                    value = formato.format(value);
                }

                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
            }
        };
        this.jtbl = jtbl;
        this.tblModel = new MyDataTableModel<>(tcd.getDefColumnsTable());
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
            TableColumn tcm = this.jtbl.getColumnModel().getColumn(i);
            DefiCustomColumnHeaderTable dccht = tcd.getDefColumnsTable()[i];
            // Pone el Render (formato) de la columna
            DefiCustomColumnHeaderTable.DataTypes colType = dccht.getType();
            /*if (colType == DefiCustomColumnHeaderTable.DataTypes.SQLDATETYPE
                    || colType == DefiCustomColumnHeaderTable.DataTypes.UTILDATETYPE) {
                int colActual = i;
                // this.jtbl.getColumnModel().getColumn(i).setCellRenderer(dateRenderer);
                tcm.setCellRenderer(dateRenderer);
            }*/
            tcm.setCellRenderer(dateRenderer);
            // Pone el tamaño de la colmna
            if (dccht.isVisible()) {
                tcm.setPreferredWidth(tcd.getDefColumnsTable()[i].getLength());
            } else {
                tcm.setMaxWidth(0);
                tcm.setMinWidth(0);
                tcm.setPreferredWidth(0);
                tcm.setResizable(false);
            }

        }
    }

    public TableModelViewManager(JTable pJtbl) {
        this.dateRenderer = new DefaultTableCellRenderer() {
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                if (value instanceof Date) {
                    value = f.format(value);
                }

                // Alternar colores de fondo
                if (row % 2 == 0) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(new Color(240, 240, 240)); // Gris suave
                }
                return c;
            }
        };
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
