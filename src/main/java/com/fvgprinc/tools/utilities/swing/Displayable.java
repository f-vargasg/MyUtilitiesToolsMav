/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fvgprinc.tools.utilities.swing;

/**
 *
 * @author fvargas
 */
public interface Displayable {

      
    public String getDisplayName();
    
    //public ArrayList<String> getDataArray();
   // public String[] getDataArrayString();
    
    public Object[] getDataArrayObject();
    
    public void setDataObject(int numCol, Object obj);
    
}
