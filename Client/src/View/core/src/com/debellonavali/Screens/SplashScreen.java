package com.debellonavali.Screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Timer;
import com.debellonavali.Constants;

import com.debellonavali.Screens.BattlePhase.BattlePhaseScreen;

import com.debellonavali.Tween.ActorAccessor;
import com.debellonavali.Tween.SpriteAccessor;

public class SplashScreen implements Screen {

    private Game game;              // Permette di cambiare screen

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private Label titleLabel;
    private LabelStyle titleLabelStyle;
    private TextButton playButton, exitButton;
    private TextButtonStyle textButtonStyle;
    private BitmapFont buttonFont, titleFont;
    private SpriteBatch batch;
    private Sprite splash;
    private Music backgroundMusic;
    private TweenManager tweenManager;
    private boolean prossimoScreenIstanziato = false;

    public SplashScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {

        atlas = new TextureAtlas(Gdx.files.internal(Constants.SPLASH_BUTTON_PACK));
        skin = new Skin(atlas);

        tweenManager = new TweenManager();
        batch = new SpriteBatch();
        Texture texture = new Texture(Gdx.files.internal(Constants.SPLASH_BACKGROUND_IMG));
        splash = new Sprite(texture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        buttonFont = new BitmapFont(Gdx.files.internal(Constants.SPLASH_BUTTON_FONT), false);
        titleFont = new BitmapFont(Gdx.files.internal(Constants.SPLASH_TITLE_FONT), false);

        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = buttonFont;
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");

        titleLabelStyle = new LabelStyle();
        titleLabelStyle.font = titleFont;
        titleLabelStyle.fontColor = new Color(.1f, .1f, .1f, 1);

        titleLabel = new Label(Constants.SPLASH_LABEL_TITLE, titleLabelStyle);

        playButton = new TextButton(Constants.SPLASH_PLAY_TEXT, textButtonStyle);
        exitButton = new TextButton(Constants.SPLASH_EXIT_TEXT, textButtonStyle);

        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                fade(.3f);

                if(!prossimoScreenIstanziato) {
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
//                            backgroundMusic.stop();
                            game.setScreen(new BattlePhaseScreen(game));
                        }
                    }, .3f);
                }
                prossimoScreenIstanziato = true;
            }
        });


        if(Constants.SOUND_ENABLED) {
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(Constants.AUDIO_MAIN_THEME));
            //backgroundMusic.play();
        }


        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                fade(.3f);

                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        Gdx.app.exit();
                    }
                }, .5f);

            }
        });

        table = new Table(skin);
        table.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        table.add(titleLabel);
        table.padBottom(50);
        table.row();
        table.getCell(titleLabel).spaceBottom(200);
        table.add(playButton).width(350);
        table.getCell(playButton).spaceBottom(20);
        table.row();
        table.add(exitButton).width(350);

        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        stage.addActor(table);

        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        Tween.registerAccessor(Actor.class, new ActorAccessor());

        Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.set(table, ActorAccessor.ALPHA).target(0).start(tweenManager);

        Tween.to(splash, SpriteAccessor.ALPHA, 1).target(1).start(tweenManager);
        Tween.to(table, ActorAccessor.ALPHA, 1).target(1).start(tweenManager);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        batch.begin();
        splash.draw(batch);
        batch.end();

        stage.act(delta);
        stage.draw();
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
        tweenManager.killAll();
        backgroundMusic.dispose();
        batch.dispose();
        titleFont.dispose();
        buttonFont.dispose();
        stage.dispose();
    }

    private void fade(float duration) {
        Timeline.createParallel().beginParallel()
                .push(Tween.to(table, ActorAccessor.ALPHA, duration).target(0))
                .push(Tween.to(splash, SpriteAccessor.ALPHA, duration).target(0))
                .end().start(tweenManager);
    }
}
