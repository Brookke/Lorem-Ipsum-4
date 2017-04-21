package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.lihq.game.Assets;

import java.util.Random;

/**
 * Provides various factory methods for use in creating a UI.
 */
public class UIHelpers {

    /**
     * Creates a new block texture, for use as the background of UI controls.
     *
     * @param colour The colour of the texture
     * @param width  The width of the texture
     * @param height The height of the texture
     * @return A new texture with the specified parameters
     * @author JAAPAN
     */
    public static Texture createBackgroundTexture(Color colour, int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(colour);
        pixmap.fill();

        return new Texture(pixmap);
    }

    /**
     * Creates a new block texture, and returns it as a {@link Drawable} object.
     *
     * @param colour The colour of the texture
     * @param width  The width of the texture
     * @param height The height of the texture
     * @return A new {@link Drawable} object with the specified parameters
     * @author JAAPAN
     */
    public static Drawable createBackgroundDrawable(Color colour, int width, int height) {
        return new Image(createBackgroundTexture(colour, width, height)).getDrawable();
    }

    /**
     * Creates a new label, using the style defined in UI_SKIN. If the label is a title,
     * its default position is set to the middle of the top of the screen.
     *
     * @param text  The text to display in the label
     * @param title Use the title font
     * @return A new label with the standard style and specified text
     * @author JAAPAN/Lorem Ipsum
     */
    public static Label createLabel(String text, boolean title) {
        if (title) {
            Label label = createLabel(text, Assets.TITLE_FONT, Assets.TEXT_COLOUR);
            label.setPosition(Gdx.graphics.getWidth() / 2 - label.getWidth() / 2,
                    Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 16);
            return label;
        } else {
            return new Label(text, Assets.UI_SKIN);
        }
    }

    /**
     * Creates a new label with the specified text and font, and the standard text colour. This
     * generates a new LabelStyle on the fly, which is less efficient than using one of the
     * pre-existing ones defined in UI_SKIN. If the font is arial of size 20 or 30, therefore, use
     * {@link #createLabel(String, boolean)} instead.
     *
     * @param text The text to display in the label
     * @param font The font to use
     * @return A new label with the specified text and font
     * @author JAAPAN
     */
    public static Label createLabel(String text, BitmapFont font) {
        return createLabel(text, font, Assets.TEXT_COLOUR);
    }

    /**
     * Creates a new label with the specified text, font and colour. Identical to a call to
     * {@link #createLabel(String, BitmapFont)}, except it allows you to set the text colour
     * as well.
     *
     * @param text   The text to display in the label
     * @param font   The font to use
     * @param colour The colour of the text
     * @return A new label with the specified text, font and colour
     * @author JAAPAN
     */
    public static Label createLabel(String text, BitmapFont font, Color colour) {
        Label.LabelStyle style = new Label.LabelStyle(font, colour);
        return new Label(text, style);
    }

    /**
     * Creates a new text button, using the style defined in UI_SKIN.
     *
     * @param text The text to display in the button
     * @return A new text button with the standard style and specified text
     * @author JAAPAN
     */
    public static TextButton createTextButton(String text) {
        return new TextButton(text, Assets.UI_SKIN);
    }

    /**
     * Creates a new button, using the style defined in UI_SKIN.
     *
     * @return A new text button with the standard style and specified text
     * @author Lorem-Ipsum
     */
    public static Button createButton() {
        return new Button(Assets.UI_SKIN);
    }

    /**
     * Creates a new checkbox, using the style defined in UI_SKIN. Prepends the text with
     * 2 spaces, to add a gap between it and the checkbox texture.
     * @param text The text to display next to the checkbox
     * @return A new checkbox with the standard style and specified text
     * @author JAAPAN
     */
    public static CheckBox createCheckBox(String text) {
        return new CheckBox("  " + text, Assets.CHECK_SKIN);
    }

    /**
     * Creates a new slider control, using the style define in UI_SKIN.
     *
     * @param min      The minimum value of the slider
     * @param max      The maximum value of the slider
     * @param stepSize The size of the increments
     * @param vertical Whether the slider should be vertical or horizontal
     * @return A new slider with the standard style and specified attributes
     * @author JAAPAN
     */
    public static Slider createSlider(float min, float max, float stepSize, boolean vertical) {
        return new Slider(min, max, stepSize, vertical, Assets.CHECK_SKIN);
    }

    public static Image createImage(){
        String fileName = Assets.Images.get(new Random().nextInt(Assets.Images.size()));
        Texture temp = new Texture(Gdx.files.internal(fileName));
        Image b = new Image(temp);
        return b;
    }
}
