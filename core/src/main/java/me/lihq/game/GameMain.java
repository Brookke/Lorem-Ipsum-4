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
import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.controller.GlobalInput;
import me.lihq.game.screen.AbstractScreen;
import me.lihq.game.screen.ScreenManager;
import me.lihq.game.screen.Screens;
import me.lihq.game.screen.elements.SpeechBox;

import java.util.List;

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
     * Checks if the player has obtained extra score before.
     * This is in the GameMain class so that there's only one instance of the variable during multiplayer gameplay
     */
    public boolean scoreObtained = false;

     /**
     * An FPSLogger, FPSLogger allows us to check the game FPS is good enough
     */
    FPSLogger FPS;

    /**
     * Stores variable with number of players
     *
     * @author Lorem-Ipsum
     */
    public int noPlayers = 1;

    /**
     * Stores variable with current player ID
     *
     * @author Lorem-Ipsum
     */
    public int currentPlayerId = 0;

    /**
     * This stores the tile location in the main room that the hidden room is located at
     *
     * @author Lorem-Ipsum
     */
    public Vector2Int hiddenRoomLocation = null;

    /**
     * This is the game snapshot of the currently playing player
     *
     * @author Lorem-Ipsum
     */
    public GameSnapshot currentSnapshot;

    /**
     * GameSnapshots for all players
     *
     * @author Lorem-Ipsum
     */
    public List<GameSnapshot> gameSnapshots;

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


        // Load universal input class
        input = new GlobalInput(this);

        // Load input multiplexer and add universal input to it
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(input);
        Gdx.input.setInputProcessor(inputMultiplexer);

        screenManager = new ScreenManager(this);
        screenManager.setScreen(Screens.mainMenu);

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
        screenManager.update();
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
     * Initialises variables for starting the game
     * @param noPlayers - number of players in game
     *
     * @author Lorem-Ipsum
     */
    public void startGame(int noPlayers) {
        this.noPlayers = noPlayers;

        //Initialise GameSnapshots
        ScenarioBuilder builder = new ScenarioBuilder(this);
        gameSnapshots = builder.generateGame(noPlayers);
        currentPlayerId = 0;
        currentSnapshot = gameSnapshots.get(currentPlayerId);

        // Add an introductory SpeechBox
        screenManager.navigationScreen.init();
        screenManager.navigationScreen.speechboxMngr.addSpeechBox(new SpeechBox(currentSnapshot.victim.getName() + " has been murdered! You must find the killer!"));
    }

    public void nextPlayer() {
        //Load next player's GameSnapshot into currentSnapshot
        currentPlayerId++;
        if (currentPlayerId == noPlayers) {
            currentPlayerId = 0;
        }
        currentSnapshot = gameSnapshots.get(currentPlayerId);

        //Reset number of interactions remaining
        currentSnapshot.interactionsRemaining = 2;

        //Change screen
        screenManager.setScreen(Screens.navigation);
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
    }
}
