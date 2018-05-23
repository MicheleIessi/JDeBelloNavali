package com.debellonavali.Screens.PlaceShip.Stages.Layout1.GtStageDescendant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.debellonavali.Screens.zoneStage;

public class TimerStage extends zoneStage {

    private Skin skin;
    private TextureAtlas atlas;
    private BitmapFont buttonFont;
    private TextButton playButton, surrendButton;
    private TextButton.TextButtonStyle textButtonStyle,surrendButtonStyle;


    public TimerStage(zoneStage parent) {
        super( parent);
        zoneTable.pad(0);


    }

    @Override
    public void setUpLayout() {
        parent.getTable().add(zoneTable).width(com.debellonavali.Screens.PlaceShip.ConstantsPlaceShips.TIMER_ZONE_WIDTH).height(com.debellonavali.Screens.PlaceShip.ConstantsPlaceShips.TIMER_ZONE_HEIGHT);
        zoneTable.setBounds(0,0,zoneTable.getWidth(),zoneTable.getHeight());

        atlas = new TextureAtlas(Gdx.files.internal(com.debellonavali.Screens.PlaceShip.ConstantsPlaceShips.START_BATTLE_BUTTON_PACK));
        skin = new Skin(atlas);

        buttonFont = new BitmapFont(Gdx.files.internal(com.debellonavali.Screens.PlaceShip.ConstantsPlaceShips.PLACE_SHIP_BUTTON_FONT), false);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = buttonFont;
        textButtonStyle.up = skin.getDrawable("buttonUP");
        textButtonStyle.down = skin.getDrawable("buttonDOWN");

        atlas = new TextureAtlas(Gdx.files.internal(com.debellonavali.Screens.PlaceShip.ConstantsPlaceShips.SURREND_BUTTON_PACK));
        skin = new Skin(atlas);

        surrendButtonStyle = new TextButton.TextButtonStyle();
        surrendButtonStyle.font = buttonFont;
        surrendButtonStyle.up = skin.getDrawable("buttonUP");
        surrendButtonStyle.down = skin.getDrawable("buttonDOWN");

        playButton = new TextButton(com.debellonavali.Screens.PlaceShip.ConstantsPlaceShips.START_BATTLE_BUTTON_TEXT, textButtonStyle);
        surrendButton = new TextButton(com.debellonavali.Screens.PlaceShip.ConstantsPlaceShips.START_BATTLE_BUTTON_TEXT, surrendButtonStyle);



        zoneTable.add(surrendButton).expand().left().width(100).height(95);
        zoneTable.add(playButton).expand().right().width(100).height(95);

    }


}
