/*
* This is the link to the executable jar file created from this project
*
* http://www.lihq.me/Downloads/Assessment2/Game.jar
*
* or visit http://www.lihq.me
* and click "Download Game"
 */

package me.lihq.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import me.lihq.game.models.Clue;
import me.lihq.game.models.Map;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Player;
import me.lihq.game.people.controller.GlobalInput;
import me.lihq.game.screen.AbstractScreen;
import me.lihq.game.screen.InventoryScreen;
import me.lihq.game.screen.MainMenuScreen;
import me.lihq.game.screen.NavigationScreen;
import me.lihq.game.screen.PauseScreen;

/**
 * This is the class responsible for the game as a whole. It manages the current states and entry points of the game
 */
public class GameMain extends Game
{
    /**
     * This is a static reference to itself. Comes in REALLY handy when in other classes that don't have a reference to the main game
     */
    public static GameMain me = null;

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
     * A screen to be used to display standard gameplay within the game , including the status bar.
     */
    public NavigationScreen navigationScreen;

    /**
     * An FPSLogger, FPSLogger allows us to check the game FPS is good enough
     */
    FPSLogger FPS;

    /**
     * The main menu screen that shows up when the game is first started
     */
    public MainMenuScreen menuScreen;

    public PauseScreen pauseScreen;
    
    public InventoryScreen inventoryScreen;
    
    /**
     * Universal input handler
     */
    public GlobalInput input;
    
    /**
     * Input multiplexer to control multiple inputs across project
     */
    public InputMultiplexer inputMultiplexer;

    /**
     * This is called at start up. It initialises the game.
     */
    @Override
    public void create()
    {
        this.me = this;

        Assets.load();// Load in the assets the game needs

        gameMap = new Map(); //instantiate game map

        initialiseAllPeople();

        initialiseClues();
        
        // Load universal input class
        input = new GlobalInput(this);
        
        // Load input multiplexer and add universal input to it
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(input);
        Gdx.input.setInputProcessor(inputMultiplexer);

        //set up the screen and display the first room

        //Set up the Menu
        menuScreen = new MainMenuScreen(this);
        this.setScreen(menuScreen);

        navigationScreen = new NavigationScreen(this);
        navigationScreen.updateTiledMapRenderer();
        
        pauseScreen = new PauseScreen(this);
        inventoryScreen = new InventoryScreen(this);

        //Instantiate the FPSLogger to show FPS
        FPS = new FPSLogger();
    }

    /**
     * This defines what's rendered on the screen for each frame.
     */
    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        FPS.log();//this is where fps is displayed
        
        input.update();

