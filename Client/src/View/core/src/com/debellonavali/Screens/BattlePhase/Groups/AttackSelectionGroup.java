package com.debellonavali.Screens.BattlePhase.Groups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.debellonavali.Classes.Model.Ship;
import com.debellonavali.Classes.Model.Weapon;
import com.debellonavali.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Group that allows the selection of the weapon
 */
public class AttackSelectionGroup extends Group {

    Table selectionTable;
    Skin skin;
    TextureAtlas atlas;

    public AttackSelectionGroup(Ship ship) {
        atlas = new TextureAtlas(Gdx.files.internal(Constants.TABLE_BORDER_PACK));
        skin = new Skin(atlas);
        selectionTable = new Table();
        selectionTable.debug();
        selectionTable.setBounds(0, 0, 100, 100);
        selectionTable.setBackground(skin.getDrawable("border"));
        HashMap<Integer, Weapon> weapons = ship.getWeapons();
        this.addActor(selectionTable);

    }



}
