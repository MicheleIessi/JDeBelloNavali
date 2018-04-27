package com.debellonavali.PlaceShip.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.debellonavali.PlaceShip.Stage.Layout1.GtStage;
import com.debellonavali.PlaceShip.Stage.Layout1.ShipListStage;

import java.util.ArrayList;
import java.util.List;

public class PlaceShipStage extends zoneStage {

    private List<zoneStage> stages = new ArrayList<zoneStage>();

    private ShipListStage shipListStage;
    private GtStage gtStage;
    private final String civilization="RomanFleet";


    public PlaceShipStage(){
        super();
        zoneTable.setFillParent(true);
        zoneTable.pad(0);
        shipListStage= new ShipListStage(this);
        gtStage= new GtStage(this);
        stages.add(shipListStage);
        stages.add(gtStage);

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
        shipListStage.setUpDragAndDrop();
        addActor(zoneTable);
    }

    public ShipListStage getShipListStage() {
        return shipListStage;
    }

    public GtStage getGtStage() {
        return gtStage;
    }
}
