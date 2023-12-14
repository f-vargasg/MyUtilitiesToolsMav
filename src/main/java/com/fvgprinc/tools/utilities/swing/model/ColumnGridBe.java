package com.fvgprinc.tools.utilities.swing.model;

/**
 *
 * @author garfi
 */
public class ColumnGridBe {

    private int idColumn;

    private String lblColumn;

    private String nomFisico;
    
    public ColumnGridBe() {
    }

    public ColumnGridBe(int idColumn, String lblColumn, String nomFisico) {
        this.idColumn = idColumn;
        this.lblColumn = lblColumn;
        this.nomFisico = nomFisico;
    }

    public int getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(int idColumn) {
        this.idColumn = idColumn;
    }

    public String getLblColumn() {
        return lblColumn;
    }

    public void setLblColumn(String lblColumn) {
        this.lblColumn = lblColumn;
    }

    public String getNomFisico() {
        return nomFisico;
    }

    public void setNomFisico(String nomFisico) {
        this.nomFisico = nomFisico;
    }

    @Override
    public String toString() {
        return Integer.toString(idColumn) + "  -  " + lblColumn;
    }
    
    
    

}
