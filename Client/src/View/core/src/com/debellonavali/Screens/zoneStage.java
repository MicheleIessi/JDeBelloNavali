package com.debellonavali.Screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.List;


public abstract class zoneStage extends Stage {
    protected zoneStage parent;
    protected Table zoneTable;

    protected List<zoneStage> stages = new ArrayList<>();

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

    protected void setZoneTableDimension(int width, int height){
        parent.getTable().add(zoneTable).width(width).height(height);

    }
}
