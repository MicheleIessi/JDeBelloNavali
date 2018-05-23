package com.debellonavali.Screens.PlaceShip.Stages;

import com.badlogic.gdx.Gdx;
import com.debellonavali.Screens.PlaceShip.Stages.Layout1.ShipListStage;
import com.debellonavali.Screens.PlaceShip.Stages.Layout1.GtStage;
import com.debellonavali.Screens.zoneStage;

import java.util.ArrayList;
import java.util.List;

public class PlaceShipStage extends zoneStage {



    private final String civilization="RomanFleet";



    public PlaceShipStage(){
        super();

        zoneTable.setFillParent(true);
        zoneTable.pad(0);

        stages.add(new ShipListStage(this));
        stages.add(new GtStage(this));

    }

    public String getCivilization() {
        return civilization;
    }

    public List<zoneStage> getStages() {
        return stages;
    }


    @Override
    public void setUpLayout() {
        Gdx.input.setInputProcessor(this);

        for (zoneStage stage : stages) {
            stage.setUpLayout();
        }

        addActor(zoneTable);
    }

    public ShipListStage getShipListStage() {
        return (ShipListStage)this.stages.get(0);
    }

    public GtStage getGtStage() {
        return (GtStage) this.stages.get(1);
    }

    @Override
    public void dispose() {
        super.dispose();
        stages.get(0).dispose();
        stages.get(1).dispose();
    }
}
