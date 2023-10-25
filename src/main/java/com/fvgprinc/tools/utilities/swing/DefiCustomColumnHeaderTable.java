/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities.swing;

import com.fvgprinc.tools.string.MyCommonString;


/**
 *
 * @author fvargas
 */
public class DefiCustomColumnHeaderTable {

    public enum DataTypes {
        INTTYPE, LONGTYPE, DOUBLETYPE,
        NUMBERFIXEDTYPE,
        STRINGTYPE, 
        SQLDATETYPE, CUSTOMSQLDATETYPE,
        UTILDATETYPE, CUSTOMUTILDATETYPE,
        NODEFTYPE // tipo no definido.
    }
    
    // Index definition 
    // Estructura de la hilera de definición
    static final int IDCOLUMN = 0;
    static final int TYPECOLUMN = 1;
    static final int LABELCOLUMN = 2;
    static final int DBCOLNAMECOLUMN = 3;
    static final int PRECISIONCOLUMN = 4;
    static final int SCALECOLUMN = 5;
    static final int LENGTHCOLUMN = 6;
    static final int OBJNAMECOLUMN = 7;
    static final int EDITABLENAMECOLUMN =8; // editable indicator.
    
    // strucutre definition
    private String idColumn;
    private DataTypes type;
    private String labelColumn;
    private String dbColName;
    private int precision; // aplicable only to precision number null if no number with preccion
    private int scale; // aplicable only to precision number null if no number with preccion
    private int length; // por ejemplo a varchar2(xxxx)
    private String objectName; // if has a object name associated
    private boolean editable; // is column editable

    public DefiCustomColumnHeaderTable() {
        this.idColumn = MyCommonString.EMPTYSTR;
        this.type = DataTypes.NODEFTYPE;
        this.labelColumn = MyCommonString.EMPTYSTR;
        this.dbColName = MyCommonString.EMPTYSTR;
        this.precision = 0;
        this.scale = 0;
        this.length = 0;
        this.objectName = MyCommonString.EMPTYSTR;
        this.editable = false;
    }

    public DefiCustomColumnHeaderTable(String idColumn, DataTypes type, String labelColumn, int precision, 
                                      int scale, int length, String objectName, boolean editable) {
        this.idColumn = idColumn;
        this.type = type;
        this.labelColumn = labelColumn;
        this.precision = precision;
        this.scale = scale;
        this.length = length;
        this.objectName = objectName;
        this.editable = editable;
    }

    public String getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(String idColumn) {
        this.idColumn = idColumn;
    }

    public String getLabelColumn() {
        return labelColumn;
    }

    public void setLabelColumn(String labelColumn) {
        this.labelColumn = labelColumn;
    }

    public String getDbColName() {
        return dbColName;
    }

    public void setDbColName(String dbColName) {
        this.dbColName = dbColName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public DataTypes getType() {
        return type;
    }

    public void setType(DataTypes type) {
        this.type = type;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    
}
