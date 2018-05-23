package com.debellonavali.Screens.PlaceShip;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.debellonavali.Screens.PlaceShip.Stages.PlaceShipStage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class PlaceShipScreen implements Screen {

    private final Game app;
    private PlaceShipStage stage;
    private SpriteBatch batch;
    private Music backgroundMusic;
    private Image splash;



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
        batch = new SpriteBatch();
    }

    @Override
    public void show() {


        System.out.println("Place ship screen show...");
        Gdx.input.setInputProcessor(stage);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(com.debellonavali.Screens.PlaceShip.ConstantsPlaceShips.AUDIO_MAIN_THEME));
        //backgroundMusic.play();



        stage.setUpLayout();


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
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
         stage.dispose();
    }

    public void update(float delta){
        stage.act(delta);
    }
}
