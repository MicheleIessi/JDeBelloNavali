package Model.RangeStrategy;

public class AbstractRangeStrategy {

    private static int dimensionX = 7;
    private static int dimensionY = 7;

    protected AbstractRangeStrategy(int dimensionX, int dimensionY) {
        setDimensionX(dimensionX);
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
