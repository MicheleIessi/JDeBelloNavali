package com.debellonavali.Classes.Model.RangeStrategy;

/**
 * AbstractRangeStrategy contains information about the logic used by the IRangeStrategy objects
 * extending this abstract class. For now, the information is about the dimension of the battlefield.
 * If the dimension of the battlefield changes from a 8x8 grid, IRangeStrategy objects will be able
 * to operate correctly by changing the dimensionX and dimensionY variables of this class at runtime.
 */
public class AbstractRangeStrategy {

    private static int dimensionX = 7;
    private static int dimensionY = 7;

    public AbstractRangeStrategy() {}

    public AbstractRangeStrategy(int dimensionX, int dimensionY) {
        setDimensionX(dimensionX);
        setDimensionY(dimensionY);
    }

    public void setDimensionX(int newDimensionX) {
        dimensionX = newDimensionX;
    }

    public static int getDimensionX() {
        return dimensionX;
    }

    public void setDimensionY(int newDimensionY) {
        dimensionY = newDimensionY;
    }

    public static int getDimensionY() {
        return dimensionY;
    }

}
