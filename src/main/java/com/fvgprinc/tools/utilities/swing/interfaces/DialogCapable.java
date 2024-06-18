
package com.fvgprinc.tools.utilities.swing.interfaces;

/**
 *
 * @author Ryzen9-Gaming
 */
public interface DialogCapable {

    void showDialog();               // Método para mostrar el diálogo

    void okDialog();                // Método para cerrar el diálogo
    
    void cancelDialog();                // Método para cerrar el diálogo

    String getTitle();           // Método para obtener el título del diálogo

    void setTitle(String titulo); // Método para establecer el título del diálogo
    
    int getResult();  
}
