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
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import me.lihq.game.screen.elements.UIHelpers;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Defines the assets that the game uses, and provides factory methods for loading textures
 * and creating fonts.
 */
public class Assets {
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
    public static BitmapFont FONT45, FONT30, FONT20, FONT15;

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
     * @author JAAPAN
     */
    public static Skin UI_SKIN;
    
    /**
     * Default colour for text.
     */
    public static final Color TEXT_COLOUR = Color.RED;
    
    /**
     * Default colours for UI buttons.
     * 
     * @author JAAPAN
     */
    private static final Color BUTTON_BACKGROUND_COLOR = Color.GRAY, 
    		BUTTON_DOWN_COLOR = Color.DARK_GRAY, 
    		BUTTON_OVER_COLOR = Color.LIGHT_GRAY;

    /**
     * Loads all assets for the game, such as textures, sound files and fonts.
     */
    public static void load() {
    	FONT45 = createFont("fofer", 45);
        FONT30 = createFont("arial", 30);
        FONT20 = createFont("arial", 20);
        FONT15 = new BitmapFont();

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
     * Initialises UI_SKIN, so the UI factory methods (getTextButton() etc.) can be used.
     * 
     * @author JAAPAN
     */
    private static void initSkin() {
        UI_SKIN = new Skin();
        
        Label.LabelStyle titleStyle = new Label.LabelStyle(FONT30, TEXT_COLOUR);
        Label.LabelStyle labelStyle = new Label.LabelStyle(FONT20, TEXT_COLOUR);
        UI_SKIN.add("title", titleStyle);
        UI_SKIN.add("default", labelStyle);

        //Create a texture
        UI_SKIN.add("background", UIHelpers.createBackgroundTexture(Color.ORANGE, Gdx.graphics.getWidth() / 4,
        		Gdx.graphics.getHeight() / 10));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = UI_SKIN.newDrawable("background", BUTTON_BACKGROUND_COLOR);
        textButtonStyle.down = UI_SKIN.newDrawable("background", BUTTON_DOWN_COLOR);
        textButtonStyle.checked = UI_SKIN.newDrawable("background", BUTTON_BACKGROUND_COLOR);
        textButtonStyle.over = UI_SKIN.newDrawable("background", BUTTON_OVER_COLOR);
        textButtonStyle.font = FONT15;
        UI_SKIN.add("default", textButtonStyle);

        // Use the checkbox textures
        UI_SKIN.add("uncheck", UNCHECKED_BOX);
        UI_SKIN.add("check", CHECKED_BOX);

        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.checkboxOff = UI_SKIN.getDrawable("uncheck");
        checkBoxStyle.checkboxOn = UI_SKIN.getDrawable("check");
        checkBoxStyle.font = FONT20;
        checkBoxStyle.fontColor = TEXT_COLOUR;
        UI_SKIN.add("default", checkBoxStyle);
        
        // Create the SliderStyle, using generated block textures
        UI_SKIN.add("slider", UIHelpers.createBackgroundTexture(Color.BLACK, 20, 20));
        UI_SKIN.add("knob", UIHelpers.createBackgroundTexture(Color.GRAY, 10, 10));
        
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.background = UI_SKIN.getDrawable("slider");
        sliderStyle.knob = UI_SKIN.getDrawable("knob");
        UI_SKIN.add("default-horizontal", sliderStyle);
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
    	FONT45.dispose();
    	FONT30.dispose();
    	FONT20.dispose();
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
     * under this name in the fonts directory
     * @param size The size of the font
     * @return The generated font
     * 
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
