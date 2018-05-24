package com.debellonavali.Screens.BattlePhase.Stages;


import org.omg.CORBA.PUBLIC_MEMBER;

public class ConstantsBattlePhase {


    public static int APP_WIDTH = 1280;
    public static int APP_HEIGHT = 720;

    /************************** TABLE AND ZONE SIZE CONSTANTS**********************************/
    public static int FIELD_TABLE_PER_WIDTH=75;
    public static int  FIELD_TABLE_WIDTH=  (APP_WIDTH/100)*FIELD_TABLE_PER_WIDTH;
    public static int  FIELD_TABLE_HEIGHT=  APP_HEIGHT;

    public static int FLEETLIST_TABLE_PER_WIDTH =100-FIELD_TABLE_PER_WIDTH;
    public static final int FLEETLIST_TABLE_WIDTH =  (APP_WIDTH-FIELD_TABLE_WIDTH);
    public static final int FLEETLIST_TABLE_HEIGHT =  APP_HEIGHT;




    public static int ENEMYFIELD_TABLE_PER_HEIGHT=68;
    public static int ENEMYFIELD_TABLE_WIDTH= FIELD_TABLE_WIDTH;
    public static int ENEMYFIELD_TABLE_HEIGHT= (FIELD_TABLE_HEIGHT/100)*ENEMYFIELD_TABLE_PER_HEIGHT;

    public static int PLAYERFIELD_TABLE_PER_HEIGHT=32;
    public static int PLAYER_FIELD_TABLE_WIDTH = FIELD_TABLE_WIDTH;
    public static int PLAYERFIELD_TABLE_HEIGHT= APP_HEIGHT- ENEMYFIELD_TABLE_HEIGHT;



    public static final int ENEMY_GRID_TABLE_WIDTH = ENEMYFIELD_TABLE_WIDTH-430;
    public static final int ENEMY_GRID_TABLE_HEIGHT= ENEMYFIELD_TABLE_HEIGHT-20;


    public static final int PLAYER_GRID_TABLE_PER_HEIGHT=20;
    public static final int PLAYER_GRID_TABLE_WIDTH=FIELD_TABLE_WIDTH/4;
    public static final int PLAYER_GRID_TABLE_HEIGHT=PLAYERFIELD_TABLE_HEIGHT;





    /***********************************BUTTON****************************************************/



    public static final String ALREADY_HIT_TEXTURE_PATH="Pictures/BattlePhasePictures/alreadyHit.png";
    public static final String WATER_HOLE_TEXTURE_PATH="Pictures/BattlePhasePictures/waterHole.png";


    public static final String FIRE_TEXTURE="Pictures/BattlePhasePictures/fire/fire";




}
