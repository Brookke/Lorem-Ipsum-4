package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.screen.Screens;

/**
 * The status bar shown throughout the game.
 * Contains UI controls for presenting the game status to the player, and allowing
 * them to access the inventory and the pause menu.
 */
public class StatusBar {
    /**
     * The height of the status bar
     */
    public static final int HEIGHT = 50;
    /**
     * The number of items that are in the status bar. Used to set the width of the elements
     * on the bar
     */
    private static final int ITEM_COUNT = 4;
    /**
     * The width of each element of the status bar
     */
    private static final int WIDTH = (int) Gdx.graphics.getWidth() / ITEM_COUNT;
    /**
     * The background colour of the status bar
     */
    private static final Color BACKGROUND_COLOR = Color.GRAY;
    /**
     * The stage to render the elements to
     */
    public Stage stage;
    /**
     * Reference to the game; used for accessing the player's score and personality,
     * and changing the screen when buttons are pressed
     */
    private GameMain game;
    /**
     * The skin for the UI elements
     *
     * @author JAAPAN
     */
    private Skin skin;

    /**
     * The label displaying the player's current score
     *
     * @author JAAPAN
     */
    private Label scoreLabel;

    /**
     * The label displaying the player's personality
     *
     * @author JAAPAN
     */
    private Label personalityLabel;

    /**
     * The constructor for the StatusBar.
     * Sets up UI controls and adds them to the stage ready for rendering.
     */
    public StatusBar(final GameMain game) {
        this.game = game;

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        initSkin();

        // Create a table, so we can more easily lay the elements out
        Table statusBar = new Table();
        statusBar.setSize(Gdx.graphics.getWidth(), HEIGHT);
        statusBar.setPosition(0, 0);
        statusBar.row().height(HEIGHT);
        statusBar.defaults().width(WIDTH);

        scoreLabel = new Label("Score: " + game.currentSnapshot.player.getScore(), skin);
        scoreLabel.setAlignment(Align.center, Align.center);
        statusBar.add(scoreLabel).uniform();

        personalityLabel = new Label("Personality: " + game.currentSnapshot.player.getPersonality().toString(), skin);
        personalityLabel.setAlignment(Align.center, Align.center);
        statusBar.add(personalityLabel).uniform();

        TextButton inventoryButton = new TextButton("Inventory", skin);
        statusBar.add(inventoryButton).uniform();
        inventoryButton.addListener(new ClickListener() {
            /**
             * Called when the button is clicked. Changes the current screen to the
             * inventory.
             *
             * @author JAAPAN
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.screenManager.setScreen(Screens.inventory);
            }
        });

        TextButton pauseButton = new TextButton("Pause", skin);
        statusBar.add(pauseButton).uniform();
        pauseButton.addListener(new ClickListener() {
            /**
             * Called when the button is clicked. Changes the current screen to the
             * pause menu.
             *
             * @author JAAPAN
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.screenManager.setScreen(Screens.playerSwitch);
            }
        });

        stage.addActor(statusBar);
    }

    /**
     * Renders the status bar and updates the score and personality labels.
     * Should be called within the render() method of a screen.
     */
    public void render() {
        /******************** Added by team JAAPAN ********************/
        scoreLabel.setText("Score: " + game.currentSnapshot.player.getScore());
        personalityLabel.setText("Personality: " + game.currentSnapshot.player.getPersonality().toString());
        /**************************** End *****************************/
        stage.act();
        stage.draw();
    }

    /**
     * This method is called on a window resize.
     *
     * @param width  the new width
     * @param height the new height
     */
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Disposes all the elements
     */
    public void dispose() {
        stage.dispose();
    }

    /**
     * Sets up skin variable used for defining UI control styles.
     *
     * @author JAAPAN
     */
    private void initSkin() {
        skin = new Skin();

        // Create a texture
        skin.add("background", UIHelpers.createBackgroundTexture(BACKGROUND_COLOR, WIDTH, HEIGHT));

        // Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background");
        textButtonStyle.down = skin.newDrawable("background", Color.BLACK);
        textButtonStyle.checked = skin.newDrawable("background");
        textButtonStyle.over = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.font = Assets.FONT15;
        skin.add("default", textButtonStyle);

        //Create a button style
        Label.LabelStyle labelStyle = new Label.LabelStyle(Assets.FONT15, Color.WHITE);
        labelStyle.background = skin.getDrawable("background");
        skin.add("default", labelStyle);
    }
}
