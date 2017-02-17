package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.GameMain;

/**
 * The status bar shown throughout the game
 * Contains UI controls for presenting the game status to the player
 */
public class StatusBar
{
	/**
	 * Variable for the game
	 */
	private GameMain game;
	
    /**
     * The height of the StatusBar
     */
    public static final int HEIGHT = 50; //Used to set height of status bar

    /**
     * The amount of items that are in the StatusBar
     */
    private static final int ITEM_COUNT = 4; //Used to set width of controls on bar

    /**
     * The width of the StatusBar
     */
    private static final int WIDTH = (int) Gdx.graphics.getWidth() / ITEM_COUNT;

    /**
     * The background color of the StatusBar
     */
    private static final Color BACKGROUND_COLOR = Color.GRAY;

    /**
     * The stage to render the elements to
     */
    public Stage stage;

    /**
     * The skin for the UI elements
     */
    private Skin skin;
    
    /**
     * Variable for score label
     */
    private Label scoreLabel;
    
    private Label personalityLabel;

    /**
     * The constructor for the StatusBar.
     * Sets up UI controls and adds them to the stage ready for rendering
     */
    public StatusBar(final GameMain game)
    {
    	this.game = game;
    	
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        initSkins();

        Table statusBar = new Table();
        statusBar.setSize(Gdx.graphics.getWidth(), HEIGHT);
        statusBar.setPosition(0, 0);
        statusBar.row().height(HEIGHT);
        statusBar.defaults().width(WIDTH);

        scoreLabel = new Label("Score: " + game.player.getScore(), skin);
        scoreLabel.setAlignment(Align.center, Align.center);
        statusBar.add(scoreLabel).uniform();

        personalityLabel = new Label("Personality: " + game.player.getPersonality().toString(), skin);
        personalityLabel.setAlignment(Align.center, Align.center);
        statusBar.add(personalityLabel).uniform();

        TextButton inventoryButton = new TextButton("Inventory", skin);
        statusBar.add(inventoryButton).uniform();
        inventoryButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
            	game.navigationScreen.playerController.clear();
            	game.setScreen(game.inventoryScreen);
            }
        });

        TextButton pauseButton = new TextButton("Pause", skin);
        statusBar.add(pauseButton).uniform();
        pauseButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.isPaused = true;
                game.navigationScreen.playerController.clear();
                game.setScreen(game.pauseScreen);
            }
        });

        stage.addActor(statusBar);
    }

    /**
     * Renders the status bar.
     * Should be called within the render() method of a screen
     */
    public void render()
    {
    	scoreLabel.setText("Score: " + game.player.getScore());
    	personalityLabel.setText("Personality: " + game.player.getPersonality().toString());
        stage.act();
        stage.draw();
    }

    /**
     * This method is called on a window resize
     *
     * @param width  - the new width
     * @param height - the new height
     */
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height, true);
    }

    /**
     * This disposes all the elements
     */
    public void dispose()
    {
        stage.dispose();
    }

    /**
     * Sets up skin variable used for defining UI control styles
     * 
     * @author JAAPAN
     */
    private void initSkins()
    {
        // Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        // Create a texture
        Pixmap pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(BACKGROUND_COLOR);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        // Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background");
        textButtonStyle.down = skin.newDrawable("background", Color.BLACK);
        textButtonStyle.checked = skin.newDrawable("background");
        textButtonStyle.over = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);


        // Create a label style
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        labelStyle.background = skin.getDrawable("background");
        skin.add("default", labelStyle);
    }
}
