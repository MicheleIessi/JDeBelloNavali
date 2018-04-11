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
import com.debellonavali.Tween.SpriteAccessor;


public class SplashScreen implements Screen {

    private Game game;              // Permette di cambiare screen

    private SpriteBatch batch;
    private Sprite splash;
    private TweenManager tweenManager;

    public SplashScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {

        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        batch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal("Pictures/background.jpg"));
        splash = new Sprite(texture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(splash, SpriteAccessor.ALPHA, 1f).target(1).start(tweenManager);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.88f, 0.84f, 0.71f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        batch.begin();
        splash.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // La finestra non è resizable
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
