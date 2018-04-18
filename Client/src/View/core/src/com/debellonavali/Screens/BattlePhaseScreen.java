package com.debellonavali.Screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.debellonavali.Constants;
import com.debellonavali.Tween.SpriteAccessor;

public class BattlePhaseScreen implements Screen {

    private Game game;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private TweenManager tweenManager;
    private Table leftTable, gridTable;
    private Sprite sfondo;
    private SpriteBatch batch;


    public BattlePhaseScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {

        atlas = new TextureAtlas(Gdx.files.internal(Constants.GRID_PACK));
        skin = new Skin(atlas);

        stage = new Stage();
        tweenManager = new TweenManager();

        Texture texture = new Texture(Gdx.files.internal(Constants.BATTLE_BACKGROUND_IMG));
        sfondo = new Sprite(texture);
        batch = new SpriteBatch();
        sfondo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        leftTable = new Table();
        gridTable = new Table();

        leftTable.setBounds(0,0,Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight());
        gridTable.setBounds(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/8, Gdx.graphics.getWidth()/2, 7*Gdx.graphics.getHeight()/8);
        leftTable.debug();

        setupLeftTable();
        setupGridTable();

        Tween.set(sfondo, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(sfondo, SpriteAccessor.ALPHA, .3f).target(1).start(tweenManager);

        Gdx.input.setInputProcessor(stage);

        stage.addActor(leftTable);
        stage.addActor(gridTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        batch.begin();
        sfondo.draw(batch);
        batch.end();


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


        Image border_cell = new Image(skin, "grid_border");
        Image center_cell = new Image(skin, "grid_center");
        Image angle_cell = new Image(skin, "grid_angle");

        /* ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = skin.getDrawable("gridButton.up");
        buttonStyle.down = skin.getDrawable("gridButton.down");*/
        gridTable.addActor(angle_cell);
        gridTable.addActor(border_cell);
        gridTable.addActor(center_cell);
        /*for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (i==0 && j==0)
                {
                    gridTable.addActor(angle_cell);
                }
                if (i==0 && j==7){
                    angle_cell.setRotation(90);
                    gridTable.addActor(angle_cell);
                }
                if (i==7 && j==0){
                    angle_cell.setRotation(270);
                    gridTable.addActor(angle_cell);
                }
                if (i==7 && j==7){
                    angle_cell.setRotation(360);
                    gridTable.addActor(angle_cell);
                }
                /*Button button = new Button(buttonStyle);
                gridTable.add(button).width(75).height(75);
                button.setName(i+","+j);
                button.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        System.out.println("Clicked actor (" + actor.getName() + ")");
                    }
                });
            }
            gridTable.row();
        }*/
    }

    private void setupLeftTable() {

    }

}
