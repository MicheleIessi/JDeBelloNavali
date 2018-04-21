package com.debellonavali.PlaceShip.Stage.Layout1;


import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.debellonavali.PlaceShip.ConstantsPlaceShips;

import com.debellonavali.PlaceShip.Stage.zoneStage;

public class ShipListStage extends zoneStage {


    public ShipListStage(zoneStage parent) {
        super(parent);

    }

    @Override
    public void setUpLayout() {
        parent.getTable().add(zoneTable).width(ConstantsPlaceShips.SHIP_ZONE_WIDTH).height(ConstantsPlaceShips.SHIP_ZONE_HEIGHT);

    }
}
