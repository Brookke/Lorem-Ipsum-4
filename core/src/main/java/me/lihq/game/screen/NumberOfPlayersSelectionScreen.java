package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.UIHelpers;

/**
 * Class that stores the screen for choosing how many players the game runs for in multi-player.
 *
 * @author Lorem Ipsum
 */
public class NumberOfPlayersSelectionScreen extends AbstractScreen{

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
    public NumberOfPlayersSelectionScreen(GameMain game)
    {
        super(game);

        // elements from the screen
        TextButton backButton;

        //set up the stage
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        //set up the slider
        playerCount = UIHelpers.createSlider(1f,maxPlayers,1f,false);
        playerCount.setPosition(Gdx.graphics.getWidth()/2-playerCount.getWidth()/2,Gdx.graphics.getHeight()/2);

        //set up the text above the slider
        playerCountLabel = UIHelpers.createLabel("Number of Players: "+getNumPlayers(),false);
        playerCountLabel.setPosition(Gdx.graphics.getWidth()/2-playerCountLabel.getWidth()/2,Gdx.graphics.getHeight()/(5/2));

        //set up the back button
        backButton = UIHelpers.createTextButton("Back");
        backButton.setPosition(Gdx.graphics.getWidth()/2-backButton.getWidth()/2,Gdx.graphics.getHeight()/4);

        //add all actors to the stage
        stage.addActor(playerCount);
        stage.addActor(playerCountLabel);
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
        playerCountLabel.setText("Number of Players: "+getNumPlayers());
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

    /**
     * This method is called at the start of a game to determine how many players are playing.
     *
     * @return current value of the playerCount slider
     */
    public int getNumPlayers()
    {
        return (int)playerCount.getValue();
    }

    @Override
    public void update() {}

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
