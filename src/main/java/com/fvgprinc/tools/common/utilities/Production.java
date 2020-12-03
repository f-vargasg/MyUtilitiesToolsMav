/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fvgprinc.tools.common.utilities;

public class Production {

    private Lexeme leftSide;
    private String rightSide;

    public Production(Lexeme pLeftSide, String pRightSide) {
        this.leftSide = pLeftSide;
        this.rightSide = pRightSide;
    }

    public Production(String pleftSide, String pRightSide) {
        this.leftSide = new Lexeme(pleftSide, true);
        this.rightSide = pRightSide;
    }

    public Lexeme getLeftSide() {
        return this.leftSide;
    }

    public void setLeftSide(Lexeme leftSide) {
        this.leftSide = leftSide;
    }

    public String getRightSide() {
        return this.rightSide;
    }

    public void setRightSide(String rightSide) {
        this.rightSide = rightSide;
    }

    public boolean isInLeftSide(String pPattern) {
        boolean res = false;

        res = (pPattern.indexOf(this.leftSide.getLex()) >= 0);
        return res;
    }

    public boolean isInRightSide(String pPattern) {
        boolean res = false;

        res = (pPattern.indexOf(this.rightSide) >= 0);
        return res;
    }
    

    /**
     * Two production are equal <=> are equals the token of leftside and right
     * side string
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Production toCompare = (Production) obj;
        boolean res = (this.getLeftSide().getToken().compareToIgnoreCase(toCompare.getLeftSide().getToken()) == 0
                && this.getRightSide().compareToIgnoreCase(toCompare.getRightSide()) == 0);
        return res;

    }

}
