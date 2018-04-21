package com.debellonavali.PlaceShip.Stage.Layout1;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.debellonavali.PlaceShip.ConstantsPlaceShips;
import com.debellonavali.PlaceShip.Stage.Layout1.GtStageDescendant.GridStage;
import com.debellonavali.PlaceShip.Stage.Layout1.GtStageDescendant.TimerStage;
import com.debellonavali.PlaceShip.Stage.PlaceShipStage;
import com.debellonavali.PlaceShip.Stage.zoneStage;

import java.util.ArrayList;
import java.util.List;

public class GtStage extends zoneStage {


    private List<zoneStage> stages = new ArrayList<zoneStage>();


    public GtStage(zoneStage parent) {
        super(parent);
        zoneTable.pad(0);

        stages.add(new GridStage(this));
        stages.add(new TimerStage(this));

    }

    @Override
    public void setUpLayout() {
        for (zoneStage stage : stages) {
            stage.setUpLayout();
            zoneTable.row();
        }
        parent.getTable().add(zoneTable).width(ConstantsPlaceShips.GRIDTIMER_TABLE_WIDTH).height(ConstantsPlaceShips.GRIDTIMER_TABLE_HEIGHT);
    }


}
