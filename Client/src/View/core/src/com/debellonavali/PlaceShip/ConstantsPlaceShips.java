package com.debellonavali.PlaceShip;

public class ConstantsPlaceShips {

    public static int APP_WIDTH = 1280;
    public static int APP_HEIGHT = 720;

    public static String AUDIO_MAIN_THEME="Music/themePlaceShip.mp3";
    public static String PLACE_SHIP_BACKGROUND_IMG= "Pictures/PlaceShipPictures/PlaceShipBackground.jpg";

    /************************** TABLE AND ZONE SIZE CONSTANTS**********************************/
    public static int SHIP_ZONE_PER_WIDTH=35;
    public static int  SHIP_ZONE_WIDTH=  (APP_WIDTH/100)*SHIP_ZONE_PER_WIDTH;
    public static int  SHIP_ZONE_HEIGHT=  APP_HEIGHT;

    public static int GRIDTIMER_TABLE_PER_WIDTH =100-SHIP_ZONE_PER_WIDTH;
    public static final int GRIDTIMER_TABLE_WIDTH =  (APP_WIDTH-SHIP_ZONE_WIDTH);
    public static final int GRIDTIMER_TABLE_HEIGHT =  APP_HEIGHT;

    public static int GRID_ZONE_PER_HEIGHT=80;
    public static final int GRID_ZONE_WIDTH= GRIDTIMER_TABLE_WIDTH;
    public static final int GRID_ZONE_HEIGHT= (GRIDTIMER_TABLE_HEIGHT/100)*GRID_ZONE_PER_HEIGHT;

    public static final int FLEET_WEIGHT_TABLE_PER_HEIGHT =5;
    public static final int FLEET_WEIGHT_TABLE_WIDTH =GRID_ZONE_WIDTH;
    public static final int FLEET_WEIGHT_TABLE_HEIGHT= (GRID_ZONE_HEIGHT/100)*FLEET_WEIGHT_TABLE_PER_HEIGHT;

    public static final int GRID_TABLE_PER_HEIGHT=95;
    public static final int GRID_TABLE_WIDTH=GRID_ZONE_WIDTH;
    public static final int GRID_TABLE_HEIGHT=GRID_ZONE_HEIGHT-FLEET_WEIGHT_TABLE_HEIGHT;


    public static final int TIMER_ZONE_WIDTH= GRIDTIMER_TABLE_WIDTH;
    public static final int TIMER_ZONE_HEIGHT=  GRIDTIMER_TABLE_HEIGHT-GRID_ZONE_HEIGHT;
    public static int TIMER_ZONE_PER_HEIGHT=100-GRID_ZONE_PER_HEIGHT;


    /***********************************BUTTON****************************************************/

    public static final String START_BATTLE_BUTTON_TEXT ="";
    public static final String SURREND_BATTLE_BUTTON_TEXT ="";

    public static final String START_BATTLE_BUTTON_PACK = "Packs/placeShipPacks/startBattleButton.pack";
    public static final String SURREND_BUTTON_PACK= "Packs/placeShipPacks/surrendBattleButton.pack";
    public static final int START_BATTLE_BUTTON_WIDTH= TIMER_ZONE_WIDTH/5;

    public static String PLACE_SHIP_BUTTON_FONT = "Fonts/buttonFont.fnt";


    /***********************************GRID TEXTURE*******************************************************/

    public static final String NOT_EMPTY_TEXTURE_PATH= "Pictures/PlaceShipPictures/notEmpty.png";
    public static final String EMPTY_TEXTURE_PATH= "Pictures/PlaceShipPictures/empty.png";
    public static final String EMPTY_INVALID_TEXTURE_PATH= "Pictures/PlaceShipPictures/notValid.png";

    public static final String WATER_TEXTURE= "Pictures/PlaceShipPictures/water2/";


    /***********************************SHIPS IMG*******************************************************/
    public static final String FLEET_PICTURES_PATH="Pictures/PlaceShipPictures/FleetPictures/";
    public static final String SHIP_NAME_FONT="Fonts/Arcon.ttf";
}
