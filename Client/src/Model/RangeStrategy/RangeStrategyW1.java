package src.Model.RangeStrategy;

import java.util.ArrayList;

public class RangeStrategyW1 extends AbstractRangeStrategy implements IRangeStrategy {

    private static String rangeName = "Single";

    public RangeStrategyW1() {}

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

}
