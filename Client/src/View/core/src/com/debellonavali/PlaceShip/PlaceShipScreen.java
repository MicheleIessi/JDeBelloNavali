package com.debellonavali.PlaceShip;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.debellonavali.Constants;
import com.debellonavali.PlaceShip.ConstantsPlaceShips;
import com.debellonavali.PlaceShip.Stage.PlaceShipStage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class PlaceShipScreen implements Screen {

    private final Game app;
    private PlaceShipStage stage;
    private Music backgroundMusic;


    /*   layout:
         ++++++++++++++++++++++++++
         +        +    grid       +
         + ship   +    zone       +
         + zone   +               +
         +        +++++++++++++++++
         +        +    timer      +
         +        +    zone       +
         ++++++++++++++++++++++++++
     */
    public PlaceShipScreen (Game game){
        this.app=game;
        stage= new PlaceShipStage();

    }

    @Override
    public void show() {
        System.out.println("Place ship screen show...");
        Gdx.input.setInputProcessor(stage);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(ConstantsPlaceShips.AUDIO_MAIN_THEME));
        //backgroundMusic.play();

        stage.setUpLayout();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

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

    public void update(float delta){
        stage.act(delta);
    }
}
