package me.lihq.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Map;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by joeshuff on 02/03/2017.
 *
 * @author Lorem-Ipsum
 */
public class ScenarioBuilder {

    /**
     * Reference to the main game object
     */
    private GameMain game;

    /**
     * Constructor. Sets the game variable to the parameter
     * @param game
     */
    public ScenarioBuilder(GameMain game)
    {
        this.game = game;
    }

    /**
     * Initialises all the clues that are to be added to the game.
     *
     * @author Lorem-Ipsum
     */
    private void initialiseClues(Map map) {

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

                /**
                Set the first clues in the list to red herrings (the number of red herrings
                specified by NUMBER_OF_RED_HERRINGS). As the order of choosing clues is random,
                this does not need to be further randomised.
                */
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
        int amountOfRooms = map.getAmountOfRooms();

        List<Integer> roomsLeft = new ArrayList<>();

        for (int i = 0; i < amountOfRooms; i++) {
            if (map.getRoom(i).getName().equals("Secret Room")) continue;
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
            Room room = map.getRoom(selectedRoom);

            Vector2Int randHidingSpot = room.getRandHidingSpot();

            if (randHidingSpot != null) {
                room.addClue(clue.setTileCoordinates(randHidingSpot));
            } else {
                throw new IndexOutOfBoundsException("No places to Place Clue " + clue.getName() + " in room " + room.getName());
            }
        }
    }

    /**
     * This method generates the single player
     */
    private Player initialisePlayer(Map map, int playerNo)
    {
        Player player = new Player(game, "Player " + playerNo, "player.png", 3, 6);
        player.setRoom(map.getRoom(0));
        return player;
    }

    /**
     * Generates all the NPC's, Players
     *
     * @author Lorem-Ipsum
     */
    private List<NPC> initialiseAllPeople(Map map) {

        List<NPC> NPCs = new ArrayList<NPC>();

        //Add ALL NPCs to the list
        //This is how you initialise an NPC
        NPC npc = new NPC(game, "Colin", "colin.png", 15, 17, map.getRoom(0), "Colin.JSON");
        NPCs.add(npc);

        NPC npc2 = new NPC(game, "Diana", "diana.png", 4, 4, map.getRoom(1), "Diana.JSON");
        NPCs.add(npc2);

        NPC npc3 = new NPC(game, "Lily", "lily.png", 0, 0, map.getRoom(0), "Lily.JSON");
        NPCs.add(npc3);

        NPC npc4 = new NPC(game, "Mary", "mary.png", 0, 0, map.getRoom(0), "Mary.JSON");
        NPCs.add(npc4);

        NPC npc5 = new NPC(game, "Mike", "mike.png", 0, 0, map.getRoom(0), "Mike.JSON");
        NPCs.add(npc5);

        NPC npc6 = new NPC(game, "Will", "will.png", 0, 0, map.getRoom(0), "Will.JSON");
        NPCs.add(npc6);

        NPC npc7 = new NPC(game, "Roger", "Roger.png", 0, 0, map.getRoom(0), "Roger.JSON");
        NPCs.add(npc7);

        NPC npc8 = new NPC(game, "Horatio", "Horatio.png", 0, 0, map.getRoom(0), "Horatio.JSON");
        NPCs.add(npc8);

        NPC npc9 = new NPC(game, "Kyle", "Kyle.png", 0, 0, map.getRoom(0), "Kyle.JSON");
        NPCs.add(npc9);

        NPC npc10 = new NPC(game, "Adam", "Adam.png", 0, 0, map.getRoom(0), "Adam.JSON");
        NPCs.add(npc10);

        int amountOfRooms = map.getAmountOfRooms();

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

             /*
              Checks whether the randomly selected room is the secret room,
              if it is then an NPC is not placed there and instead another room is selected
            */
            if (map.getRoom(selectedRoom).getName().equals("Secret Room")) {
                toTake = new Random().nextInt(roomsLeft.size());
                selectedRoom = roomsLeft.get(toTake);
                roomsLeft.remove(toTake);
            }
            
            loopNpc.setRoom(map.getRoom(selectedRoom));
            Vector2Int position = loopNpc.getRoom().getRandomLocation();
            loopNpc.setTileCoordinates(position.x, position.y);

            System.out.println(loopNpc.getName() + " has been placed in room \"" + map.getRoom(selectedRoom).getName() + "\" at " + position);
        }

        return NPCs;
    }

    /**
     * This method creates and returns the game data
     *
     * @return GameSnapshot - The game data
     *
     * @author Lorem-Ipsum
     */
    private GameSnapshot getInitialGameSnapshot(boolean isMultiPlayer)
    {
        Map map = new Map(game);
        map.setRandomMurderRoom();
        Player player = initialisePlayer(map, 1);
        List<NPC> NPCs = initialiseAllPeople(map);

        GameSnapshot snapshot = new GameSnapshot(game, map, player, NPCs, isMultiPlayer);

        /*
        Generate who the Killer and Victim are
         */
        NPC killer = NPCs.get(new Random().nextInt(NPCs.size()));
        while (!killer.setKiller()) {
            killer = NPCs.get(new Random().nextInt(NPCs.size()));
        }

        NPC victim = NPCs.get(new Random().nextInt(NPCs.size()));
        while (!victim.setVictim()) {
            victim = NPCs.get(new Random().nextInt(NPCs.size()));
        }

        killer.setMotive(victim);
        // Remove the victim from the list of NPCs, so they aren't added to the game
        NPCs.remove(victim);

        //This is open for discussion. Do we need to store the list of clues? Because the Clues are stored in the Room which is stored in the Map...
        //I dont think we need to store the clues seperately as they are in the rooms
        initialiseClues(map);

        snapshot.victim = victim;
        snapshot.killer = killer;

        return snapshot;
    }


    /**
     * This method creates and returns the game data
     *
     * @param noPlayers - number of players in game
     * @return List<GameSnapshot> - The game data
     *
     * @author Lorem-Ipsum
     */
    public List<GameSnapshot> generateGame(int noPlayers)
    {
        boolean isMultiPlayer = false;
        if (noPlayers > 1) {
            isMultiPlayer = true;
        }

        //Generate Player 1's game, used as a base for further players
        GameSnapshot snapshot = getInitialGameSnapshot(isMultiPlayer);

        //Return both game snapshots
        List<GameSnapshot> allSnapshots = new ArrayList<>();
        allSnapshots.add(snapshot);

        //For each Player generate a game snapshot, with same victim and killer
        for (int i = 0; i < (noPlayers - 1); i++){

            GameSnapshot nextSnapshot = new GameSnapshot(snapshot);

            nextSnapshot.player.setName("Player " + (i + 2));

            allSnapshots.add(nextSnapshot);
        }

        return allSnapshots;
    }

}
