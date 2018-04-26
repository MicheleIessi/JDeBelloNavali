package com.debellonavali.Screens.BattlePhase.Tables;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Timer;
import com.debellonavali.Tween.ActorAccessor;

public class PlayerGridTable extends Table {

    // Default grid dimension
    private float PLAYER_GRID_POS_X = Gdx.graphics.getWidth() / 2;
    private float PLAYER_GRID_POS_Y = 0;
    private float PLAYER_GRID_WIDTH = Gdx.graphics.getWidth() / 2;
    private float PLAYER_GRID_HEIGHT = Gdx.graphics.getHeight();
    private float PLAYER_GRID_PADDING = 20;

    // Minimized grid dimension
    private float PLAYER_GRID_MIN_POS_X = Gdx.graphics.getWidth() / 4;
    private float PLAYER_GRID_MIN_POS_Y = 0;
    public float PLAYER_GRID_MIN_WIDTH = Gdx.graphics.getWidth() / 4;
    private float PLAYER_GRID_MIN_HEIGHT = Gdx.graphics.getWidth() / 4;
    private float PLAYER_GRID_MIN_PADDING = 10;

    private Skin skin;
    private TweenManager tweenManager;
    private DragAndDrop dragAndDrop;

    public PlayerGridTable(Skin skin, TweenManager tweenManager) {
        super();
        this.skin = skin;
        this.tweenManager = tweenManager;
        dragAndDrop = new DragAndDrop();

        this.setPosition(PLAYER_GRID_POS_X, PLAYER_GRID_POS_Y);
        this.setSize(PLAYER_GRID_WIDTH, PLAYER_GRID_HEIGHT);
        this.pad(PLAYER_GRID_PADDING);

        setupGrid();
    }


    public void toggleDimension(boolean resized) {

        float new_pos_x = resized ? PLAYER_GRID_POS_X : PLAYER_GRID_MIN_POS_X;
        float new_pos_y = resized ? PLAYER_GRID_POS_Y : PLAYER_GRID_MIN_POS_Y;
        float new_width = resized ? PLAYER_GRID_WIDTH : PLAYER_GRID_MIN_WIDTH;
        float new_height = resized ? PLAYER_GRID_HEIGHT : PLAYER_GRID_MIN_HEIGHT;
        float new_padding = resized ? PLAYER_GRID_PADDING : PLAYER_GRID_MIN_PADDING;

        System.out.println(new_pos_x);

        Timeline.createParallel().beginParallel()
                .push(Tween.to(this, ActorAccessor.WIDTH, .3f).target(new_width))
                .push(Tween.to(this, ActorAccessor.HEIGHT, .3f).target(new_height))
                .push(Tween.to(this, ActorAccessor.X, .3f).target(new_pos_x))
                .push(Tween.to(this, ActorAccessor.Y, .3f).target(new_pos_y))
                .end().start(tweenManager);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                pad(new_padding);
            }
        }, .3f);

    }

    private void setupGrid() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Image gridCell = new Image(skin.getDrawable("grid_center"));
                gridCell.setName(i + "," + j);
                this.add(gridCell).width(75).height(75).minHeight(25).minWidth(25);
                dragAndDrop.addTarget(new DragAndDrop.Target(gridCell) {
                    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        getActor().setColor(Color.LIME);
                        return true;
                    }

                    public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
                        getActor().setColor(Color.WHITE);
                    }

                    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                        System.out.println("Accepted: " + payload.getObject() + " " + gridCell.getName());
                    }
                });

            }
            this.row();
        }

    }
}
