package me.lihq.game;


import java.util.HashMap;

/**
 * This class is used for game wide CONSTANTS or VARIABLES
 */
public class Settings
{
    /**
     * This is the global size of Tiles in the game
     */
    public static final int TILE_SIZE = 32;

    /**
     * The zoom level of the camera
     */
    public static final int ZOOM = 2;

    /**
     * The maximum amount of ticks per second
     */
    public static final int TPS = 60;
    
    /**
     * The size of the clue textures
     */
    public static final int CLUE_SIZE = 64;
    
    public static final int NUMBER_OF_CLUES = 10;
    
    public static boolean MUTED = false;
    
    public static float MUSIC_VOLUME = 0.5f;
    
    public static float SFX_VOLUME = 1f;

    /**
     * This is whether to draw some debug features to the screen
     * <p>
     * WARNING: DEBUG MODE IS LIKELY TO REDUCE FRAME RATE
     */
    public static boolean DEBUG = false;

    /**
     * This stores the debug options
     */
    public static HashMap<String, Boolean> DEBUG_OPTIONS;

    static {
        DEBUG_OPTIONS = new HashMap<String, Boolean>();
        DEBUG_OPTIONS.put("showHideable", true);
        DEBUG_OPTIONS.put("showWalkable", false);
    }
}
