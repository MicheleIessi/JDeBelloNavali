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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.debellonavali.Tween.SpriteAccessor;

public class BattlePhaseScreen implements Screen {

    private Game game;
    private TweenManager tweenManager;
    private Stage stage;
    private Table table;
    private Sprite sfondo;
    private SpriteBatch batch;


    public BattlePhaseScreen(Game game) {
        System.out.println("Instanziato");
        this.game = game;
    }

    @Override
    public void show() {

        tweenManager = new TweenManager();

        Texture texture = new Texture(Gdx.files.internal("Pictures/sfondoFasi.png"));
        sfondo = new Sprite(texture);
        batch = new SpriteBatch();
        sfondo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        table = new Table();
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Tween.set(sfondo, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(sfondo, SpriteAccessor.ALPHA, .3f).target(1).start(tweenManager);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
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

    }
}
