/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fvgprinc.tools.utilities;

/**
 *
 * @author garfi
 */
public class MyDouble {

    public static final double EPSILON = 0.000001;

    public static boolean isZero(Double d) {
        boolean res = (Math.abs(d) <= EPSILON);
        return res;
    }
}