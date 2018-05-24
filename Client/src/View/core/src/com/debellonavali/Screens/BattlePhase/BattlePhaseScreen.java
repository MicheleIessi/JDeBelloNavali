package com.debellonavali.Screens.BattlePhase;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import com.debellonavali.Classes.Communicator.DTO.IDTO;
import com.debellonavali.Classes.Model.*;
import com.debellonavali.Classes.Model.RangeStrategy.IRangeStrategy;
import com.debellonavali.Classes.Model.RangeStrategy.RangeStrategyW1;

import com.debellonavali.Screens.BattlePhase.Observers.BattleScreenObserver;
import com.debellonavali.Screens.BattlePhase.Observers.IScreenObserver;
import com.debellonavali.Screens.BattlePhase.Stages.BattlePhaseStage;

import com.debellonavali.Screens.PlaceShip.CellGrid;

import java.util.ArrayList;
import java.util.HashMap;

public class BattlePhaseScreen implements INotifiableScreen {

    private Game game;
    private BattlePhaseStage stage;

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
        for(IScreenObserver observer : observerList) {
            observer.update(dto, this);
        }

    }


    public ArrayList<ArrayList<CellGrid>> getEnemyField(){
        return stage.getEnemyField();

    }

}