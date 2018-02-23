package Model.RangeStrategy;

import java.util.ArrayList;

public class RangeStrategyW2 extends AbstractRangeStrategy implements IRangeStrategy {

    private static RangeStrategyW2 instance = null;
    private static String rangeName = "3-Row";

    protected RangeStrategyW2(int dimensionX, int dimensionY) {
        super(dimensionX, dimensionY);
    }

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

    public static RangeStrategyW2 getInstance() {
        if (instance == null) {
            instance = new RangeStrategyW2(getDimensionX(), getDimensionY());
        }
        return instance;
    }

}
