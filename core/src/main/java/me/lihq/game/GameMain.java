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
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Map;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Player;
import me.lihq.game.people.controller.GlobalInput;
import me.lihq.game.screen.AbstractScreen;
import me.lihq.game.screen.ScreenManager;
import me.lihq.game.screen.Screens;
import me.lihq.game.screen.elements.SpeechBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
     * The ScreenManager to handle all GUI screens of the game
     */
    public ScreenManager screenManager;

    /**
     * This is called at start up. It initialises the game.
     */
    @Override
    public void create() {
        Assets.load();// Load in the assets the game needs

        gameMap = new Map(this); //instantiate game map 

        initialiseAllPeople();

        initialiseClues();

        // Load universal input class
        input = new GlobalInput(this);

        // Load input multiplexer and add universal input to it
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(input);
        Gdx.input.setInputProcessor(inputMultiplexer);

        screenManager = new ScreenManager(this);
        screenManager.setScreen(Screens.mainMenu);

        // Add an introductory speechbox
        screenManager.navigationScreen.speechboxMngr.addSpeechBox(new SpeechBox(victim.getName() + " has been murdered! You must find the killer!", 5));

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


    /**MOVE TO SNAPSHOT*/
    /**
     * This method returns a list of the NPCs that are in the specified room
     *
     * @param room The room to check
     * @return (List<NPC>) The NPCs that are in the specified room
     */
    public List<NPC> getNPCS(Room room) {
        List<NPC> npcsInRoom = new ArrayList<>();
        for (NPC n : this.NPCs) {
            if (n.getRoom() == room) {
                npcsInRoom.add(n);
            }
        }

        return npcsInRoom;
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
