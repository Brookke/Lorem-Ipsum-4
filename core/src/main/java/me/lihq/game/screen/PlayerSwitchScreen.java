package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.GameSnapshot;
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

    private Image scoreBackground;

    private int scoreBoardHeight=1;

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
         * get the correct height of the scoreboard
          */
        scoreBoardHeight = game.noPlayers*20;
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
         * Set up the label to tell which player to go next
         */
        next = UIHelpers.createLabel("Batman, are you ready?", Assets.TITLE_FONT, Color.WHITE);
        next.setPosition(Gdx.graphics.getWidth() / 2 - (next.getWidth() / 2), Gdx.graphics.getHeight() / 2);

        /**
         * Set up the button to click
         */
        TextButton continueButton;
        continueButton = UIHelpers.createTextButton("Ready?");
        continueButton.setSize(Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
        continueButton.setPosition((Gdx.graphics.getWidth() / 2) - (Menu.BUTTON_WIDTH / 2), Gdx.graphics.getHeight() / 2 + Menu.BUTTON_HEIGHT + 20);

        /**
         * Set up the current LeaderBoard //could add a background?
         */
        List<String> scoreBoard = new List<String>(Assets.UI_SKIN);
        scoreBoard.setSize(120,scoreBoardHeight);
        scoreBoard.setColor(0,0,0,0);
        scoreBoard.setItems(getScores());
        //set location of scoreBoard
        scoreBoard.setPosition((Gdx.graphics.getWidth() / 2) - 60, Gdx.graphics.getHeight() / 2 - scoreBoardHeight);

        /**
         * This is the background so that the UI is visible
         */
        background = new Image(UIHelpers.createBackgroundTexture(new Color(0, 0, 0, 0.7f), (int) (scoreBoard.getWidth() + 40), (int) ((text.getY() + text.getHeight()) - next.getY())));
        background.setPosition(Gdx.graphics.getWidth() / 2 - (background.getWidth() / 2), scoreBoard.getY()-30);

        /**
         * This is the background so that the scoreBoard is visible
         */
        scoreBackground = new Image(UIHelpers.createBackgroundTexture(new Color(0, 0, 0, 0.7f), (int) (140), (int) (scoreBoardHeight+10)));
        scoreBackground.setPosition(Gdx.graphics.getWidth() / 2 - (scoreBackground.getWidth() / 2),scoreBoard.getY()-scoreBoardHeight/game.noPlayers);

        /**
         * Add the actors and a button listener
         */
        stage.addActor(background);
        stage.addActor(scoreBackground);
        stage.addActor(next);
        stage.addActor(text);
        stage.addActor(continueButton);
        stage.addActor(scoreBoard);
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

    private Array<String> getScores()
    {
        //get each player + the players score
        Array<String> arrayPlayers = new Array<String>(game.noPlayers+1);
        int count = 0;
        arrayPlayers.add("");//blank one for a gap so the selected player doesn't go weird
        for (GameSnapshot snapshot: game.gameSnapshots)
        {
            arrayPlayers.add(snapshot.player.getName()+" Score: "+snapshot.player.getScore());
            count++;
        }
        return arrayPlayers;
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
