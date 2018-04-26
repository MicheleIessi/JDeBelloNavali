package com.debellonavali.Screens.BattlePhase.Tables;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.debellonavali.Constants;

public class PlayerGridTable extends GridTable {

    private Skin skin;
    private DragAndDrop dragAndDrop;

    public PlayerGridTable(Skin skin, DragAndDrop dragAndDrop) {
        super();
        this.skin = skin;
        this.dragAndDrop = dragAndDrop;

        this.setPosition(Constants.GRID_MAXIMIZED_POS_X, Constants.GRID_MAXIMIZED_POS_Y);
        this.setSize(Constants.GRID_MAXIMIZED_WIDTH, Constants.GRID_MAXIMIZED_HEIGHT);
        this.pad(Constants.GRID_MAXIMIZED_PADDING);

        setupGrid();
    }


    private void setupGrid() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Image gridCell = new Image(skin.getDrawable("grid_center"));
                gridCell.setName(i + "," + j);

                this.add(gridCell)
                        .maxSize(Constants.GRID_CELL_MAX_DIMENSION)
                        .minSize(Constants.GRID_CELL_MIN_DIMENSION);

                dragAndDrop.addTarget(new DragAndDrop.Target(gridCell) {
                    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        getActor().setColor(Color.RED);
                        return false;
                    }

                    public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
                        getActor().setColor(Color.WHITE);
                    }

                    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        System.out.println("REFUSED: " + payload.getObject() + " " + gridCell.getName());
                    }
                });

            }
            this.row();
        }

    }
}
