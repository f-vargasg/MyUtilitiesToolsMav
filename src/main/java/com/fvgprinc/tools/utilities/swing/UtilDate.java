
package com.fvgprinc.tools.utilities.swing;

import java.util.Date;

/**
 *
 * @author garfi
 */
public class UtilDate {

    Date valor;

    public UtilDate() {
        this.valor = new Date();
    }

    public UtilDate(Date valor) {
        this.valor = valor;
    }

    public Date getValor() {
        return valor;
    }

    public void setValor(Date valor) {
        this.valor = valor;
    }

   
}
