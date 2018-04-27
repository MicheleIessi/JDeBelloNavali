package com.debellonavali.PlaceShip.Stage.Layout1.GtStageDescendant;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.debellonavali.PlaceShip.CellGrid;
import com.debellonavali.PlaceShip.ConstantsPlaceShips;
import com.debellonavali.PlaceShip.Stage.zoneStage;

import java.util.ArrayList;

public class GridStage extends zoneStage {


    //Game grid
    private ArrayList<ArrayList<CellGrid>> grid;

    private Table fleetWeightTable;
    private Table gridTable;


    public GridStage(zoneStage parent) {
        super(parent);
        zoneTable.pad(0);
        fleetWeightTable= new Table();
        gridTable= new Table();

        grid= new ArrayList<>(8);
        for(int i=0; i<8; i++) {
            grid.add(new ArrayList<>(8));
        }


    }


    @Override
    public void setUpLayout() {
        zoneTable.add(fleetWeightTable).width(ConstantsPlaceShips.FLEET_WEIGHT_TABLE_WIDTH).height(ConstantsPlaceShips.FLEET_WEIGHT_TABLE_HEIGHT);
        zoneTable.row();
        zoneTable.add(gridTable).width(ConstantsPlaceShips.GRID_TABLE_WIDTH).height(ConstantsPlaceShips.GRID_TABLE_HEIGHT);
        parent.getTable().add(zoneTable).width(ConstantsPlaceShips.GRID_ZONE_WIDTH).height(ConstantsPlaceShips.GRID_ZONE_HEIGHT);


        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                grid.get(i).add(new CellGrid(i,j));
                gridTable.addActor(grid.get(i).get(j));
            }
        }



    }

    public ArrayList<ArrayList<CellGrid>> getGrid() {
        return grid;
    }

    public Table getFleetWeightTable() {
        return fleetWeightTable;
    }

    public Table getGridTable() {
        return gridTable;
    }
}
