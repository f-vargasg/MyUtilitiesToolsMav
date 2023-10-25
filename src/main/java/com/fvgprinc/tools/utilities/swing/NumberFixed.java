/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities.swing;

/**
 *
 * @author fvargas
 */
public class NumberFixed {
    Double valor;

    public NumberFixed() {
        this.valor = 0.00;
    }
    
    public NumberFixed(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
   
    
    
}
