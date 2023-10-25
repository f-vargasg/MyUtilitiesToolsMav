/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities.swing;

import java.sql.Date;

/**
 *
 * @author fvargas
 */
public class CustomJavaSqlDate {
    private java.sql.Date valor;

    public CustomJavaSqlDate(Date valor) {
        this.valor = valor;
    }

    public Date getValor() {
        return valor;
    }

    public void setValor(Date valor) {
        this.valor = valor;
    }
    
    
}
