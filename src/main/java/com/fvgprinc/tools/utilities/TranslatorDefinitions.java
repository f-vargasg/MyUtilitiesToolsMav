/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fvgprinc.tools.utilities;

/**
 * The lexemes are allowed:
 * <%lexeme> replacement with the varialbles defined for lexeme
 * <~nameOfRule>  execute other rule named "nameOfRule"
 * @author fvargas
 */
public class TranslatorDefinitions {
	public static final int NULL_RULE = -1;
	public static final String SEPRULES = "=>";
	public static final String DEFAOPENLEX ="<%";
	public static final String DEFACLOSELEX = ">";
        public static final String APPLYOTHERRULESOPENLEX = "<~";

}