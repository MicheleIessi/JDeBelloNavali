package src.Model.RangeStrategy;

import java.util.ArrayList;


public class RangeStrategyW3 extends AbstractRangeStrategy implements IRangeStrategy {

    private static String rangeName = "2-Square";

    public RangeStrategyW3() {}

    @Override
    public ArrayList getInvolvedSquares(int coordX, int coordY) {

        ArrayList<int[]> involvedSquares = new ArrayList<>();

        int[] leftUpperSquare = {coordX, coordY};
        involvedSquares.add(leftUpperSquare);

        if((coordX+1) <= getDimensionX()) {
            int[] rightUpperSquare = {(coordX+1), coordY};
            involvedSquares.add(rightUpperSquare);
        }
        if((coordY+1) <= getDimensionY()) {

            int[] leftLowerSquare = {coordX, (coordY+1)};
            involvedSquares.add(leftLowerSquare);

            if((coordX+1) <= getDimensionX()) {
                int[] rightLowerSquare = {(coordX+1),(coordY+1)};
                involvedSquares.add(rightLowerSquare);
            }
        }

        return involvedSquares;
    }

}
