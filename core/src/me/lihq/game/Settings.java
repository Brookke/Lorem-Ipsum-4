package me.lihq.game;


import java.util.HashMap;

/**
 * This class is used for game wide CONSTANTS or VARIABLES
 */
public class Settings {
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
     *
     * @author JAAPAN
     */
    public static final int CLUE_SIZE = 64;
    
    /**
     * The number of clues to be used in a given game
     *
     * @author JAAPAN
     */
    public static final int NUMBER_OF_CLUES = 10;
    
    /**
     * The number of clues which should be red herrings; i.e. point
     * the player in the wrong direction
     *
     * @author JAAPAN
     */
    public static final int NUMBER_OF_RED_HERRINGS = 4;
  
    /**
     * The muted state of the game. Initially false, changed via the checkbox
     * in SettingsScreen. When muted, the music is paused and sound effects do not play
     *
     * @author JAAPAN
     */
    public static boolean MUTED = false;
    
    /**
     * The volume of the music. Represented by a value between 0 (effectively muted) and
     * 1 (full volume)
     *
     * @author JAAPAN
     */
    public static float MUSIC_VOLUME = 0.5f;
    
    /**
     * The volume of the sound effects. Represented by a value between 0 (effectively muted) 
     * and 1 (full volume)
     *
     * @author JAAPAN
     */
    public static float SFX_VOLUME = 1f;

    /**
     * This is whether to draw some debug features to the screen
     * <p>
     * WARNING: DEBUG MODE IS LIKELY TO REDUCE FRAME RATE
     * </p>
     */
    public static boolean DEBUG = false;

    /**
     * The debug options
     */
    public static HashMap<String, Boolean> DEBUG_OPTIONS;

    static {
        DEBUG_OPTIONS = new HashMap<String, Boolean>();
        DEBUG_OPTIONS.put("showHideable", true);
        DEBUG_OPTIONS.put("showWalkable", false);
    }
}
