package me.lihq.game;

import me.lihq.game.models.Map;
import me.lihq.game.models.Room;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Player;
import me.lihq.game.screen.Screens;
import me.lihq.game.screen.elements.Puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * This class stores all the vital information about a game state.
 *
 * Each player has one of these, storing the current state of their game.
 *
 * @author Lorem-Ipsum
 */
public class GameSnapshot {

    /**
     * A reference to the main game object
     */
    private GameMain game;

    /**
     * A list holding NPC objects
     */
    public List<NPC> NPCs = new ArrayList<NPC>();

    /**
     * The game map
     */
    public Map gameMap;

    /**
     * A player object for the player of the game
     */
    public Player player;

    /**
     * This is the puzzle for this specific player
     */
    private Puzzle puzzle;

    /**
     * Indicates if the player has solved the puzzle
     */
    public boolean puzzleSolved = false;
    /**
     * An NPC object for the killer. This allows us to easily access the name and room of the
     * killer, without having to iterate through each NPC.
     */
    public NPC killer;

    /**
     * An NPC object for the victim. This allows us to easily access the name of the victim,
     * without having to iterate through each NPC.
     */
    public NPC victim;

    /**
     * Count number of interactions remaining before switching to next player
     * Set to -1 if in single player mode so that prompt to switch player is never shown
     * PlayerSwitchScreen is shown if this = 0
     */
    public int interactionsRemaining = -1;

    /**
     * This is the maximum amount of interactions to be done before players switch
     */
    private static final int MULTIPLAYER_INTERACTION_LIMIT = 2;

    /**
     * This constructor creates a GameSnapshot with the provided information
     *
     * @param game - Reference to the GameMain instance
     * @param map - The map for the game
     * @param player - The player playing the game
     * @param npcs - List of NPCs in the maps
     *
     * @author Lorem-Ipsum
     */
    public GameSnapshot(GameMain game, Map map, Player player, List<NPC> npcs, boolean isMultiPlayer) {
        this.game = game;
        this.gameMap = map;
        this.player = player;
        this.NPCs = npcs;

        if (isMultiPlayer) {
            this.interactionsRemaining = MULTIPLAYER_INTERACTION_LIMIT;
        }
    }

    /**
     * This constructor creates a GameSnapshot from another GameSnapshot
     * Copying all the data to new spaces in memory
     *
     * @param other - The GameSnapshot to copy
     * @author Lorem-Ipsum
     */
    public GameSnapshot(GameSnapshot other)
    {
        this.game = other.game;

        gameMap = new Map(other.gameMap);

        player = new Player(game, "Player", "player.png", 3, 6);
        player.setRoom(gameMap.getRoom(0));

        for (NPC npc : other.NPCs)
        {
            NPCs.add(new NPC(npc, gameMap));
        }

        //Find victim from first snapshot and give it to the second
        this.victim = new NPC(other.victim, gameMap);

        //Find killer from first snapshot and give it to the second
        Predicate<NPC> killerPredicate = npc -> npc.getName().equals(other.killer.getName());
        NPC killer = other.NPCs.stream().filter(killerPredicate).findFirst().get();
        this.killer = new NPC(killer, gameMap);
        this.killer.setMotive(victim);

        this.interactionsRemaining = other.interactionsRemaining;
    }

    /**
     * This method returns a list of the NPCs that are in the specified room
     *
     * @param room The room to check
     * @return (List<NPC>) The NPCs that are in the specified room
     */
    public List<NPC> getNPCs(Room room) {
        List<NPC> npcsInRoom = new ArrayList<>();
        for (NPC n : this.NPCs) {
            if (n.getRoom() == room) {
                npcsInRoom.add(n);
            }
        }

        return npcsInRoom;
    }

    /**
     * This method returns the unique puzzle for this player
     *
     * @return Puzzle - puzzle
     */
    public Puzzle getPuzzle() {
        if (puzzle != null) return puzzle;

        this.puzzle = new Puzzle(this.game);
        return puzzle;
    }

    /**
     * This method is called when an interaction is completed.
     */
    public void finishedInteraction() {
        this.interactionsRemaining -= 1;
        if (this.interactionsRemaining == 0) {
            game.screenManager.navigationScreen.captureFrame = true;
            this.interactionsRemaining = MULTIPLAYER_INTERACTION_LIMIT;
            game.screenManager.setScreen(Screens.playerSwitch);
        }
    }
}
