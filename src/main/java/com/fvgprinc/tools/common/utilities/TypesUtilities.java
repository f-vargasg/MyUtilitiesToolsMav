/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fvgprinc.tools.common.utilities;

/**
 *
 * @author garfi
 */
public class TypesUtilities {
    
    public static boolean isNumeric (String strNum)
    {
        if (strNum == null) {
            return false;
        }
        try   {
            double d = Double.parseDouble(strNum);
            
        }
        catch (NumberFormatException nfe) {
          return false;      
        }
        return true;
    }
            
}
