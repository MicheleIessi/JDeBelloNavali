package com.debellonavali.PlaceShip.Stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public abstract class zoneStage extends Stage {
    protected zoneStage parent;
    protected Table zoneTable;


    //In case the zone is the root zone
    public zoneStage(){
        super();
        parent=null;
        zoneTable= new Table();
    }

    public zoneStage(zoneStage parentStage) {
        super();
        this.parent = parentStage;
        zoneTable= new Table();
    }

    public Stage getParent() {
        return parent;
    }
    public Table getTable() {
        return zoneTable;
    }

    public abstract void setUpLayout();
}
