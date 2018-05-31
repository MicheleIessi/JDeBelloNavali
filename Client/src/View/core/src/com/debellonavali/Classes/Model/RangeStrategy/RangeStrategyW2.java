package com.debellonavali.Classes.Model.RangeStrategy;

import java.util.ArrayList;

/**
 * RangeStrategyW2 models a horizontal three-square attack
 */
public class RangeStrategyW2 extends AbstractRangeStrategy implements IRangeStrategy {

    private static String rangeName = "3-Row";

    public RangeStrategyW2() {}

    @Override
    public ArrayList getInvolvedSquares(int coordX, int coordY) {

        ArrayList<int[]> involvedSquares = new ArrayList<>();
        int[] leftSquare = {coordX, coordY}; // center square
        involvedSquares.add(leftSquare);

        if((coordX+1) <= getDimensionX()) {
            int[] centerSquare = {(coordX+1), coordY};
            involvedSquares.add(centerSquare);
        }

        if((coordX+2) <= getDimensionX()) {
            int[] rightSquare = {(coordX+2), coordY};
            involvedSquares.add(rightSquare);
        }

        return involvedSquares;
    }
}
