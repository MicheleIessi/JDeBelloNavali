package com.debellonavali.Classes.Model.RangeStrategy;

import java.util.ArrayList;

/**
 * RangeStrategyW1 models a single square attack
 */
public class RangeStrategyW1 extends AbstractRangeStrategy implements IRangeStrategy {

    private static String rangeName = "Single";

    public RangeStrategyW1() {}

    public static String getRangeName() {
        return rangeName;
    }

    @Override
    public ArrayList<int[]> getInvolvedSquares(int coordX, int coordY) {
        ArrayList<int[]> involvedSquares = new ArrayList<>();
        int[] centerSquare = {coordX, coordY};
        involvedSquares.add(centerSquare);
        return involvedSquares;
    }

}
