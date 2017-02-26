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
import me.lihq.game.screen.*;
import me.lihq.game.screen.elements.DebugOverlay;
import me.lihq.game.screen.elements.SpeechBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is the class responsible for the game as a whole. It manages the current states and entry points of the game
 */
public class GameMain extends Game {

    /**
     * A list holding NPC objects
     */
    public List<NPC> NPCs = new ArrayList<>();

    /**
     * The game map
     */
    public Map gameMap;

    /**
     * A player object for the player of the game
     */
    public Player player;

    /**
     * An NPC object for the killer. This allows us to easily access the name and room of the
     * killer, without having to iterate through each NPC.
     *
     * @author JAAPAN
     */
    public NPC killer;

    /**
     * An NPC object for the victim. This allows us to easily access the name of the victim,
     * without having to iterate through each NPC.
     *
     * @author JAAPAN
     */
    public NPC victim;
    /**
     * A screen to be used to display standard gameplay within the game , including the status bar.
     */
    public NavigationScreen navigationScreen;
    /**
     * The main menu screen that shows up when the game is first started.
     */
    public MainMenuScreen menuScreen;
    /**
     * The screen that is displayed when the game is paused.
     *
     * @author JAAPAN
     */
    public PauseScreen pauseScreen;
    /**
     * The screen that displays the player's inventory.
     *
     * @author JAAPAN
     */
    public InventoryScreen inventoryScreen;
    /**
     * The screen that allows the player to modify settings.
     *
     * @author JAAPAN
     */
    public SettingsScreen settingsScreen;
    /**
     * True if we're in the main menu, false otherwise. Used to determine whether to return
     * to the MainMenuScreen or PauseScreen when leaving the SettingsScreen.
     *
     * @author JAAPAN
     */
    public boolean mainMenu = true;
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
     * This is called at start up. It initialises the game.
     */
    @Override
    public void create() {
        Assets.load();// Load in the assets the game needs

        gameMap = new Map(this); //instantiate game map

        initialiseAllPeople();

        initialiseClues();

        /******************** Added by team JAAPAN ********************/
        // Load universal input class
        input = new GlobalInput(this);

        // Load input multiplexer and add universal input to it
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(input);
        Gdx.input.setInputProcessor(inputMultiplexer);
        /**************************** End *****************************/

        // Set up the various screens
        menuScreen = new MainMenuScreen(this);
        this.setScreen(menuScreen);

        navigationScreen = new NavigationScreen(this);
        navigationScreen.updateTiledMapRenderer();

        DebugOverlay.setGame(this);


        /******************** Added by team JAAPAN ********************/
        pauseScreen = new PauseScreen(this);
        inventoryScreen = new InventoryScreen(this);
        settingsScreen = new SettingsScreen(this);

        // Add an introductory speechbox
        navigationScreen.speechboxMngr.addSpeechBox(new SpeechBox(victim.getName() + " has been murdered! You must find the killer!", 5));

        Assets.MUSIC.play();
        /**************************** End *****************************/

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
        /******************** Added by team JAAPAN ********************/
        input.update(); // Update the global input controller
        /**************************** End *****************************/

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

        navigationScreen.dispose();
        menuScreen.dispose();
        pauseScreen.dispose();
        inventoryScreen.dispose();
        settingsScreen.dispose();
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
     * Generates all the NPC's, Players
     */
    public void initialiseAllPeople() {
        //Add ALL NPCs to the list
        //This is how you initialise an NPC
        player = new Player(this, "Player", "player.png", 3, 6);
        player.setRoom(gameMap.getRoom(0));

        NPC npc = new NPC(this, "Colin", "colin.png", 15, 17, gameMap.getRoom(0), "Colin.JSON");
        NPCs.add(npc);

        NPC npc2 = new NPC(this, "Diana", "diana.png", 4, 4, gameMap.getRoom(1), "Diana.JSON");
        NPCs.add(npc2);

        NPC npc3 = new NPC(this, "Lily", "lily.png", 0, 0, gameMap.getRoom(0), "Lily.JSON");
        NPCs.add(npc3);

        NPC npc4 = new NPC(this, "Mary", "mary.png", 0, 0, gameMap.getRoom(0), "Mary.JSON");
        NPCs.add(npc4);

        NPC npc5 = new NPC(this, "Mike", "mike.png", 0, 0, gameMap.getRoom(0), "Mike.JSON");
        NPCs.add(npc5);

        NPC npc6 = new NPC(this, "Will", "will.png", 0, 0, gameMap.getRoom(0), "Will.JSON");
        NPCs.add(npc6);

        /******************** Added by team JAAPAN ********************/
        NPC npc7 = new NPC(this, "Roger", "Roger.png", 0, 0, gameMap.getRoom(0), "Roger.JSON");
        NPCs.add(npc7);

        NPC npc8 = new NPC(this, "Horatio", "Horatio.png", 0, 0, gameMap.getRoom(0), "Horatio.JSON");
        NPCs.add(npc8);

        NPC npc9 = new NPC(this, "Kyle", "Kyle.png", 0, 0, gameMap.getRoom(0), "Kyle.JSON");
        NPCs.add(npc9);

        NPC npc10 = new NPC(this, "Adam", "Adam.png", 0, 0, gameMap.getRoom(0), "Adam.JSON");
        NPCs.add(npc10);
        /**************************** End *****************************/

        /*
        Generate who the Killer and Victim are
         */
        killer = NPCs.get(new Random().nextInt(NPCs.size()));
        while (!killer.setKiller()) {
            killer = NPCs.get(new Random().nextInt(NPCs.size()));
        }

        victim = NPCs.get(new Random().nextInt(NPCs.size()));
        while (!victim.setVictim()) {
            victim = NPCs.get(new Random().nextInt(NPCs.size()));
        }

        /******************** Added by team JAAPAN ********************/
        killer.setMotive(victim);
        // Remove the victim from the list of NPCs, so they aren't added to the game
        NPCs.remove(victim);
        /**************************** End *****************************/


        int amountOfRooms = gameMap.getAmountOfRooms();

        List<Integer> roomsLeft = new ArrayList<>();

        for (int i = 0; i < amountOfRooms; i++) {
            roomsLeft.add(i);
        }

        for (NPC loopNpc : NPCs) {
            /*
            Refill the rooms left list if there are more NPCs than Rooms. This will put AT LEAST one NPC per room if so.
             */
            if (roomsLeft.isEmpty()) {
                for (int i = 0; i < amountOfRooms; i++) {
                    roomsLeft.add(i);
                }
            }

            /*
            Pick a random room and put that NPC in it
             */
            int toTake = new Random().nextInt(roomsLeft.size());
            int selectedRoom = roomsLeft.get(toTake);
            roomsLeft.remove(toTake);

            loopNpc.setRoom(gameMap.getRoom(selectedRoom));
            Vector2Int position = loopNpc.getRoom().getRandomLocation();
            loopNpc.setTileCoordinates(position.x, position.y);

            System.out.println(loopNpc.getName() + " has been placed in room \"" + gameMap.getRoom(selectedRoom).getName() + "\" at " + position);
        }
        System.out.println();
    }

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
     * Initialises all the clues that are to be added to the game.
     *
     * @author JAAPAN
     */
    private void initialiseClues() {
        //This is a temporary list of clues
        List<Clue> tempClues = new ArrayList<>();

        Random random = new Random();
        List<Integer> clueIndices = new ArrayList<>();

        try {
            JsonValue jsonData = new JsonReader().parse(Gdx.files.internal("clues/clues.json"));

            // Get the total number of generic clues in the JSON file
            int totalClues = jsonData.get("clues").size;

            // Randomly select a number of clues, by generating random indices.
            // NUMBER_OF_CLUES - 1 is used because the murder weapon is added later.
            while (clueIndices.size() < Settings.NUMBER_OF_CLUES - 1) {
                int r = random.nextInt(totalClues);
                if (!clueIndices.contains(r)) {
                    clueIndices.add(r);
                }
            }

            for (int i = 0; i < Settings.NUMBER_OF_CLUES - 1; i++) {
                JsonValue entry = jsonData.get("clues").get(clueIndices.get(i));
                tempClues.add(new Clue(entry.name, entry.getString("description"), false, entry.getInt("x"), entry.getInt("y")));

                // Set the first clues in the list to red herrings (the number of red herrings
                // specified by NUMBER_OF_RED_HERRINGS). As the order of choosing clues is random,
                // this does not need to be further randomised.
                if (i < Settings.NUMBER_OF_RED_HERRINGS) {
                    tempClues.get(i).setRedHerring();
                }
            }

            // Choose a random murder weapon
            int murderWeapon = random.nextInt(jsonData.get("weapons").size);
            // Create the murder weapon from the JSON file
            JsonValue entry = jsonData.get("weapons").get(murderWeapon);
            tempClues.add(new Clue(entry.name, entry.getString("description"), true, entry.getInt("x"), entry.getInt("y")));

            System.out.println(entry.name + " is the murder weapon");
        } catch (Exception e) {
            // Display error message and close the game
            System.out.println("Fatal Error: Clues not working");
            Gdx.app.exit();
        }

        // Assign each clue to a randomly selected room.
        int amountOfRooms = gameMap.getAmountOfRooms();

        List<Integer> roomsLeft = new ArrayList<>();

        for (int i = 0; i < amountOfRooms; i++) {
            roomsLeft.add(i);
        }

        for (Clue clue : tempClues) {
            // Refill the rooms left list if there are more clues than rooms. This will put AT LEAST one clue per room if so.
            if (roomsLeft.isEmpty()) {
                for (int i = 0; i < amountOfRooms; i++) {
                    roomsLeft.add(i);
                }
            }
            int toTake = random.nextInt(roomsLeft.size());
            int selectedRoom = roomsLeft.get(toTake);
            roomsLeft.remove(toTake);
            Room room = gameMap.getRoom(selectedRoom);

            Vector2Int randHidingSpot = room.getRandHidingSpot();

            if (randHidingSpot != null) {
                room.addClue(clue.setTileCoordinates(randHidingSpot));
            }
        }
    }

    /**
     * Resets the state of the game.
     *
     * @author JAAPAN
     */
    public void resetAll() {
        // Would be better to reset the necessary variables, rather than recreate the objects

        // Clear the list of NPCs, ready to refill it
        NPCs.clear();

        // Recreate the map, so the murder room is randomly re-assigned
        gameMap = new Map(this);

        // Clear the input multiplexer, and add the global input controller
        inputMultiplexer.clear();
        inputMultiplexer.addProcessor(input);

        // Reinitialise all people and clues
        initialiseAllPeople();
        initialiseClues();

        // Recreate the navigation screen, so the references to the player and NPCs are
        // updated
        navigationScreen = new NavigationScreen(this);
        navigationScreen.updateTiledMapRenderer();

        mainMenu = true;
    }
}
