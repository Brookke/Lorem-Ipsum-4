package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.Menu;
import me.lihq.game.screen.elements.UIHelpers;

/**
 * This controls the MainMenuScreen that is the first thing the user sees
 */
public class MainMenuScreen extends AbstractScreen {
    /**
     * This is the menu element
     */
    private Menu menu;

    /**
     * This is the camera for the screen
     */
    private OrthographicCamera camera = new OrthographicCamera();

    /**
     * maximum number of players in one game
     */
    public float maxPlayers = 2f;

    /**
     * slider to change the number of players in a game
     */
    private Slider playerCount = new Slider(1f,maxPlayers,1f,false,new Skin());

    /**
     * The constructor for the MainMenuScreen
     *
     * @param game The game it is getting created for
     */
    public MainMenuScreen(GameMain game) {
        super(game);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        //Setting up the camera
        camera.setToOrtho(false, w, h);
        camera.update();

        //Creates a Main Menu object thus creating the main menu
        menu = new Menu(game, false);

        /**
         * add in the field to change the number of players.
         *
         * @autor Lorem Ipsum
         **/
        Label playerCountLabel;
        playerCountLabel = UIHelpers.createLabel("Number of Players:",false);
        playerCountLabel.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/16);

        playerCount.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/20);

        menu.stage.addActor(playerCount);
        menu.stage.addActor(playerCountLabel);

        /**end of addition**/
    }

    /**
     * This method is called to show the Menu Screen
     */
    @Override
    public void show() {
        game.inputMultiplexer.addProcessor(menu.stage);
    }

    /**
     * This method is called once a tick
     */
    @Override
    public void update() {
    }

    /**
     * This method is called once a render loop to render the menu
     *
     * @param delta The time in seconds since the last draw
     */
    @Override
    public void render(float delta) {
        //Renders the main menu
        menu.render();
    }

    /**
     * This is called when the window is resized
     *
     * @param width  The new width
     * @param height The new height
     */
    @Override
    public void resize(int width, int height) {
        menu.resize(width, height);
    }

    /**
     * This is called when the focus is lost on the window
     */
    @Override
    public void pause() {
    }

    /**
     * This method is called when the window is brought back into focus
     */
    @Override
    public void resume() {
    }

    /**
     * This method is called when the user hides the window
     */
    @Override
    public void hide() {
        game.inputMultiplexer.removeProcessor(menu.stage);
    }

    /**
     * This is to be called when you want to dispose of all data
     */
    @Override
    public void dispose() {
        //Disposes the main menu
        menu.dispose();
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

}
