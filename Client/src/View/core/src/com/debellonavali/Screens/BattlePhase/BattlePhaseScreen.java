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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.debellonavali.Classes.Communicator.ConnectionManagerImpl;
import com.debellonavali.Classes.Controller.Observers.AttackResultsObserver;
import com.debellonavali.Classes.Controller.Observers.ChatObserver;
import com.debellonavali.Classes.Controller.Observers.ReceiveAttackObserver;
import com.debellonavali.Classes.Controller.FacadeClientController;
import com.debellonavali.Classes.Controller.Observers.LoginObserver;
import com.debellonavali.Classes.Model.*;
import com.debellonavali.Classes.Model.Factories.GameFactory;
import com.debellonavali.Classes.Model.RangeStrategy.IRangeStrategy;
import com.debellonavali.Classes.Model.RangeStrategy.RangeStrategyW1;
import com.debellonavali.Constants;
import com.debellonavali.Screens.BattlePhase.Groups.ShipInfoGroup;
import com.debellonavali.Screens.BattlePhase.Tables.EnemyGridTable;
import com.debellonavali.Screens.BattlePhase.Tables.PlayerGridTable;
import com.debellonavali.Screens.BattlePhase.Tables.ShipInfoTable;
import com.debellonavali.Tween.SpriteAccessor;

import java.util.HashMap;

public class BattlePhaseScreen implements Screen {

    private Game game;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private TweenManager tweenManager;
    private ShipInfoTable shipInfoTable;
    private PlayerGridTable playerGridTable;
    private EnemyGridTable enemyGridTable;
    private Sprite sfondo;
    private SpriteBatch batch;
    private boolean resized = false;
    private DragAndDrop dragAndDrop;
    // Test variables
    private Battlefield playerBattlefield;


    public BattlePhaseScreen(Game game) {
        // Graphic initialization
        this.game = game;
        stage = new Stage();

        DeBelloGame dbg = GameFactory.getInstance().createDeBelloGame();

        FacadeClientController clientController = new FacadeClientController();
        clientController.attachObserver(new ReceiveAttackObserver());
        clientController.attachObserver(new ChatObserver());
        clientController.attachObserver(new LoginObserver());
        clientController.attachObserver(new AttackResultsObserver());
        dbg.attachFacadeController(clientController);

        ConnectionManagerImpl connectionManager = ConnectionManagerImpl.getInstance();
        connectionManager.initialize(Constants.DEFAULT_ENEMY_PORT);
        connectionManager.setEnemyInformation("localhost", Constants.DEFAULT_PLAYER_PORT);
        connectionManager.startMonitoringThread();

        testOperation();

    }

    public void testOperation() {
        playerBattlefield = DeBelloGame.getInstance().getPlayerBattlefield();
        Ship ship = new Ship();
        ship.setIntegrity(100);
        ship.setShipID(1);
        IRangeStrategy range = new RangeStrategyW1();
        Weapon weapon = new Weapon();
        weapon.setWeaponName("Prova");
        weapon.setMaxReloadTime(0);
        weapon.setRangeStrategy(range);
        weapon.setWeaponID(1);
        AmmoStorage.getInstance().addWeaponToStorage(weapon.getWeaponName(), 999);
        HashMap map = new HashMap();
        map.put(weapon.getWeaponID(), weapon);
        ship.setWeapons(map);
        HashMap fleet = new HashMap();
        fleet.put(ship.getShipID(), ship);
        playerBattlefield.setFleet(fleet);
    }

    @Override
    public void show() {

        dragAndDrop = new DragAndDrop();

        atlas = new TextureAtlas(Gdx.files.internal(Constants.GRID_CELL_PACK));
        skin = new Skin(atlas);
        skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        tweenManager = new TweenManager();

        Texture texture = new Texture(Gdx.files.internal(Constants.BATTLE_BACKGROUND_IMG));
        sfondo = new Sprite(texture);
        batch = new SpriteBatch();
        sfondo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        shipInfoTable = new ShipInfoTable(skin, dragAndDrop);
        playerGridTable = new PlayerGridTable(skin, dragAndDrop);
        enemyGridTable = new EnemyGridTable(skin, dragAndDrop);

        ScrollPane infoScrollPane = new ScrollPane(shipInfoTable);
        infoScrollPane.setPosition(0, 0);
        infoScrollPane.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight());

        Tween.set(sfondo, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(sfondo, SpriteAccessor.ALPHA, .3f).target(1).start(tweenManager);

        for (int i = 0; i < playerBattlefield.getFleet().size(); i++) {
            ShipInfoGroup shipGroup = new ShipInfoGroup(dragAndDrop, playerBattlefield.getFleet().get(1));
            shipInfoTable.addShipGroup(shipGroup);
        }

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(infoScrollPane);
        stage.addActor(enemyGridTable);
        stage.addActor(playerGridTable);

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
        batch.dispose();
        atlas.dispose();
        skin.dispose();
        stage.dispose();
    }

    private void addTableClickListeners() {

        ClickListener playerClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (playerGridTable.getWidth() == Constants.GRID_MINIMIZED_WIDTH) {
                    playerGridTable.toggleDimension(resized, tweenManager);
                    enemyGridTable.toggleDimension(!resized, tweenManager);
                }
                resized = !resized;
            }
        };

        ClickListener enemyClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (enemyGridTable.getWidth() == Constants.GRID_MINIMIZED_WIDTH) {
                    playerGridTable.toggleDimension(!resized, tweenManager);
                    enemyGridTable.toggleDimension(resized, tweenManager);
                }
                resized = !resized;
            }
        };

        playerGridTable.addListener(playerClickListener);
        enemyGridTable.addListener(enemyClickListener);
    }

}
