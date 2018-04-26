package com.debellonavali.Screens.BattlePhase.Groups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.debellonavali.Constants;



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

    public ShipInfoGroup(DragAndDrop dragAndDrop) {

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


}