        super.render(); // This calls the render method of the screen that is currently set

    }

    /**
     * This is to be called when you want to dispose of all data
     */
    @Override
    public void dispose()
    {

    }

    /**
     * Overrides the getScreen method to return our AbstractScreen type.
     * This means that we can access the additional methods like update.
     *
     * @return The current screen of the game.
     */
    @Override
    public AbstractScreen getScreen()
    {
        return (AbstractScreen) super.getScreen();
    }

    /**
     * Generates all the NPC's, Players
     */
    public void initialiseAllPeople()
    {
        //Add ALL NPCs to the list
        //This is how you initialise an NPC
        player = new Player("Player", "player.png", 3, 6);
        player.setRoom(gameMap.getRoom(0));

        //TODO: Sort NPC personalities
        NPC npc = new NPC("Colin", "colin.png", 15, 17, gameMap.getRoom(0), true, "Colin.JSON");
        NPCs.add(npc);

        NPC npc2 = new NPC("Diana", "diana.png", 4, 4, gameMap.getRoom(1), true, "Diana.JSON");
        NPCs.add(npc2);

        NPC npc3 = new NPC("Lily", "lily.png", 0, 0, gameMap.getRoom(0), true, "Lily.JSON");
        NPCs.add(npc3);

        NPC npc4 = new NPC("Mary", "mary.png", 0, 0, gameMap.getRoom(0), true, "Mary.JSON");
        NPCs.add(npc4);

        NPC npc5 = new NPC("Mike", "mike.png", 0, 0, gameMap.getRoom(0), true, "Mike.JSON");
        NPCs.add(npc5);

        NPC npc6 = new NPC("Will", "will.png", 0, 0, gameMap.getRoom(0), true, "Will.JSON");
        NPCs.add(npc6);

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
            int toTake = new Random().nextInt(roomsLeft.size() - 1);
            int selectedRoom = roomsLeft.get(toTake);
            roomsLeft.remove(toTake);

            loopNpc.setRoom(gameMap.getRoom(selectedRoom));
            Vector2Int position = loopNpc.getRoom().getRandomLocation();
            loopNpc.setTileCoordinates(position.x, position.y);

            System.out.println(loopNpc.getName() + " has been placed in room " + selectedRoom + " at " + position);
        }

        /*
        Generate who the Killer and Victim are
         */
        NPC killer = NPCs.get(new Random().nextInt(NPCs.size() - 1));

        while (!killer.setKiller()) {
            killer = NPCs.get(new Random().nextInt(NPCs.size() - 1));
        }

        NPC victim = NPCs.get(new Random().nextInt(NPCs.size() - 1));


        while (!victim.setVictim()) {
            victim = NPCs.get(new Random().nextInt(NPCs.size() - 1));
        }
    }

    /**
     * This method returns a list of the NPCs that are in the specified room
     *
     * @param room - The room to check
     * @return (List<NPC>) The NPCs that are in the specified room
     */
    public List<NPC> getNPCS(Room room)
    {
        List<NPC> npcsInRoom = new ArrayList<>();
        for (NPC n : this.NPCs) {
            if (n.getRoom() == room) {
                npcsInRoom.add(n);
            }
        }

        return npcsInRoom;
    }

    /**
     * This method initialises all the clues that are to be added to the games.
     */
    private void initialiseClues()
    {
        //This is a temporary list of clues
        List<Clue> tempClues = new ArrayList<>();


        tempClues.add(new Clue("Big Footprint", "A big footprint left at the crime scene by the killer.", false, new TextureRegion(Assets.CLUE_SHEET, 0, 0, Settings.CLUE_SIZE, Settings.CLUE_SIZE)));
        tempClues.add(new Clue("Small Footprint", "A small footprint left at the crime scene by the killer.", false, new TextureRegion(Assets.CLUE_SHEET, 0, 0, Settings.CLUE_SIZE, Settings.CLUE_SIZE)));
        tempClues.add(new Clue("Glasses", "A pair of glasses these were found by another detective at the crime scene.", false, new TextureRegion(Assets.CLUE_SHEET, 0, 0, Settings.CLUE_SIZE, Settings.CLUE_SIZE)));
        tempClues.add(new Clue("Bag", "A bag. Someone must have left in a hurry.", false, new TextureRegion(Assets.CLUE_SHEET, 0, 0, Settings.CLUE_SIZE, Settings.CLUE_SIZE)));
        tempClues.add(new Clue("Lipstick", "Lipstick, a killers best friend.", false, new TextureRegion(Assets.CLUE_SHEET, 0, 0, Settings.CLUE_SIZE, Settings.CLUE_SIZE)));
        tempClues.add(new Clue("Right-Handed fountain pen", "A bloodstained fountain pen. The killer must be right-handed", false, new TextureRegion(Assets.CLUE_SHEET, 0, 0, Settings.CLUE_SIZE, Settings.CLUE_SIZE)));
        tempClues.add(new Clue("Dark Hair", "A dark hair from the crime scene", false, new TextureRegion(Assets.CLUE_SHEET, 0, 0, Settings.CLUE_SIZE, Settings.CLUE_SIZE)));
        tempClues.add(new Clue("Erotic Novel", "An erotic novel the killer left behind. At least now we know that they have terrible taste.", false, new TextureRegion(Assets.CLUE_SHEET, 0, 0, Settings.CLUE_SIZE, Settings.CLUE_SIZE)));
        tempClues.add(new Clue("Broken Mobile Phone", "A broken mobile phone. Perhaps somebody will recognise it.", false, new TextureRegion(Assets.CLUE_SHEET, 0, 0, Settings.CLUE_SIZE, Settings.CLUE_SIZE)));
        tempClues.add(new Clue("Car Keys", "A set of car keys left at the crimescene by the killer.", false, new TextureRegion(Assets.CLUE_SHEET, 0, 0, Settings.CLUE_SIZE, Settings.CLUE_SIZE)));

        Collections.shuffle(tempClues);

        for (Room room : gameMap.getRooms()) {
            if (tempClues.isEmpty()) return;

            Vector2Int randHidingSpot = room.getRandHidingSpot();

            if (randHidingSpot != null) {
                room.addClue(tempClues.get(0).setTileCoordinates(randHidingSpot));
                tempClues.remove(0);
            }

        }

    }
}
