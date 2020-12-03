/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.common.utilities;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import com.fvgprinc.tools.common.string.MyCommonString;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * For this class, a Lexeme, is a identifier, wrap in openLex, closeLex
 * a token is the string enwrap in openLex, closeLex
 * @author fvargas
 */


public class Lexeme {

    private String lex;
    private String openLex;
    private String closeLex;

    /**
     * Get the value of closeLex
     *
     * @return the value of closeLex
     */
    public String getCloseLex() {
        return closeLex;
    }

    /**
     * Get the value of openLex
     *
     * @return the value of openLex
     */
    public String getOpenLex() {
        return openLex;
    }

    public void setLex(String lex) {
        this.lex = lex;
    }

    public String getLex() {
        return this.lex;
    }

    /**
     * Create a new Lexeme. In this class, if the lexeme don't has a initial and end
     * symbols, the parameter must be true.
     * @param pIdentifier
     * @param pEnwrap
     * @deprecated 
     * Example:
     * String strlex = "Hola";
     *
     * Lexeme lex = new Lexeme(strlex, true);
     *
     * Conversly
     * String strlex = "<%hola>"
     * hola is a identifier
     * Lexeme lex = new Lexeme(strlex, false);
     */

    public Lexeme(String pIdentifier, boolean pEnwrap) {
        if (pEnwrap) {
            this.lex = TranslatorDefinitions.DEFAOPENLEX + pIdentifier + TranslatorDefinitions.DEFACLOSELEX;
        } else {
            this.lex = pIdentifier;
        }
        this.openLex = TranslatorDefinitions.DEFAOPENLEX;
        this.closeLex = TranslatorDefinitions.DEFACLOSELEX;

    }

    public Lexeme(String pIdentifier, String pOpenLex, String pCloseLex, boolean pEnwrap) {
        if (pEnwrap) {
            this.lex = pOpenLex + pIdentifier + pCloseLex;
        } else {
            this.lex = pIdentifier;
        }

        this.openLex = pOpenLex;
        this.closeLex = pCloseLex;
    }

    /**
     *
     * @return
     */
    public String getToken() {
        String res = MyCommonString.EMPTYSTR;
        int pos, pos1;

        pos = this.lex.indexOf(TranslatorDefinitions.DEFAOPENLEX);
        if (pos >= 0) {
            pos1 = lex.indexOf(TranslatorDefinitions.DEFACLOSELEX);
            if (pos1 > 0) {// ok
                pos += TranslatorDefinitions.DEFAOPENLEX.length();
                if (Math.abs(pos1 - pos) > 0) {
                    res = this.lex.substring(pos, pos1);
                }
            }
        }
        return res;
    }

    /**
     *
     * @param pStr
     * @return
     */
    public static String toFullLexeme(String pStr) {
        String res = MyCommonString.EMPTYSTR;

        res = TranslatorDefinitions.DEFAOPENLEX + pStr + TranslatorDefinitions.DEFACLOSELEX;
        return res;
    }

    /**
     *
     * @param pLine
     * @param pPos
     * @param pLexeme
     * @return
     */
    public static int getLexeme(String pLine,
            int pPos,
            Object pLexeme) {
        String scrap = MyCommonString.EMPTYSTR;
        String wLine = MyCommonString.EMPTYSTR;
        int pos, pos1;
        int tope, i;
        boolean fin;

        wLine = (String) pLine;
        tope = wLine.length();
        fin = false;
        i = pPos;
        scrap = MyCommonString.EMPTYSTR;

        while (!fin && i < tope) {
            pos = wLine.indexOf(TranslatorDefinitions.DEFAOPENLEX, i);
            if (pos >= 0) {  // found!!!
                if (pos < tope) {
                    pos1 = wLine.indexOf(TranslatorDefinitions.DEFACLOSELEX, pos + 1);
                    if (pos1 > 0) {
                        scrap = wLine.substring(pos + TranslatorDefinitions.DEFACLOSELEX.length(), pos1 - (pos + TranslatorDefinitions.DEFACLOSELEX.length()));
                        fin = true;
                        i = pos1 + TranslatorDefinitions.DEFACLOSELEX.length();
                    } else {
                        ++i; // don't forget the right side
                    }
                } else // wpos < tope, don't found leftside
                {
                    ++i;
                }
            } else { // don't found
                fin = true;
                i = wLine.length() + 1;
            }
        }
        pLexeme = scrap;
        return i;
    }

    /**
     *
     * @param arrStr
     * @return
     */
    public static ArrayList<String> getAllLexemes(String[] arrStr) {
        ArrayList<String> lstLexemes = new ArrayList<String>();
        String fullLexeme = MyCommonString.EMPTYSTR;
        int pos, tope;

        for (int i = 0; i < arrStr.length; i++) {
            pos = 0;
            tope = arrStr[i].length();
            while (pos < tope) {
                pos = getLexeme(arrStr[i], pos, fullLexeme);
                if (fullLexeme.compareTo(MyCommonString.EMPTYSTR) != 0) {
                    fullLexeme = toFullLexeme(fullLexeme);
                    if (!lstLexemes.contains(fullLexeme)) {
                        lstLexemes.add(fullLexeme);
                    }
                }
            }
        }
        return lstLexemes;
    }

    /**
     *
     * @param token
     * @return
     */
    public static String getLexemeFromToken(String token) {
        String scrap;

        scrap = token.replace(TranslatorDefinitions.DEFAOPENLEX, MyCommonString.EMPTYSTR);
        scrap = scrap.replace(TranslatorDefinitions.DEFACLOSELEX, MyCommonString.EMPTYSTR);
        return scrap;

    }

    /**
     *
     * @param lstLexemes
     * @return
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public static String SerializeLexemes(ArrayList<String> lstLexemes) throws ParserConfigurationException, TransformerException {
        String res = MyCommonString.EMPTYSTR;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element translatorEl = document.createElement("translator"); // root element
        document.appendChild(translatorEl);
        for (String scrap : lstLexemes) {
            Element production = document.createElement("Production");
            Element leftSide = document.createElement("leftSide");
            leftSide.setTextContent(Lexeme.getLexemeFromToken(scrap));
            Element rightSide = document.createElement("rightSide");
            rightSide.setTextContent(MyCommonString.EMPTYSTR);
            production.appendChild(leftSide);
            production.appendChild(rightSide);
            translatorEl.appendChild(production);
        } // for
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);
        res = result.toString();
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        
        res = (this.getToken().compareToIgnoreCase(((Lexeme)obj).getToken()) == 0);
        return res;
    }
    
    
}
