package src.Model.RangeStrategy;

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
