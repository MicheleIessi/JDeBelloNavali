package com.debellonavali.Screens.BattlePhase;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Classes.Model.*;
import com.debellonavali.Classes.Model.RangeStrategy.IRangeStrategy;
import com.debellonavali.Classes.Model.RangeStrategy.RangeStrategyW1;

import com.debellonavali.Classes.Model.RangeStrategy.RangeStrategyW3;
import com.debellonavali.Screens.BattlePhase.Observers.BattleScreenObserver;
import com.debellonavali.Screens.BattlePhase.Observers.IScreenObserver;
import com.debellonavali.Screens.BattlePhase.Stages.BattlePhaseStage;

import com.debellonavali.Screens.BattlePhase.Stages.ConstantsBattlePhase;
import com.debellonavali.Screens.PlaceShip.CellGrid;

import java.util.ArrayList;
import java.util.HashMap;

public class BattlePhaseScreen implements INotifiableScreen {

    private Game game;
    private BattlePhaseStage stage;
    private Dialog winDialog;
    private Dialog loseDialog;

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
        observerList.add(new BattleScreenObserver());

        // Dialog configuration
        setEndGameDialogs();
        stage = new BattlePhaseStage(DeBelloGame.getInstance());

    }

    private void setEndGameDialogs() {
        Skin uiSkin = new Skin(Gdx.files.internal("Dialog/uiskin.json"));

        winDialog = new Dialog(ConstantsBattlePhase.DIALOG_TITLE, uiSkin) {
            public void result(Object obj) {
                System.out.println("result " + obj);
            }
        };

        loseDialog = new Dialog(ConstantsBattlePhase.DIALOG_TITLE, uiSkin) {
            public void result(Object obj) {
                System.out.println("result " + obj);
            }
        };

        String winDialogText = ConstantsBattlePhase.DIALOG_WIN_MESSAGE;
        winDialog.text(winDialogText);
        winDialog.button("Great!", false).sizeBy(50, 50); //sends "false" as the result

        String loseDialogText = ConstantsBattlePhase.DIALOG_LOSE_MESSAGE;
        loseDialog.text(loseDialogText);
        loseDialog.button("Better luck next time", false).sizeBy(50, 50);

    }

    public void testOperation() {
        DeBelloGame deBelloGame = DeBelloGame.getInstance();
        deBelloGame.setCurrentScreen(this);

        deBelloGame.setPlayerTurn(true);
        playerBattlefield = deBelloGame.getPlayerBattlefield();
        /**************************  add two static ships****************************************/
        Ship twoShip = new Ship();
        twoShip.setDimension(2);
        twoShip.setShipID(1);
        twoShip.setShipName("Bireme");
        twoShip.setShipWeight(40);
        IRangeStrategy range = new RangeStrategyW1();
        Weapon weapon = new Weapon();
        weapon.setWeaponName("W1");
        weapon.setMaxReloadTime(0);
        weapon.setRangeStrategy(range);
        weapon.setWeaponID(1);
        AmmoStorage.getInstance().addWeaponToStorage(weapon.getWeaponName(), 999);
        HashMap map = new HashMap();
        map.put(weapon.getWeaponID(), weapon);
        twoShip.setWeapons(map);
        HashMap<Integer, Ship> fleet = new HashMap();
        playerBattlefield.addShip(twoShip, new int[]{1, 1}, Battlefield.HORIZONTAL);
        fleet.put(twoShip.getShipID(), twoShip);

        Ship threeShip = new Ship();
        threeShip.setDimension(3);
        threeShip.setShipID(2);
        threeShip.setShipName("Trireme");
        threeShip.setShipWeight(60);
        range = new RangeStrategyW3();
        weapon = new Weapon();
        weapon.setWeaponName("W3");
        weapon.setMaxReloadTime(0);
        weapon.setRangeStrategy(range);
        weapon.setWeaponID(2);
        AmmoStorage.getInstance().addWeaponToStorage(weapon.getWeaponName(), 999);
        map = new HashMap();
        map.put(weapon.getWeaponID(), weapon);
        threeShip.setWeapons(map);
        System.out.println("Ship dimension: " + threeShip.getDimension());
        playerBattlefield.addShip(threeShip, new int[]{4, 2}, Battlefield.HORIZONTAL);
        System.out.println("Ship dimension: " + threeShip.getDimension());

        fleet.put(threeShip.getShipID(), threeShip);

        System.out.println(fleet.get(threeShip.getShipID()).getDimension());

        /**************************************************************************************/
        playerBattlefield.setFleet(fleet);
        //Draw all the ships placed in the place ship phase
        stage.setFleet(fleet);

    }

    @Override
    public void show() {
        System.out.println("Battle phase screen show...");
        //Give the input processor to the main stage
        Gdx.input.setInputProcessor(stage);
        //Set up layout of the screen
        stage.setUpLayout();
        testOperation();
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(1, 1, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

    public void notifyScreen(IDTO dto) {
        for (IScreenObserver observer : observerList) {
            observer.update(dto, this);
        }
    }

    @Override
    public void showEndGameDialog(boolean winner) {
        if (winner) {
            winDialog.show(stage);
        } else {
            loseDialog.show(stage);
        }
    }


    public ArrayList<ArrayList<CellGrid>> getEnemyField() {
        return stage.getEnemyField();
    }

    public ArrayList<ArrayList<CellGrid>> getPlayerField() {
        return stage.getPlayerField();
    }


}