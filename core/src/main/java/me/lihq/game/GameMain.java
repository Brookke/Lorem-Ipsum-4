/*
* Game executable:
*
* http://jaapan.alexcummins.uk/JAAPAN-MITRCH-A3.jar
*
*/

package me.lihq.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import me.lihq.game.people.controller.GlobalInput;
import me.lihq.game.screen.AbstractScreen;
import me.lihq.game.screen.ScreenManager;
import me.lihq.game.screen.Screens;
import me.lihq.game.screen.elements.SpeechBox;

/**
 * This is the class responsible for the game as a whole. It manages the current states and entry points of the game
 */
public class GameMain extends Game {

    /**
     * Universal input handler
     *
     * @author JAAPAN
     */
    public GlobalInput input;

    /**
     * Input multiplexer to control multiple inputs across project
     *
     * @author JAAPAN
     */
    public InputMultiplexer inputMultiplexer;

    /**
     * An FPSLogger, FPSLogger allows us to check the game FPS is good enough
     */
    FPSLogger FPS;

    /**
     * This is the game snapshot of the currently playing player
     *
     * @author Lorem-Ipsum
     */
    public GameSnapshot currentSnapshot;

    /**
     * The ScreenManager to handle all GUI screens of the game
     */
    public ScreenManager screenManager;

    /**
     * This is called at start up. It initialises the game.
     */
    @Override
    public void create() {
        Assets.load();// Load in the assets the game needs

        //INITIALISE GAME SNAPSHOTS
        ScenarioBuilder builder = new ScenarioBuilder(this);
        currentSnapshot = builder.generateGame();

        // Load universal input class
        input = new GlobalInput(this);

        // Load input multiplexer and add universal input to it
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(input);
        Gdx.input.setInputProcessor(inputMultiplexer);

        screenManager = new ScreenManager(this);
        screenManager.setScreen(Screens.mainMenu);

        // Add an introductory speechbox
        screenManager.navigationScreen.speechboxMngr.addSpeechBox(new SpeechBox(currentSnapshot.victim.getName() + " has been murdered! You must find the killer!", 5));

        Assets.MUSIC.play();

        //Instantiate the FPSLogger to show FPS
        FPS = new FPSLogger();
    }

    /**
     * This defines what's rendered on the screen for each frame.
     */
    @Override
    public void render() {
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        FPS.log();//this is where fps is displayed
        input.update(); // Update the global input controller
        super.render(); // This calls the render method of the screen that is currently set
    }

    /**
     * Called when the Application is destroyed. Should release all assets from memory.
     *
     * @author JAAPAN
     */
    @Override
    public void dispose() {
        Assets.dispose();
        screenManager.dispose();
    }

    /**
     * Overrides the getScreen method to return our AbstractScreen type.
     * This means that we can access the additional methods like update.
     *
     * @return The current screen of the game.
     */
    @Override
    public AbstractScreen getScreen() {
        return (AbstractScreen) super.getScreen();
    }

    /**
     * Resets the state of the game.
     *
     * @author JAAPAN
     */
    public void resetAll() {
        // Clear the input multiplexer, and add the global input controller
        inputMultiplexer.clear();
        inputMultiplexer.addProcessor(input);

        // Reset screenManager
        screenManager.reset();
        screenManager.setScreen(Screens.mainMenu);

        //POTENTIALLY GENERATE A NEW GAMESNAPSHOT HERE?!?!
    }
}
