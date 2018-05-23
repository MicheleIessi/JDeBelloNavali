package com.debellonavali.Screens.BattlePhase;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Classes.Model.*;
import com.debellonavali.Classes.Model.RangeStrategy.IRangeStrategy;
import com.debellonavali.Classes.Model.RangeStrategy.RangeStrategyW1;
import com.debellonavali.Constants;
import com.debellonavali.Screens.BattlePhase.Observers.BattleObserver;
import com.debellonavali.Screens.BattlePhase.Observers.IScreenObserver;
import com.debellonavali.Screens.BattlePhase.Stages.BattlePhaseStage;
import com.debellonavali.Screens.BattlePhase.Tables.EnemyGridTable;
import com.debellonavali.Screens.BattlePhase.Tables.PlayerGridTable;
import com.debellonavali.Screens.BattlePhase.Tables.ShipInfoTable;

import java.util.ArrayList;
import java.util.HashMap;

public class BattlePhaseScreen implements INotifiableScreen {

    private Game game;
    private BattlePhaseStage stage;
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
    private ArrayList<IScreenObserver> observerList;

    // Test variables
    private Battlefield playerBattlefield;


    /*   layout 1:
        ++++++++++++++++++++++++++
        +        +    +  Enemy   +
        + Fleet  +    +  Field   +
        + zone   +    +          +
        +        +    ++++++++++++
        +        ++++++++++      +
        +        + Player +      +
        +        + Field  +      +
        ++++++++++++++++++++++++++
    */
    public BattlePhaseScreen(Game game) {
        // Graphic initialization
        this.game = game;
        this.observerList = new ArrayList<>();
        observerList.add(new BattleObserver());

        stage = new BattlePhaseStage(DeBelloGame.getInstance());



    }

    public void testOperation() {
        DeBelloGame deBelloGame=DeBelloGame.getInstance();
        deBelloGame.setCurrentScreen(this);
        deBelloGame.setPlayerTurn(true);
        playerBattlefield = deBelloGame.getPlayerBattlefield();
        /**************************  add two static ships****************************************/
        Ship ship = new Ship();
        ship.setDimension(2);
        ship.setShipID(1);
        ship.setShipName("Bireme");
        ship.setShipWeight(40);
        IRangeStrategy range = new RangeStrategyW1();
        Weapon weapon = new Weapon();
        weapon.setWeaponName("W1");
        weapon.setMaxReloadTime(0);
        weapon.setRangeStrategy(range);
        weapon.setWeaponID(1);
        AmmoStorage.getInstance().addWeaponToStorage(weapon.getWeaponName(), 999);
        HashMap map = new HashMap();
        map.put(weapon.getWeaponID(), weapon);
        ship.setWeapons(map);
        HashMap fleet = new HashMap();
        playerBattlefield.addShip(ship, new int[] {1,1}, Battlefield.HORIZONTAL);
        fleet.put(ship.getShipID(), ship);


        ship = new Ship();
        ship.setDimension(3);
        ship.setShipID(2);
        ship.setShipName("Trireme");
        ship.setShipWeight(60);
         range = new RangeStrategyW1();
         weapon = new Weapon();
        weapon.setWeaponName("W3");
        weapon.setMaxReloadTime(0);
        weapon.setRangeStrategy(range);
        weapon.setWeaponID(2);
        AmmoStorage.getInstance().addWeaponToStorage(weapon.getWeaponName(), 999);
         map = new HashMap();
        map.put(weapon.getWeaponID(), weapon);
        ship.setWeapons(map);

        playerBattlefield.addShip(ship, new int[] {4,2}, Battlefield.HORIZONTAL);

        fleet.put(ship.getShipID(), ship);




        /**************************************************************************************/

        playerBattlefield.drawField();


        playerBattlefield.setFleet(fleet);

        //Draw all the ships placed in the place ship phase
        stage.setFleet(fleet);

    }

    @Override
    public void show() {

        System.out.println("Battle phase screen show...");

        //Give the input processor to the main stage
        Gdx.input.setInputProcessor(stage);

        //Set uo layout of the screen
        stage.setUpLayout();
        testOperation();

        /*dragAndDrop = new DragAndDrop();

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


        Gdx.input.setInputProcessor(stage);
        stage.addActor(infoScrollPane);
        stage.addActor(enemyGridTable);
        stage.addActor(playerGridTable);

        addTableClickListeners();*/

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(1, 1, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

      /*  batch.begin();
        sfondo.draw(batch);
        batch.end();

        tweenManager.update(delta);
*/
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

    public void notifyScreen(IDTO dto) {
        for(IScreenObserver observer : observerList) {
            observer.update(dto, this);
        }

    }

}