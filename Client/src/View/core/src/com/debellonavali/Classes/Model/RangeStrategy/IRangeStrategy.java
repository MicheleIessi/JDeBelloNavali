package com.debellonavali.Classes.Model.RangeStrategy;

import java.util.ArrayList;

public interface IRangeStrategy {

    /**
     * Returns a List of involved coordinates
     * @param coordX The target X-Axis coordinate
     * @param coordY The target Y-Axis coordinate
     * @return The List of involved coordinates
     */
    ArrayList<int[]> getInvolvedSquares(int coordX, int coordY);

}
