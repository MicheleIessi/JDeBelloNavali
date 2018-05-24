package com.debellonavali.Screens.BattlePhase.Stages;

import com.debellonavali.Classes.Model.Battlefield;
import com.debellonavali.Classes.Model.DeBelloGame;
import com.debellonavali.Classes.Model.Ship;
import com.debellonavali.Screens.BattlePhase.Stages.Layout1.FieldStage;
import com.debellonavali.Screens.BattlePhase.Stages.Layout1.FleetListStage;
import com.debellonavali.Screens.PlaceShip.CellGrid;
import com.debellonavali.Screens.zoneStage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BattlePhaseStage extends zoneStage {

    private FleetListStage fleetListStage;
    private FieldStage fieldStage;
    private DeBelloGame deBelloGame;

    public BattlePhaseStage(DeBelloGame dbg){
        super();
        zoneTable.setFillParent(true);
        zoneTable.pad(0);

        this.deBelloGame=dbg;
        fleetListStage= new FleetListStage(this);
        fieldStage= new FieldStage(this,deBelloGame);

        stages.add(fleetListStage);
        stages.add(fieldStage);


    }

    @Override
    public void setUpLayout() {
        for (zoneStage stage : stages) {
            stage.setUpLayout();
        }
        addActor(zoneTable);
    }


    public void setFleet(HashMap fleet) {

        fleetListStage.setFleet(fleet);
        fieldStage.setFleet(fleet);
    }

    public ArrayList<ArrayList<CellGrid>> getEnemyField() {
        return fieldStage.getEnemyField();
    }
}
