package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.screen.Screens;

/**
 * Reusable Menu UI, can be used for the pause screen as well.
 */

public class Menu {
    /**
     * Button width and height
     *
     * @author Lorem Ipsum
     */
    public static final int BUTTON_WIDTH = Gdx.graphics.getWidth() / 3;
    public static final int BUTTON_HEIGHT = 80;
    private static final int BUTTON_MARGIN = BUTTON_HEIGHT / 4;
    private static final int BUTTON_START = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 3) - 50;

    /**
     * The width of the menu
     */
    private static final int WIDTH = Gdx.graphics.getWidth() / 2 - BUTTON_WIDTH / 2;

    /**
     * the stage to render the menu to
     */
    public Stage stage;

    /**
     * Game for menu
     */
    private GameMain game;

    /**
     * This stores whether or not the menu is for the main menu (false) or pause menu (true)
     */
    private boolean pauseMenu;

    /**
     * Constructor for the menu
     *
     * @param game      The game object the menu is being loaded for
     * @param pauseMenu Whether it is a pause menu or not
     */
    public Menu(final GameMain game, boolean pauseMenu) {
        this.game = game;

        //Initialising new stage
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.pauseMenu = pauseMenu;

        //Loading the menu or pause screen
        initMenu(this.game);
    }

    /**
     * This method is called if you want to initialise the main menu
     *
     * @param game The game to initialise the menu for
     */
    private void initMenu(final GameMain game) {
        //Creating the label containing text and determining its size and location on screen
        Label text;

        TextButton resumeGameButton = UIHelpers.createTextButton("Resume Game");
        resumeGameButton.setPosition(WIDTH, BUTTON_START);
        resumeGameButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        TextButton newGameButton = UIHelpers.createTextButton("New Game");
        newGameButton.setPosition(WIDTH, BUTTON_START);
        newGameButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        //Setting the background
        Image background = new Image(new TextureRegion(Assets.loadTexture("menuBackground.jpg")));
        stage.addActor(background);

        TextButton settings = UIHelpers.createTextButton("Settings");
        settings.setPosition(WIDTH, BUTTON_START - 1 * (BUTTON_HEIGHT + BUTTON_MARGIN));
        settings.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        TextButton quit = UIHelpers.createTextButton("Quit");
        quit.setPosition(WIDTH, BUTTON_START - 2 * (BUTTON_HEIGHT + BUTTON_MARGIN));
        quit.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        if (pauseMenu) {
            text = UIHelpers.createLabel("Paused", true);
            stage.addActor(resumeGameButton);

        } else {
            //Add in a multi-player button on the main menu only
            TextButton multiplayerButton = UIHelpers.createTextButton("Multi-Player");
            multiplayerButton.setPosition(WIDTH, BUTTON_START - 1 * (BUTTON_HEIGHT + BUTTON_MARGIN));
            multiplayerButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            multiplayerButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.screenManager.setScreen(Screens.numberOfPlayersSelection);
                }
            });

            settings.setPosition(WIDTH, BUTTON_START - 2 * (BUTTON_HEIGHT + BUTTON_MARGIN));
            quit.setPosition(WIDTH, BUTTON_START - 3 * (BUTTON_HEIGHT + BUTTON_MARGIN));

            stage.addActor(multiplayerButton);
            stage.addActor(newGameButton);

            text = UIHelpers.createLabel("Welcome to Lorem Ipsum's Murder Mystery Game!", true);
        }

        text.setPosition(Gdx.graphics.getWidth() / 2 - text.getWidth() / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 6);

        //Loading the buttons and the background onto the stage
        //stage.addActor(background);
        stage.addActor(text);
        stage.addActor(settings);
        stage.addActor(quit);

        //Making the "New Game" button clickable and causing it to start the game
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.startGame(1);
                game.screenManager.setScreen(Screens.navigation);
            }
        });

        //Making the "Resume" button clickable and causing it to start the game
        resumeGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.screenManager.setScreen(Screens.navigation);
            }
        });

        //Making the "Quit" button clickable and causing it to close the game
        quit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        /******************** Added by team JAAPAN ********************/
        //Making the "Settings" button clickable and causing it to load the settings screen
        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.screenManager.setScreen(Screens.settings);
            }
        });
        /**************************** End *****************************/
    }

    /**
     * This method is called to render the main menu to the stage
     */
    public void render() {
        //Determining the background colour of the menu
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Rendering the buttons
        stage.act();
        stage.draw();
    }

    /**
     * This method disposes of all elements
     */
    public void dispose() {
        //Called when disposing the main menu
        stage.dispose();
    }

    /**
     * This method is called when the window is resized.
     *
     * @param width  The new width
     * @param height The new height
     */
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
