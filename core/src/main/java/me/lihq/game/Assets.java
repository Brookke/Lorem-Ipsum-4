package me.lihq.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Defines the assets that the game uses, and provides factory methods for loading textures
 * and creating fonts.
 */
public class Assets {
    /**
     * Default colour for text.
     */
    public static final Color TEXT_COLOUR = new Color(0.5f, 0f, 0f, 1f);

    /**
     * The asset sheet for clues.
     */
    public static Texture CLUE_SHEET;
    /**
     * The textures for the checkbox UI item.
     *
     * @author JAAPAN
     */
    public static TextureRegion UNCHECKED_BOX, CHECKED_BOX;
    /**
     * These TextureRegions store the 4 different directions that the room changing
     * arrows can face.
     */
    public static TextureRegion UP_ARROW;
    public static TextureRegion DOWN_ARROW;
    public static TextureRegion LEFT_ARROW;
    public static TextureRegion RIGHT_ARROW;
    /**
     * This is the asset for the RoomTag {@link me.lihq.game.screen.elements.RoomTag}.
     */
    public static Texture TAG_BORDER;
    /**
     * The default fonts used in the game - the number specifies the size. Used to render
     * UI elements and room tags.
     *
     * @author JAAPAN
     */
    public static BitmapFont FONT15;
    /**
     * This it the animation for the clue glint to be drawn where a clue is hidden.
     */
    public static Animation CLUE_GLINT;
    /**
     * Used for streaming the soundtrack.
     *
     * @author JAAPAN
     */
    public static Music MUSIC;
    /**
     * Used for playing the sound effect when a clue is found.
     *
     * @author JAAPAN
     */
    public static Sound SOUND;
    /**
     * Global skin storing the standard UI styles, used in the UI factory methods.
     *
     * @author Lorem Ipsum
     */
    public static Skin UI_SKIN;

    public static Skin CHECK_SKIN;

    /**
     * Global font storing the standard UI style for titles.
     *
     * @author LOREM IPSUM
     */
    public static BitmapFont TITLE_FONT;

    /**
     * Loads all assets for the game, such as textures, sound files and fonts.
     */
    public static void load() {

        FONT15 = new BitmapFont();
        /**
         * @Lorem Ipsum
         */
        TITLE_FONT = createFont("Stranger back in the Night", 80);

        Texture arrows = loadTexture("arrows.png");
        LEFT_ARROW = new TextureRegion(arrows, 0, 0, 32, 32);
        RIGHT_ARROW = new TextureRegion(arrows, 32, 0, 32, 32);
        DOWN_ARROW = new TextureRegion(arrows, 0, 32, 32, 32);
        UP_ARROW = new TextureRegion(arrows, 32, 32, 32, 32);

        TAG_BORDER = loadTexture("border.png");

        CLUE_SHEET = loadTexture("clueSheet.png");

        Texture checkbox = loadTexture("checkbox.png");
        UNCHECKED_BOX = new TextureRegion(checkbox, 0, 0, 32, 32);
        CHECKED_BOX = new TextureRegion(checkbox, 32, 0, 32, 32);

        Texture glintFile = loadTexture("glintSheet.png");
        TextureRegion[][] splitFrames = TextureRegion.split(glintFile, 32, 32);
        TextureRegion[] frames = splitFrames[0];

        CLUE_GLINT = new Animation(0.1f, frames);

        MUSIC = Gdx.audio.newMusic(Gdx.files.internal("music/background.ogg"));
        MUSIC.setVolume(Settings.MUSIC_VOLUME);
        MUSIC.setLooping(true);

        SOUND = Gdx.audio.newSound(Gdx.files.internal("music/clue-found.ogg"));

        initSkin();
    }

    /**
     * Creating the global skins using freetype fonts.
     *
     * @author LOREM IPSUM
     */
    private static void initSkin() {
        UI_SKIN = new Skin(Gdx.files.internal("skins/skin/skin.json"));
        CHECK_SKIN= new Skin(Gdx.files.internal("skins/skin_default/uiskin.json"));
    }

    /**
     * This method takes a direction and returns the corresponding arrow asset for that direction
     *
     * @param direction The direction to fetch
     * @return (TextureRegion) the corresponding TextureRegion
     */
    public static TextureRegion getArrowDirection(String direction) {
        if (direction.equals("NORTH")) {
            return UP_ARROW;
        } else if (direction.equals("SOUTH")) {
            return DOWN_ARROW;
        } else if (direction.equals("WEST")) {
            return LEFT_ARROW;
        } else if (direction.equals("EAST")) {
            return RIGHT_ARROW;
        }

        return null;
    }

    /**
     * Safely disposes of all assets, freeing memory. Using assets after calling dispose()
     * will result in undefined behaviour.
     *
     * @author JAAPAN
     */
    public static void dispose() {
        CLUE_SHEET.dispose();
        TAG_BORDER.dispose();
        MUSIC.dispose();
        SOUND.dispose();
        UI_SKIN.dispose();
    }

    /*************************************************************************/
    /**************************** Factory Methods ****************************/
    /*************************************************************************/

    /**
     * @param file The file that contains the textures
     * @return The new texture
     */
    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
    }

    /**
     * Creates a BitmapFont with the specified font and size.
     *
     * @param font The name of the font - must be stored as a .ttf file
     *             under this name in the fonts directory
     * @param size The size of the font
     * @return The generated font
     * @author JAAPAN
     */
    public static BitmapFont createFont(String font, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/" + font + ".ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont f = generator.generateFont(parameter);
        generator.dispose();

        return f;
    }

}
