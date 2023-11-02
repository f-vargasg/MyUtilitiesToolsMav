/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities.swing;

import java.util.StringTokenizer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author fvargas
 * @deprecated No USAR POR EL MOMENTO
 */
public class MyDefaultTableColumnModel extends DefaultTableColumnModel {

    private DefiCustomColumnHeaderTable[] defColumnsTable; //TODO: Pasar

    public DefiCustomColumnHeaderTable[] getDefColumnsTable() {
        return defColumnsTable;
    }

    public MyDefaultTableColumnModel(DefiCustomColumnHeaderTable[] defColumnsTable) {
        this.defColumnsTable = defColumnsTable;
    }

    public MyDefaultTableColumnModel(String strDef) {
        parseDefColumnHeader(strDef);
    }

    /**
     * *
     *
     * @param defColumnHeader
     */
    private void parseDefColumnHeader(String defColumnHeader) {
        StringTokenizer stkLine = new StringTokenizer(defColumnHeader, "|");
        this.defColumnsTable = new DefiCustomColumnHeaderTable[stkLine.countTokens()];
        int i = 0;
        while (stkLine.hasMoreTokens()) {
            String line = stkLine.nextToken();
            StringTokenizer stkFlds = new StringTokenizer(line, ";");
            DefiCustomColumnHeaderTable colDefinition = new DefiCustomColumnHeaderTable();
            int j = 0;
            colDefinition.setEditable(false);  // default false editable cells            
            while (stkFlds.hasMoreTokens()) {
                String strFld = stkFlds.nextToken();
                strFld = strFld.trim();
                switch (j) {
                    case DefiCustomColumnHeaderTable.IDCOLUMN:
                        colDefinition.setIdColumn(strFld);
                        break;
                    case DefiCustomColumnHeaderTable.TYPECOLUMN:
                        colDefinition.setType(getTypeFromString(strFld));
                        break;
                    case DefiCustomColumnHeaderTable.LABELCOLUMN:
                        colDefinition.setLabelColumn(strFld);
                        break;
                    case DefiCustomColumnHeaderTable.DBCOLNAMECOLUMN:
                        colDefinition.setDbColName(strFld);
                        break;
                    case DefiCustomColumnHeaderTable.PRECISIONCOLUMN:
                        colDefinition.setPrecision(Integer.parseInt(strFld));
                        break;
                    case DefiCustomColumnHeaderTable.LENGTHCOLUMN:
                        colDefinition.setLength(Integer.parseInt(strFld));
                        break;
                    case DefiCustomColumnHeaderTable.SCALECOLUMN:
                        colDefinition.setScale(Integer.parseInt(strFld));
                        break;
                    case DefiCustomColumnHeaderTable.OBJNAMECOLUMN:
                        colDefinition.setObjectName(strFld);
                        break;
                    case DefiCustomColumnHeaderTable.EDITABLENAMECOLUMN:
                        colDefinition.setEditable((strFld.compareToIgnoreCase("S") == 0));
                }
                j++;
                this.defColumnsTable[i] = colDefinition;
            }
            i++;
        }
        /**
         * myDataTableModel.header = new String[myDataTableModel.colCount]; for
         * (int h = 0; h < myDataTableModel.colCount; h++) {
         * myDataTableModel.header[h] =
         * myDataTableModel.defColumnsTable[h].getLabelColumn(); }
         * myDataTableModel.idHeader = new String[myDataTableModel.colCount];
         * for (int h = 0; h < myDataTableModel.colCount; h++) {
         * myDataTableModel.idHeader[h] =
         * myDataTableModel.defColumnsTable[h].getIdColumn(); }
         *
         */
    }

    /**
     * public Object getValueByColName(int row, String columnName) { Displayable
     * disp; // search in the header vector to which column // is mapped the
     * name disp = (Displayable) myData.get(row); String res =
     * disp.getDataMap(this.idHeader).get(columnName); return res; // return
     * ((String[]) cache.elementAt(row))[col]; }
     *
     * NOTA: Los tipos definidos son: N = queda DEPRECATED I = Integer L = Long
     * D = Double F = Number Fixed(Number Fixed.-Precision = cant enteros, scale
     * = cant decimales)) S = String D = java.sql.Date DC = java.sql.Date Custom
     * DU =java.util.Date DUC = java.util.Date Custom
     *
     *
     */
    DefiCustomColumnHeaderTable.DataTypes getTypeFromString(String pstr) {
        DefiCustomColumnHeaderTable.DataTypes res = null;
        if (pstr.compareTo("N") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.LONGTYPE;
        } else if (pstr.compareTo("I") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.INTTYPE;
        } else if (pstr.compareTo("L") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.LONGTYPE;
        } else if (pstr.compareTo("D") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.DOUBLETYPE;
        } else if (pstr.compareTo("F") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.NUMBERFIXEDTYPE;
        } else if (pstr.compareTo("S") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.STRINGTYPE;
        } else if (pstr.compareTo("DSQ") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.SQLDATETYPE;
        } else if (pstr.compareTo("DC") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.CUSTOMSQLDATETYPE;
        } else if (pstr.compareTo("DU") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.UTILDATETYPE;
        } else if (pstr.compareTo("DUC") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.CUSTOMUTILDATETYPE;
        }
        return res;
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

    public int getColumnWidth(int i) {
        int res;

        res = this.defColumnsTable[i].getLength();
        return res;
    }

    public String searchColumnNameByNumCol(int col) {
        int ind = 0;
        boolean f = false;

        while (!f && ind < this.defColumnsTable.length) {
            f = (col == ind);
            ind = (f ? ind : ++ind);
        }
        return this.defColumnsTable[ind].getDbColName();
    }

    /**
     * Se tuvo que definir esta clase para poder ajustar los tamanos de las
     * columnas en la creacion
     *
     * @param aColumn
     */
    @Override
    public void addColumn(TableColumn aColumn) {
        int colLength = defColumnsTable[aColumn.getModelIndex()].getLength();
        if (colLength == 0) {
            aColumn.setMinWidth(0);
            aColumn.setWidth(0);
            aColumn.setMaxWidth(0);
        } else {
            // aColumn.setMinWidth(0);
            aColumn.setPreferredWidth(colLength);
            // aColumn.setMaxWidth((int) (colLength * 2));
        }

        super.addColumn(aColumn); //To change body of generated methods, choose Tools | Templates.
    }

}
