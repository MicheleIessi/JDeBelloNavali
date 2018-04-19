package com.debellonavali.Screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.debellonavali.Constants;
import com.debellonavali.Tween.ActorAccessor;
import com.debellonavali.Tween.SpriteAccessor;

public class BattlePhaseScreen implements Screen {

    private Game game;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private TweenManager tweenManager;
    private Table leftTable, playerGridTable, enemyGridTable;
    private Sprite sfondo;
    private SpriteBatch batch;
    private DragAndDrop dragAndDrop;
    private boolean resized = false;


    public BattlePhaseScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {

        atlas = new TextureAtlas(Gdx.files.internal(Constants.GRID_CELL_PACK));
        skin = new Skin(atlas);
        skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        stage = new Stage();
        tweenManager = new TweenManager();

        Image sourceImage = new Image(skin.getDrawable("grid_border"));

        Texture texture = new Texture(Gdx.files.internal(Constants.BATTLE_BACKGROUND_IMG));
        sfondo = new Sprite(texture);
        batch = new SpriteBatch();
        sfondo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        leftTable = new Table();
        playerGridTable = new Table();
        enemyGridTable = new Table();

        leftTable.setPosition(0,0);
        leftTable.setSize(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight());
        leftTable.add(sourceImage);

        enemyGridTable.setPosition(Gdx.graphics.getWidth()/3, 0);
        enemyGridTable.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight());

        playerGridTable.setPosition(Gdx.graphics.getWidth() / 2, 0);
        playerGridTable.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());

        playerGridTable.align(Align.top);
        playerGridTable.pad(20);

        Tween.set(sfondo, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(sfondo, SpriteAccessor.ALPHA, .3f).target(1).start(tweenManager);

        Gdx.input.setInputProcessor(stage);

        stage.addActor(leftTable);
        stage.addActor(enemyGridTable);
        stage.addActor(playerGridTable);

        dragAndDrop = new DragAndDrop();
        dragAndDrop.addSource(new DragAndDrop.Source(sourceImage) {
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject("Ciao");

                payload.setDragActor(new Image(skin.getDrawable("grid_angle")));

                Image validImage = new Image(skin.getDrawable("grid_border"));
                payload.setValidDragActor(validImage);


                Label invalidLabel = new Label("Valid", skin);
                invalidLabel.setColor(0, 1, 0, 1);
                payload.setValidDragActor(invalidLabel);

                return payload;

            }
        });

        setupLeftTable();
        setupGridTable();


        sourceImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                float newWidth, newHeight, newX, newY;
                if(!resized) {
                    playerGridTable.pad(10);
                    newWidth = Gdx.graphics.getWidth()/6 + 20;
                    newHeight = Gdx.graphics.getWidth()/6 + 20;
                    newX = Gdx.graphics.getWidth() / 3;
                    newY = 0;
                    resized = true;
                }
                else {
                    playerGridTable.pad(20);
                    newWidth = Gdx.graphics.getWidth() / 2;
                    newHeight = Gdx.graphics.getHeight();
                    newX = Gdx.graphics.getWidth()/2;
                    newY = 0;
                    resized = false;
                }


                Timeline.createParallel().beginParallel()
                        .push(Tween.to(playerGridTable, ActorAccessor.WIDTH, .3f).target(newWidth))
                        .push(Tween.to(playerGridTable, ActorAccessor.HEIGHT, .3f).target(newHeight))
                        .push(Tween.to(playerGridTable, ActorAccessor.X, .3f).target(newX))
                        .push(Tween.to(playerGridTable, ActorAccessor.Y, .3f).target(newY))
                        .end().start(tweenManager);

            }
        });

        playerGridTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                Timeline.createParallel().beginParallel()
                        .push(Tween.to(playerGridTable, ActorAccessor.WIDTH, .3f).target(Gdx.graphics.getWidth() / 2))
                        .push(Tween.to(playerGridTable, ActorAccessor.HEIGHT, .3f).target(Gdx.graphics.getHeight()))
                        .push(Tween.to(playerGridTable, ActorAccessor.X, .3f).target(Gdx.graphics.getWidth()/2))
                        .push(Tween.to(playerGridTable, ActorAccessor.Y, .3f).target(0))
                        .end().start(tweenManager);
            }
        });


    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sfondo.draw(batch);
        batch.end();

        tweenManager.update(delta);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }


    private void setupGridTable() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //Image gridCell = getImageByPosition(i, j);
                Image gridCell = new Image(skin.getDrawable("grid_center"));
                gridCell.setName(i+","+j);
                playerGridTable.add(gridCell).width(75).height(75).minHeight(25).minWidth(25);
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
            playerGridTable.row();
        }
    }

    private Image getImageByPosition(int i, int j) {
        Image gridCell;

        if (i == 0 && j == 0) {
            gridCell = new Image(skin.getDrawable("grid_angle"));
        } else if (i == 0 && j == 7) {
            gridCell = new Image(skin.getDrawable("grid_angle"));
            gridCell.setOrigin(gridCell.getWidth() / 2, gridCell.getHeight() / 2);
            gridCell.addAction(Actions.rotateTo(270));
        } else if (i == 7 && j == 7) {
            gridCell = new Image(skin.getDrawable("grid_angle"));
            gridCell.setOrigin(gridCell.getWidth() / 2, gridCell.getHeight() / 2);
            gridCell.addAction(Actions.rotateTo(180));
        } else if (i == 7 && j == 0) {
            gridCell = new Image(skin.getDrawable("grid_angle"));
            gridCell.setOrigin(gridCell.getWidth() / 2, gridCell.getWidth() / 2);
            gridCell.addAction(Actions.rotateTo(90));
        } else if (j == 0) {
            gridCell = new Image(skin.getDrawable("grid_border"));
        } else if (i == 0) {
            gridCell = new Image(skin.getDrawable("grid_border"));
            gridCell.setOrigin(gridCell.getWidth() / 2, gridCell.getHeight() / 2);
            gridCell.addAction(Actions.rotateTo(270));
        } else if (j == 7) {
            gridCell = new Image(skin.getDrawable("grid_border"));
            gridCell.setOrigin(gridCell.getWidth() / 2, gridCell.getHeight() / 2);
            gridCell.addAction(Actions.rotateTo(180));
        } else if (i == 7) {
            gridCell = new Image(skin.getDrawable("grid_border"));
            gridCell.setOrigin(gridCell.getWidth() / 2, gridCell.getHeight() / 2);
            gridCell.addAction(Actions.rotateTo(90));
        } else
            gridCell = new Image(skin.getDrawable("grid_center"));

        return gridCell;
    }

    private void setupLeftTable() {

    }

}
