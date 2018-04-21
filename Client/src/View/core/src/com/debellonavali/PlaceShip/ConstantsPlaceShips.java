package com.debellonavali.PlaceShip;

public class ConstantsPlaceShips {

    public static int APP_WIDTH = 1280;
    public static int APP_HEIGHT = 720;

    public static String  LAYOUT_PACKAGE="Layout1.";

    /************************** TABLE AND ZONE SIZE CONSTANTS**********************************/
    public static int SHIP_ZONE_PER_WIDTH=35;
    public static int GRIDTIMER_TABLE_PER_WIDTH =100-SHIP_ZONE_PER_WIDTH;

    public static int GRID_ZONE_PER_HEIGHT=80;
    public static int TIMER_ZONE_PER_HEIGHT=100-GRID_ZONE_PER_HEIGHT;

    public static int  SHIP_ZONE_WIDTH=  (APP_WIDTH/100)*SHIP_ZONE_PER_WIDTH;
    public static int  SHIP_ZONE_HEIGHT=  APP_HEIGHT;

    public static final int GRIDTIMER_TABLE_WIDTH =  (APP_WIDTH-SHIP_ZONE_WIDTH);
    public static final int GRIDTIMER_TABLE_HEIGHT =  APP_HEIGHT;


    public static final int GRID_ZONE_WIDTH= GRIDTIMER_TABLE_WIDTH;
    public static final int GRID_ZONE_HEIGHT= (GRIDTIMER_TABLE_HEIGHT/100)*GRID_ZONE_PER_HEIGHT;


    public static final int TIMER_ZONE_WIDTH= GRIDTIMER_TABLE_WIDTH;
    public static final int TIMER_ZONE_HEIGHT=  GRIDTIMER_TABLE_HEIGHT-GRID_ZONE_HEIGHT;


    /***********************************BUTTON****************************************************/

    public static final String START_BATTLE_BUTTON_TEXT ="";
    public static final String SURREND_BATTLE_BUTTON_TEXT ="";

    public static final String START_BATTLE_BUTTON_PACK = "Packs/placeShipPacks/startBattleButton.pack";
    public static final String SURREND_BUTTON_PACK= "Packs/placeShipPacks/surrendBattleButton.pack";
    public static final int START_BATTLE_BUTTON_WIDTH= TIMER_ZONE_WIDTH/5;

    public static String PLACE_SHIP_BUTTON_FONT = "Fonts/buttonFont.fnt";


    /**********************************GRID*******************************************************/

    public static final String SELECTED_TEXTURE_PATH= "Pictures/PlaceShipPictures/select.png";
    public static final String WATER_TEXTURE= "Pictures/PlaceShipPictures/water/water";
}
