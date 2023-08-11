/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.utilities;

import com.fvgprinc.tools.string.MyCommonString;
import com.fvgprinc.tools.string.MyCommonXML;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Translator {

    private ArrayList<Production> lstProductions;

    public Translator() {
        lstProductions = new ArrayList<Production>();
    }

    public ArrayList<Production> getLstProductions() {
        return lstProductions;
    }

    public void setLstProductions(ArrayList<Production> lstProductions) {
        this.lstProductions = lstProductions;
    }

    public int getNumProductions() {
        return lstProductions.size();
    }

    public void addProduction(Lexeme pLeftSide, String pRightSide) {
        Production prod = new Production(pLeftSide, pRightSide);

        lstProductions.add(prod);
    }

    public void addProduction(Production prod) {
        lstProductions.add(prod);
    }

    public ArrayList<String> lexemes() {
        ArrayList<String> res = new ArrayList<String>();

        for (String lexeme : res) {
            res.add(lexeme);
        }
        return res;
    }

    public Production findLeftSideStm(String stm) {
        Production prod = null;
        int i = 0;
        boolean found = false;

        while ((i < lstProductions.size()) && !found) {
            found = lstProductions.get(i).isInLeftSide(stm);
            if (found) {
                prod = lstProductions.get(i);
            } else {
                ++i;
            }
        }
        return prod;
    }

    public Production findRightSide(String pstm) {
        Production prod = null;
        int i = 0;
        boolean found = false;

        while ((i < lstProductions.size()) && !found) {
            found = lstProductions.get(i).isInRightSide(pstm);
            if (found) {
                prod = lstProductions.get(i);
            } else {
                ++i;
            }
        }
        return prod;
    }

    /**
     *
     * @param plexeme
     * @return
     */
    public boolean isTerminal(Lexeme plexeme) {
        boolean res;

        res = (this.findLeftSideStm(plexeme.getLex()) != null)
                && (this.findRightSide(plexeme.getLex()) == null);

        return res;
    }

    /**
     *
     * @param plinea
     * @param prod
     * @return
     */
    public boolean readProductionFromLine(String pline, Production prod) {
        String scrapLeft, scrapRight;
        int pos;
        boolean could;

        could = false;
        pos = pline.indexOf(TranslatorDefinitions.SEPRULES, 0);
        if (pos > 0) {
            scrapLeft = pline.substring(0, pos).trim();
            scrapRight = pline.substring(
                    pos + TranslatorDefinitions.SEPRULES.length(),
                    pline.length()
                    - (pos + TranslatorDefinitions.SEPRULES.length())).trim();
            prod = new Production(new Lexeme(scrapLeft, false), scrapRight);
            could = true;
        }
        return could;
    }

    public void readProductionFromArrStr(String[] arrStr) {
        Production prodI = null;
        boolean couldRead = false;

        for (String scrap : arrStr) {
            if (scrap.trim().compareTo(MyCommonString.EMPTYSTR) != 0) {
                couldRead = readProductionFromLine(scrap, prodI);
            }
        }
    }

    /**
     * Read productions from file
     *
     * @param fname
     * @throws IOException
     */
    public void readProductionsFromFile(String fname) throws IOException {
        FileInputStream fstream = new FileInputStream(fname);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        Production prodI = null;
        String strLine;
        boolean couldRead;

        // read file line by line
        while ((strLine = br.readLine()) != null) {
            if (strLine.trim().compareTo(MyCommonString.EMPTYSTR) != 0) {
                couldRead = readProductionFromLine(strLine, prodI);
                this.addProduction(prodI);
            }
        }
    }

    /**
     * Read productions from file
     *
     * @param fname
     * @throws IOException
     */
    public void readProductionsFromResource(String strResourceName) throws IOException {
        //FileInputStream fstream = new FileInputStream(strResourceName);
        InputStream inStr = this.getClass().getResourceAsStream(strResourceName);
        DataInputStream in = new DataInputStream(inStr);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        Production prodI = null;
        String strLine;
        boolean couldRead;

        // read file line by line
        while ((strLine = br.readLine()) != null) {
            if (strLine.trim().compareTo(MyCommonString.EMPTYSTR) != 0) {
                couldRead = readProductionFromLine(strLine, prodI);
                this.addProduction(prodI);
            }
        }
    }

    /**
     * Read productions from xml String with Sample:
     * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
     * <translator>
     * <Production>
     * <leftSide>fecTransac</leftSide>
     * <rightSide>25/06/2008</rightSide>
     * </Production>
     * <Production>
     * <leftSide>mtoPrecio</leftSide>
     * <rightSide>131.81</rightSide>
     * </Production>
     * </translator>
     *
     * @param pXml
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void readProductionsFromXMLString(String pXml)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(pXml));
        String strLSide = MyCommonString.EMPTYSTR,
                strRSide = MyCommonString.EMPTYSTR;

        Document doc = db.parse(is);
        NodeList nodeLst = doc.getElementsByTagName("Production");
        for (int s = 0; s < nodeLst.getLength(); s++) {
            Node fstNode = nodeLst.item(s);
            if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                // get the leftside
                Element fstElmnt = (Element) fstNode;
                NodeList leftSideLst = fstElmnt.getElementsByTagName("leftSide");
                if (leftSideLst.getLength() > 0) {
                    Element lsElmnt = (Element) leftSideLst.item(0);
                    NodeList lSide = lsElmnt.getChildNodes();
                    strLSide = MyCommonString.EMPTYSTR;
                    if (lSide.getLength() > 0) {
                        strLSide = ((Node) lSide.item(0)).getNodeValue();
                        // convert escape characters to normal
                        strLSide = MyCommonXML.fixXmlContentToStr(strLSide);
                    }
                }

                // get the rightSide
                NodeList rightSideLst = fstElmnt.getElementsByTagName("rightSide");
                if (rightSideLst.getLength() > 0) {
                    Element rsElmnt = (Element) rightSideLst.item(0);
                    NodeList rSide = rsElmnt.getChildNodes();
                    strRSide = MyCommonString.EMPTYSTR;
                    if (rSide.getLength() > 0) {
                        strRSide = ((Node) rSide.item(0)).getNodeValue();
                        // convert escape characters to normal
                        strRSide = MyCommonXML.fixXmlContentToStr(strRSide);
                    }
                }

                if (strLSide.compareTo(MyCommonString.EMPTYSTR) != 0
                        || strRSide.compareTo(MyCommonString.EMPTYSTR) != 0) {
                    Lexeme lex = new Lexeme(strLSide, true);
                    this.addProduction(new Production(lex, strRSide));
                }
            }
        }
    }

    /**
     *
     * Read productions with xml file with Sample:
     * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
     * <translator>
     * <Production>
     * <leftSide>fecTransac</leftSide>
     * <rightSide>25/06/2008</rightSide>
     * </Production>
     * <Production>
     * <leftSide>mtoPrecio</leftSide>
     * <rightSide>131.81</rightSide>
     * </Production>
     * </translator>
     *
     * @param pFileName
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public void readProductionsFromXMLFile(String pFileName)
            throws ParserConfigurationException, SAXException, IOException {

        Lexeme lex;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(pFileName);
        doc.getDocumentElement().normalize();
        NodeList nodeLst = doc.getElementsByTagName("Production");
        for (int s = 0; s < nodeLst.getLength(); s++) {
            Node fstNode = nodeLst.item(s);
            if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                // get the leftside
                Element fstElmnt = (Element) fstNode;
                NodeList leftSideLst = fstElmnt.getElementsByTagName("leftSide");
                Element lsElmnt = (Element) leftSideLst.item(0);
                NodeList lSide = lsElmnt.getChildNodes();
                String strLSide = ((Node) lSide.item(0)).getNodeValue();

                NodeList rightSideLst = fstElmnt.getElementsByTagName("rightSide");
                Element rsElmnt = (Element) rightSideLst.item(0);
                NodeList rSide = rsElmnt.getChildNodes();
                String strRSide = ((Node) rSide.item(0)).getNodeValue();
                lex = new Lexeme(strLSide, true);
                this.addProduction(new Production(lex, strRSide));
            }
        }
    }

    /**
     * Apply the productions to string
     *
     * @param stm
     * @return string with lexemes replaced.
     */
    public String applyProductions2Stm(String stm) {
        Production prodI = null;
        String scrap = stm;
        boolean found;

        prodI = this.findLeftSideStm(scrap);
        found = (prodI != null);
        while (found) {
            //scrap = scrap.replaceAll(prodI.getLeftSide().getLex(), prodI.getRightSide());
            scrap = scrap.replace(prodI.getLeftSide().getLex(), prodI.getRightSide());
            prodI = this.findLeftSideStm(scrap);
            found = (prodI != null);
        }
        return scrap;
    }

    /**
     * Get a File and apply the productions.
     *
     * @param fname
     * @return A document (line by line in item list)
     * @throws IOException
     */
    public ArrayList<String> applyProductions2File(String fname)
            throws IOException {
        ArrayList<String> ls = new ArrayList<String>();

        FileInputStream fstream = new FileInputStream(fname);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine, scrapOut;
        // read file line by line
        while ((strLine = br.readLine()) != null) {
            if (strLine.trim().compareTo(MyCommonString.EMPTYSTR) != 0) {
                scrapOut = this.applyProductions2Stm(strLine);
                ls.add(scrapOut);
            }
        }
        return ls;
    }

    /**
     * Get a File and apply the productions.
     *
     * @param fname
     * @return A document (line by line in item list)
     * @throws IOException
     */
    public ArrayList<String> applyProductions2Stream(InputStream pFstream)
            throws IOException {
        ArrayList<String> ls = new ArrayList<String>();

        DataInputStream in = new DataInputStream(pFstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine, scrapOut;
        // read file line by line
        while ((strLine = br.readLine()) != null) {
            if (strLine.trim().compareTo(MyCommonString.EMPTYSTR) != 0) {
                scrapOut = this.applyProductions2Stm(strLine);
                ls.add(scrapOut);
            }
        }
        return ls;
    }

    public ArrayList<Lexeme> getLSideLexemes() {
        ArrayList<Lexeme> res = new ArrayList<Lexeme>();
        
        for (Production production : this.lstProductions) {
            res.add(production.getLeftSide());
        }
        return res;
    }

    public ArrayList<String> getRSideLexemes() {
        ArrayList<String> res = new ArrayList<String>();
        
        for (Production production : this.lstProductions) {
            res.add(production.getRightSide());
        }

        return res;
    }
}
