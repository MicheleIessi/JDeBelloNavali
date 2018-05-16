package com.debellonavali.Screens.BattlePhase.Tables;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.debellonavali.Classes.Communicator.ConnectionManagerImpl;
import com.debellonavali.Classes.Communicator.DTO.DTO;
import com.debellonavali.Classes.Model.DeBelloGame;
import com.debellonavali.Constants;

public class EnemyGridTable extends GridTable {

    private Skin skin;
    private DragAndDrop dragAndDrop;

    public EnemyGridTable(Skin skin, DragAndDrop dragAndDrop) {
        super();
        this.skin = skin;
        this.dragAndDrop = dragAndDrop;

        this.setPosition(Constants.GRID_MINIMIZED_POS_X, Constants.GRID_MINIMIZED_POS_Y);
        this.setSize(Constants.GRID_MINIMIZED_WIDTH, Constants.GRID_MINIMIZED_HEIGHT);
        this.pad(Constants.GRID_MINIMIZED_PADDING);

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
                        getActor().setColor(Color.LIME);
                        return true;
                    }

                    public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
                        getActor().setColor(Color.WHITE);
                    }

                    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        int coordX = Integer.parseInt(gridCell.getName().substring(0,1));
                        int coordY = Integer.parseInt(gridCell.getName().substring(2,3));
                        System.out.println(coordX + " " + coordY);
                        DeBelloGame.getInstance().attack(1,1, new int[] {coordX, coordY});
                    }
                });
            }
            this.row();
        }
    }

}
