package com.debellonavali.PlaceShip.Stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.debellonavali.PlaceShip.Stage.Layout1.GtStage;
import com.debellonavali.PlaceShip.Stage.Layout1.ShipListStage;

import java.util.ArrayList;
import java.util.List;

public class PlaceShipStage extends zoneStage {

    private List<zoneStage> stages = new ArrayList<zoneStage>();



    public PlaceShipStage(){
        super();
        zoneTable.setFillParent(true);
        zoneTable.setDebug(true);
        zoneTable.pad(0);

        stages.add(new ShipListStage(this));
        stages.add(new GtStage(this));

    }


    public List<zoneStage> getStages() {
        return stages;
    }


    @Override
    public void setUpLayout() {
        for (zoneStage stage : stages) {
            stage.setUpLayout();
        }
        addActor(zoneTable);
    }

}
