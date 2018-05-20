package com.debellonavali.Screens.BattlePhase.Groups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.debellonavali.Constants;


public class ShipInfoHeader extends Group {

    private BitmapFont headerFont;
    private Label.LabelStyle headerLabelStyle;
    private Label headerLabel;
    private Table headerTable;

    public ShipInfoHeader() {

        this.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 8);

        headerFont = new BitmapFont(Gdx.files.internal(Constants.HEADER_INFO_FONT));
        headerLabelStyle = new Label.LabelStyle(headerFont, Color.WHITE);
        headerLabel = new Label(Constants.BATTLE_LABEL_HEADER, headerLabelStyle);

        headerTable = new Table();
        headerTable.setFillParent(true);
        headerTable.align(Align.center);
        headerTable.add(headerLabel);

        this.debug();
        this.addActor(headerTable);
    }

}
