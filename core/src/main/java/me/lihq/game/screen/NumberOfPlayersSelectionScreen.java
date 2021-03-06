package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.Menu;
import me.lihq.game.screen.elements.UIHelpers;

/**
 * Class that stores the screen for choosing how many players the game runs for in multi-player.
 *
 * @author Lorem Ipsum
 */
public class NumberOfPlayersSelectionScreen extends AbstractScreen {

    /**
     * Stage to put elements on.
     */
    private Stage stage;

    /**
     * Maximum number of players in one game.
     */
    public float maxPlayers = 10f;

    /**
     * Label for the playerCount.
     */
    private Label playerCountLabel;

    /**
     * Slider to change the number of players in a game.
     */
    private Slider playerCount;

    /**
     * This method is called when the screen is initialised.
     *
     * @param game
     */
    public NumberOfPlayersSelectionScreen(GameMain game) {
        super(game);

        // elements from the screen
        TextButton backButton;
        TextButton newGameButton;

        //set up the stage
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        //Setting the background
        Image background = new Image(new TextureRegion(Assets.loadTexture("menuBackground.jpg")));
        stage.addActor(background);

        //Create a backdrop for the UI so it is more visible
        Image backdrop = new Image(UIHelpers.createBackgroundTexture(new Color(0, 0, 0, 0.7f), (Menu.BUTTON_WIDTH * 2) + 80, 250));
        backdrop.setSize((Menu.BUTTON_WIDTH * 2) + 80, 250);
        backdrop.setPosition((Gdx.graphics.getWidth() / 2) - Menu.BUTTON_WIDTH - 40, Gdx.graphics.getHeight() / 3 - 20);
        stage.addActor(backdrop);

        //set up the slider
        playerCount = UIHelpers.createSlider(2f, maxPlayers, 1f, false);
        playerCount.setPosition((Gdx.graphics.getWidth() / 2) - 20 - Menu.BUTTON_WIDTH, Gdx.graphics.getHeight() / 2);
        playerCount.setSize((Menu.BUTTON_WIDTH * 2) + 40, playerCount.getHeight());

        //set up the text above the slider
        playerCountLabel = UIHelpers.createLabel("Number of Players: " + getNumPlayers(), Assets.TITLE_FONT, Color.WHITE);
        playerCountLabel.setPosition(Gdx.graphics.getWidth() / 2 - playerCountLabel.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 15);

        //set up the back button
        backButton = UIHelpers.createTextButton("Back");
        backButton.setPosition((Gdx.graphics.getWidth() / 2) + 20, Gdx.graphics.getHeight() / 3);
        backButton.setSize(Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.screenManager.setScreen(Screens.mainMenu);
            }
        });

        //set up the new game button
        newGameButton = UIHelpers.createTextButton("New Game");
        newGameButton.setPosition((Gdx.graphics.getWidth() / 2) - Menu.BUTTON_WIDTH - 20, Gdx.graphics.getHeight() / 3);
        newGameButton.setSize(Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.startGame((int) playerCount.getValue());
                game.screenManager.setScreen(Screens.navigation);
            }
        });

        //add all actors to the stage
        stage.addActor(playerCount);
        stage.addActor(playerCountLabel);
        stage.addActor(newGameButton);
        stage.addActor(backButton);

    }

    /**
     * This method disposes of all elements.
     */
    @Override
    public void dispose() {
        stage.dispose();
    }

    /**
     * This method is called each time the screen is rendered.
     *
     * @param delta The time in seconds since the last draw
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        playerCountLabel.setText("Number of Players: " + getNumPlayers());
        stage.act();
        stage.draw();
    }


    /**
     * This method is called when the game window is resized and resizes the stage accordingly.
     *
     * @param width  The new window width
     * @param height The new window height
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        // Add the stage to the input multiplexer, so it can receive input
        // without blocking other input controllers
        game.inputMultiplexer.addProcessor(stage);
    }

    @Override
    public void hide() {
        // Remove the stage from the input multiplexer, so it doesn't fire
        // event listeners when the screen is not visible
        game.inputMultiplexer.removeProcessor(stage);
    }

    /**
     * This method is called at the start of a game to determine how many players are playing.
     *
     * @return current value of the playerCount slider
     */
    public int getNumPlayers() {
        return (int) playerCount.getValue();
    }

    @Override
    public void update() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
