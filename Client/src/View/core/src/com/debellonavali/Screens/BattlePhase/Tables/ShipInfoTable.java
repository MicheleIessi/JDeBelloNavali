package com.debellonavali.Screens.BattlePhase.Tables;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.debellonavali.Screens.BattlePhase.Groups.ShipInfoGroup;
import com.debellonavali.Screens.BattlePhase.Groups.ShipInfoHeader;

public class ShipInfoTable extends Table {

    private Skin skin;
    private DragAndDrop dragAndDrop;

    public ShipInfoTable(Skin skin, DragAndDrop dragAndDrop) {
        super();
        this.skin = skin;
        this.dragAndDrop = dragAndDrop;
        this.align(Align.top);

        this.add(new ShipInfoHeader()).row();
    }


    public void addShipGroup(ShipInfoGroup shipGroup) {
        this.add(shipGroup).row();
    }
}
