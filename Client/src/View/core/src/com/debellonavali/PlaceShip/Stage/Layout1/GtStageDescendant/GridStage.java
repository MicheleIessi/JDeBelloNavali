package com.debellonavali.PlaceShip.Stage.Layout1.GtStageDescendant;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.debellonavali.PlaceShip.Cell;
import com.debellonavali.PlaceShip.ConstantsPlaceShips;
import com.debellonavali.PlaceShip.Stage.PlaceShipStage;
import com.debellonavali.PlaceShip.Stage.zoneStage;

import java.util.ArrayList;

public class GridStage extends zoneStage {


    //Game grid
    private ArrayList<ArrayList<Cell>> grid;


    public GridStage(zoneStage parent) {
        super(parent);
        zoneTable.setDebug(true);
        zoneTable.pad(0);
    }

    @Override
    public void setUpLayout() {
        parent.getTable().add(zoneTable).width(ConstantsPlaceShips.GRID_ZONE_WIDTH).height(ConstantsPlaceShips.GRID_ZONE_HEIGHT);
        zoneTable.add(new Cell(0,0));
    }




}
