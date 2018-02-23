package Model.RangeStrategy;

import Model.Square;

import java.util.ArrayList;

public class RangeStrategyW1 extends AbstractRangeStrategy implements IRangeStrategy {

    private static RangeStrategyW1 instance = null;
    private static String rangeName = "Single";

    protected RangeStrategyW1(int dimensionX, int dimensionY) {
        super(dimensionX, dimensionY);
    }

    public static String getRangeName() {
        return rangeName;
    }

    @Override
    public ArrayList<int[]> getInvolvedSquares(int coordX, int coordY) {
        // ritornare array di celle colpite
        ArrayList<int[]> involvedSquares = new ArrayList<>();

        int[] centerSquare = {coordX, coordY};
        involvedSquares.add(centerSquare);

        return involvedSquares;
    }

    public static RangeStrategyW1 getInstance() {
        if (instance == null) {
            instance = new RangeStrategyW1(getDimensionX(), getDimensionY());
        }
        return instance;
    }

}
