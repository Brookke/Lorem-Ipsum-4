package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.UIHelpers;

/**
 * Created by Beno11224 on 05/03/2017.
 */
public class NumberOfPlayersSelectionScreen extends AbstractScreen{


    /**
     * maximum number of players in one game
     */
    public float maxPlayers = 10f;

    /**
     * Label for the playerCount
     */
    private Label playerCountLabel;

    /**
     * slider to change the number of players in a game
     */
    private Slider playerCount;

    public NumberOfPlayersSelectionScreen(GameMain game)
    {
        /**
         * add in the field to change the number of players.
         *
         * @autor Lorem Ipsum
         **/
        playerCount = UIHelpers.createSlider(1f,maxPlayers,1f,false);
        playerCount.setPosition(Gdx.graphics.getWidth()/2-playerCount.getWidth()/2,Gdx.graphics.getHeight()/12);

        playerCountLabel = UIHelpers.createLabel("Number of Players: "+getNumPlayers(),false);
        playerCountLabel.setPosition(Gdx.graphics.getWidth()/2-playerCountLabel.getWidth()/2,Gdx.graphics.getHeight()/10);

        menu.stage.addActor(playerCount);
        menu.stage.addActor(playerCountLabel);

        /**end of addition**/
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
        playerCountLabel.setText("Number of Players: "+getNumPlayers());
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void update() {

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
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
