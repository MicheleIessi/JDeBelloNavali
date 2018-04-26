package com.debellonavali;

public class Constants {

    // Desktop application constants
    public static String APP_TITLE = "De Bello Navali";

    public static int APP_WIDTH = 1280;
    public static int APP_HEIGHT = 720;
    public static boolean APP_FULLSCREEN = false;
    public static boolean APP_RESIZABLE = false;

    // Bitmap fonts
    public static String SPLASH_TITLE_FONT = "Fonts/titleFont.fnt";
    public static String SPLASH_BUTTON_FONT = "Fonts/buttonFont.fnt";
    public static String HEADER_INFO_FONT = "Fonts/headerInfoFont.fnt";

    // Music and audio
    public static boolean SOUND_ENABLED = false;
    public static String AUDIO_MAIN_THEME = "Music/theme.ogg";

    // Texture packs
    public static String SPLASH_BUTTON_PACK = "Packs/titleButton.pack";
    public static String GRID_CELL_PACK = "Packs/gridCell.pack";

    // Pictures
    public static String SPLASH_BACKGROUND_IMG = "Pictures/background.jpeg";
    public static String BATTLE_BACKGROUND_IMG = "Pictures/sfondoFasi.png";


    /* --- Label & Button text --- */
    // Splash Screen
    public static String SPLASH_LABEL_TITLE = "DE  BELLO  NAVALI";
    public static String SPLASH_PLAY_TEXT = "PLAY NOW";
    public static String SPLASH_EXIT_TEXT = "EXIT";

    // Battle Screen
    public static String BATTLE_LABEL_HEADER = "Ship Info:";


    /* --- Grid Tables Position and Dimension constants --- */
    public static float GRID_CELL_MAX_DIMENSION = APP_WIDTH / 18;
    public static float GRID_CELL_MIN_DIMENSION = APP_WIDTH / 54;

    public static float GRID_MINIMIZED_POS_X = APP_WIDTH / 4;
    public static float GRID_MINIMIZED_POS_Y = 0;
    public static float GRID_MINIMIZED_WIDTH = APP_WIDTH / 4;
    public static float GRID_MINIMIZED_HEIGHT = APP_WIDTH / 4;
    public static float GRID_MINIMIZED_PADDING = 10;

    public static float GRID_MAXIMIZED_POS_X = APP_WIDTH / 2;
    public static float GRID_MAXIMIZED_POS_Y = 0;
    public static float GRID_MAXIMIZED_WIDTH = APP_WIDTH / 2;
    public static float GRID_MAXIMIZED_HEIGHT = APP_HEIGHT;
    public static float GRID_MAXIMIZED_PADDING = 20;


}
