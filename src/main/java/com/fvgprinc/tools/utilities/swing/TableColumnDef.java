
package com.fvgprinc.tools.utilities.swing;

import java.util.StringTokenizer;

/**
 *
 * @author garfi
 */
public class TableColumnDef {

    private DefiCustomColumnHeaderTable[] defColumnsTable;

    public TableColumnDef(String defColumns) {
        parseDefColumnHeader(defColumns);
    }

    public DefiCustomColumnHeaderTable[] getDefColumnsTable() {
        return defColumnsTable;
    }

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
                    case DefiCustomColumnHeaderTable.IDCOLUMN -> colDefinition.setIdColumn(strFld);
                    case DefiCustomColumnHeaderTable.TYPECOLUMN -> colDefinition.setType(getTypeFromString(strFld));
                    case DefiCustomColumnHeaderTable.LABELCOLUMN -> colDefinition.setLabelColumn(strFld);
                    case DefiCustomColumnHeaderTable.DBCOLNAMECOLUMN -> colDefinition.setDbColName(strFld);
                    case DefiCustomColumnHeaderTable.PRECISIONCOLUMN -> colDefinition.setPrecision(Integer.parseInt(strFld));
                    case DefiCustomColumnHeaderTable.LENGTHCOLUMN -> colDefinition.setLength(Integer.parseInt(strFld));
                    case DefiCustomColumnHeaderTable.SCALECOLUMN -> colDefinition.setScale(Integer.parseInt(strFld));
                    case DefiCustomColumnHeaderTable.OBJNAMECOLUMN -> colDefinition.setObjectName(strFld);
                    case DefiCustomColumnHeaderTable.EDITABLENAMECOLUMN -> colDefinition.setEditable((strFld.compareToIgnoreCase("S") == 0));
                    case DefiCustomColumnHeaderTable.VISIBLECOLUMN -> colDefinition.setVisible(strFld.compareToIgnoreCase("S") ==0);
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
     * NOTA: Los tipos definidos son: 
     * N = queda DEPRECATED 
     * I = Integer 
     * L = Long
     * D = Double 
     * F = Number Fixed(Number Fixed.-Precision = cant enteros, scale = cant decimales)) 
     * S = String 
     * D = java.sql.Date 
     * DC = java.sql.Date Custom
     * DU =java.util.Date 
     * DUC = java.util.Date Custom
     *
     */
    DefiCustomColumnHeaderTable.DataTypes getTypeFromString(String pstr) {
        DefiCustomColumnHeaderTable.DataTypes res = null;
        if (pstr.compareTo("N") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.LONGTYPE;
        }
        if (pstr.compareTo("I") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.INTTYPE;
        }
        if (pstr.compareTo("L") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.LONGTYPE;
        }
        if (pstr.compareTo("D") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.DOUBLETYPE;
        }
        if (pstr.compareTo("F") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.NUMBERFIXEDTYPE;
        }
        if (pstr.compareTo("S") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.STRINGTYPE;
        }
        if (pstr.compareTo("D") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.SQLDATETYPE;
        }
        if (pstr.compareTo("DC") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.CUSTOMSQLDATETYPE;
        }
        if (pstr.compareTo("DU") == 0) {
            res = DefiCustomColumnHeaderTable.DataTypes.UTILDATETYPE;
        }
        if (pstr.compareTo("DUC") == 0) {
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

}
