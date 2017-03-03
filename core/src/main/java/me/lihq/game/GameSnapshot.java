package me.lihq.game;

import com.badlogic.gdx.Game;
import me.lihq.game.models.Map;
import me.lihq.game.models.Room;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joeshuff on 02/03/2017.
 *
 * @author Lorem-Ipsum
 */
public class GameSnapshot {

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
     */
    public NPC killer;

    /**
     * An NPC object for the victim. This allows us to easily access the name of the victim,
     * without having to iterate through each NPC.
     */
    public NPC victim;

    public GameSnapshot(Map map, Player player, List<NPC> npcs) {
        this.gameMap = map;
        this.player = player;
        this.NPCs = npcs;
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

}
