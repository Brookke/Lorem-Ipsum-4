package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.Menu;
import me.lihq.game.screen.elements.UIHelpers;

/**
 * Class added by Lorem Ipsum
 * <p>
 * Screen displayed when the second player switches in and the first player switches out.
 *
 * @AUTHOR Lorem Ipsum
 */
public class PlayerSwitchScreen extends AbstractScreen {

    private Label next;

    private Image background;

    /**
     * The main stage for rendering and handling input.
     */
    private Stage stage;

    /**
     * This constructs the object to be shown when players switch
     *
     * @param game - Reference to the game object
     */
    public PlayerSwitchScreen(GameMain game) {
        super(game);

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    /**
     * This initialises all the objects to go on the screen
     */
    private void initScreen()
    {
        /**
         * Setup the background
         */
        Image im = game.screenManager.navigationScreen.recentFrame;
        stage.addActor(im);

        /**
         * Set up the Text Label that tells the players to switch
         */
        Label text;
        text = UIHelpers.createLabel("Time to switch players!", true);
        text.setPosition(Gdx.graphics.getWidth() / 2 - (text.getWidth() / 2), Gdx.graphics.getHeight() * 3 / 4);

        /**
         * Set up the button to click
         */
        TextButton continueButton;
        continueButton = UIHelpers.createTextButton("Ready?");
        continueButton.setSize(Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
        continueButton.setPosition((Gdx.graphics.getWidth() / 2) - (Menu.BUTTON_WIDTH / 2), Gdx.graphics.getHeight() / 2 + Menu.BUTTON_HEIGHT + 20);

        /**
         * Set up the label to tell which player to go next
         */
        next = UIHelpers.createLabel("Batman, are you ready?", Assets.TITLE_FONT, Color.WHITE);
        next.setPosition(Gdx.graphics.getWidth() / 2 - (next.getWidth() / 2), Gdx.graphics.getHeight() / 2);

        /**
         * This is the background so that the UI is visible
         */
        background = new Image(UIHelpers.createBackgroundTexture(new Color(0, 0, 0, 0.7f), (int) (next.getWidth() + 40), (int) ((text.getY() + text.getHeight()) - next.getY())));
        background.setPosition(Gdx.graphics.getWidth() / 2 - (background.getWidth() / 2), Gdx.graphics.getHeight() / 2);

        stage.addActor(background);

        /**
         * Add the actors and a button listener
         */
        stage.addActor(next);
        stage.addActor(text);
        stage.addActor(continueButton);
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.nextPlayer();
            }
        });
    }

    @Override
    public void show() {
        game.screenManager.navigationScreen.captureFrame = false;

        initScreen();
        updateUI();

        game.inputMultiplexer.addProcessor(stage);
    }

    /**
     * This method updates the objects on the screen to the new sizes based on the next players name
     */
    public void updateUI()
    {
        int nextPlayer = game.currentPlayerId + 1;

        if (nextPlayer == game.noPlayers)
        {
            nextPlayer = 0;
        }

        next.setText(game.gameSnapshots.get(nextPlayer).player.getName() + ", are you ready?");
        next.setPosition(Gdx.graphics.getWidth() / 2 - (next.getWidth() / 2), Gdx.graphics.getHeight() / 2);

        background.setSize((int) (next.getWidth() * 1.5f), background.getHeight());
        background.setPosition(Gdx.graphics.getWidth() / 2 - (background.getWidth() / 2), Gdx.graphics.getHeight() / 2);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void hide() {
        game.inputMultiplexer.removeProcessor(stage);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
