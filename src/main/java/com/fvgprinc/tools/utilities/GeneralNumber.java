package com.fvgprinc.tools.utilities;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author garfi
 */
public class GeneralNumber {
     public static boolean  isNumeric (String str)
     {
         boolean res;
         // Expresión regular que acepta enteros y decimales
        String regex = "-?\\d+(\\.\\d+)?";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        
        if (matcher.matches()) {
            if (str.contains(".")) {
                // System.out.println("La cadena es un número decimal: " + Double.parseDouble(str));
                res = true;
            } else {
                // System.out.println("La cadena es un número entero: " + Integer.parseInt(str));
                res = true;
            }
        } else {
            // System.out.println("La cadena no es un número");
            res = false;
        }
        return res;
     }
     
     /**
      * Generate a ramdom int number between 1.. maxNumber
      * @param maxNumer numero aleatorio, máximo retornado por la función
      * @return 
      */
     public static int genRandomInt(int maxNumer) {
         int res;
         
          Random random = new Random();
          res = random.nextInt(2000)  + 1;
          return res;
     }
}
