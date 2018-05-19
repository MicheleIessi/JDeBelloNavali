package com.debellonavali.Screens.BattlePhase.Groups;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.debellonavali.Classes.Model.Ship;
import com.debellonavali.Constants;
import com.debellonavali.Tween.ActorAccessor;


/**
 * ShipInfoGroup is used in the battle phase to show various information about player's ships.
 * The standard layout is this:
 *
 * +---------+-----------------------+
 * |         |                       |
 * |  image  |       Ship Info       |
 * |         |                       |
 * +---------+-----------------------+
 */
public class ShipInfoGroup extends Group {

    private Table shipInfoTable;
    private Image shipImage;
    private Skin skin;
    private TextureAtlas atlas;
    private DragAndDrop dragAndDrop;
    private TweenManager tweenManager;

    public ShipInfoGroup(DragAndDrop dragAndDrop, Ship ship) {

        tweenManager = new TweenManager();
        this.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 8);

        this.atlas = new TextureAtlas(Gdx.files.internal(Constants.GRID_CELL_PACK));
        this.skin = new Skin(atlas);
        this.dragAndDrop = dragAndDrop;

        shipImage = new Image(skin.getDrawable("grid_center"));
        shipImage.setSize(Gdx.graphics.getHeight() / 10, Gdx.graphics.getHeight() / 10);
        shipInfoTable = new Table();
        shipInfoTable.setSize(this.getWidth(), this.getHeight());
        shipInfoTable.align(Align.topLeft);
        shipInfoTable.add(shipImage);

        shipInfoTable.add(new Label(String.valueOf(ship.getHitsReceived()),
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal(Constants.HEADER_INFO_FONT)), Color.WHITE)));

        Image newImage = new Image(skin.getDrawable("grid_center"));
        newImage.setSize(Gdx.graphics.getHeight() / 20, Gdx.graphics.getHeight() / 20);
        addWeaponSelectionFunctionality(newImage);

        Group attackSelectionGroup = new AttackSelectionGroup(ship);
        attackSelectionGroup.setVisible(false);

        shipInfoTable.add(newImage);
        addDragAndDropFunctionality();
        this.addActor(shipInfoTable);

    }

    private void addDragAndDropFunctionality() {

        dragAndDrop.addSource(new DragAndDrop.Source(shipImage) {
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject("Dragging");
                payload.setDragActor(new Label("Payload", new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
                payload.setValidDragActor(new Label("Valid", new Label.LabelStyle(new BitmapFont(), Color.LIME)));
                payload.setInvalidDragActor(new Label("Invalid", new Label.LabelStyle(new BitmapFont(), Color.RED)));

                return payload;
            }
        });
    }

    private void addWeaponSelectionFunctionality(Image attackImage) {
        attackImage.addListener(
                new ClickListener() {

                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        System.out.println("OK");
                    }
                }
        );
    }

}
