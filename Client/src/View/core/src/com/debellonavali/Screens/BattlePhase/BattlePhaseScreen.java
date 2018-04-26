package com.debellonavali.Screens.BattlePhase;

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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.debellonavali.Constants;
import com.debellonavali.Screens.BattlePhase.Tables.EnemyGridTable;
import com.debellonavali.Screens.BattlePhase.Tables.PlayerGridTable;
import com.debellonavali.Tween.SpriteAccessor;

public class BattlePhaseScreen implements Screen {

    private Game game;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private TweenManager tweenManager;
    private Table leftTable;
    private PlayerGridTable playerGridTable;
    private EnemyGridTable enemyGridTable;
    private Sprite sfondo;
    private SpriteBatch batch;
    private boolean resized = false;


    public BattlePhaseScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {

        atlas = new TextureAtlas(Gdx.files.internal(Constants.GRID_CELL_PACK));
        skin = new Skin(atlas);
        skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        tweenManager = new TweenManager();

        Image sourceImage = new Image(skin.getDrawable("grid_border"));

        Texture texture = new Texture(Gdx.files.internal(Constants.BATTLE_BACKGROUND_IMG));
        sfondo = new Sprite(texture);
        batch = new SpriteBatch();
        sfondo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        leftTable = new Table();
        playerGridTable = new PlayerGridTable(skin, tweenManager);
        enemyGridTable = new EnemyGridTable(skin, tweenManager);

        leftTable.setPosition(0,0);
        leftTable.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight());
        leftTable.add(sourceImage);

        Tween.set(sfondo, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(sfondo, SpriteAccessor.ALPHA, .3f).target(1).start(tweenManager);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(leftTable);
        stage.addActor(enemyGridTable);
        stage.addActor(playerGridTable);

        leftTable.debug();
        enemyGridTable.debug();
        playerGridTable.debug();

        addTableClickListeners();

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

    private void addTableClickListeners() {
        ClickListener playerClickListener = new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(playerGridTable.getWidth() == playerGridTable.PLAYER_GRID_MIN_WIDTH) {
                    playerGridTable.toggleDimension(resized);
                    enemyGridTable.toggleDimension(resized);
                }
                resized = !resized;
            }
        };

        ClickListener enemyClickListener = new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(enemyGridTable.getWidth() == enemyGridTable.ENEMY_GRID_WIDTH) {
                    playerGridTable.toggleDimension(resized);
                    enemyGridTable.toggleDimension(resized);
                }
                resized = !resized;
            }
        };

        playerGridTable.addListener(playerClickListener);
        enemyGridTable.addListener(enemyClickListener);

    }


}
