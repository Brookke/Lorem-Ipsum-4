package me.lihq.game;

import com.badlogic.gdx.Game;
import me.lihq.game.models.Map;
import me.lihq.game.models.Room;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by joeshuff on 02/03/2017.
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
     * This constructor creates a GameSnapshot with the provided information
     *
     * @param game - Reference to the GameMain instance
     * @param map - The map for the game
     * @param player - The player playing the game
     * @param npcs - List of NPCs in the maps
     *
     * @author Lorem-Ipsum
     */
    public GameSnapshot(GameMain game, Map map, Player player, List<NPC> npcs) {
        this.game = game;
        this.gameMap = map;
        this.player = player;
        this.NPCs = npcs;
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

        this.victim = new NPC(other.victim, gameMap);

        //Find victim from first snapshot and give it to the second

        //Find killer from first snapshot and give it to the second
        Predicate<NPC> killerPredicate = npc -> npc.getName().equals(other.killer.getName());
        NPC killer = other.NPCs.stream().filter(killerPredicate).findFirst().get();
        this.killer = new NPC(killer, gameMap);
        this.killer.setMotive(victim);
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
}
