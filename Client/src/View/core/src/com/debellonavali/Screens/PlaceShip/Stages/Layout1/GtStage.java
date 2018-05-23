package com.debellonavali.Screens.PlaceShip.Stages.Layout1;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.debellonavali.Screens.PlaceShip.CellGrid;
import com.debellonavali.Screens.zoneStage;

import com.debellonavali.Screens.PlaceShip.ConstantsPlaceShips;

import java.util.ArrayList;
import java.util.List;

public class GtStage extends zoneStage {



    public GtStage(zoneStage parent) {
        super(parent);
        zoneTable.pad(0);
        zoneTable.setDebug(true);
        stages.add(new com.debellonavali.Screens.PlaceShip.Stages.Layout1.GtStageDescendant.GridStage(this));
        stages.add(new com.debellonavali.Screens.PlaceShip.Stages.Layout1.GtStageDescendant.TimerStage(this));

    }

    @Override
    public void setUpLayout() {
        for (zoneStage stage : stages) {
            stage.setUpLayout();
            zoneTable.row();
        }
        parent.getTable().add(zoneTable).width(ConstantsPlaceShips.GRIDTIMER_TABLE_WIDTH).height(ConstantsPlaceShips.GRIDTIMER_TABLE_HEIGHT);
    }

    public Table getGridTable(){
        return ((com.debellonavali.Screens.PlaceShip.Stages.Layout1.GtStageDescendant.GridStage)stages.get(0)).getGridTable();
    }
    public ArrayList<ArrayList<CellGrid>> getGridArray(){
        return ((com.debellonavali.Screens.PlaceShip.Stages.Layout1.GtStageDescendant.GridStage)stages.get(0)).getGrid();
    }


}
