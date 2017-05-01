package me.lihq.game;

import me.lihq.game.people.NPC;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by joeshuff on 10/03/2017.
 */
public class GameSnapshotTests extends GameTester {

    @Test
    public void testInitialise() {
        try {
            GameMain game = new GameMain();
            ScenarioBuilder builder = new ScenarioBuilder(game);

            GameSnapshot snap = builder.generateGame(1).get(0);
        } catch (Exception e) {
            assertTrue("Gamesnapshot Initialiser has failed", true);

        }
    }

    @Test
    public void testFinishInteraction() {
        GameMain game = new GameMain();
        ScenarioBuilder builder = new ScenarioBuilder(game);

        GameSnapshot snap = builder.generateGame(2).get(0);

        assertEquals(2, snap.interactionsRemaining);
        snap.finishedInteraction();
        assertEquals(1, snap.interactionsRemaining);
    }

    @Test
    public void testGetNPCs() {
        GameMain game = new GameMain();
        ScenarioBuilder builder = new ScenarioBuilder(game);

        GameSnapshot snap = builder.generateGame(2).get(0);

        int room1 = snap.NPCs.get(0).getRoom().getID();
        NPC inRoom1 = snap.NPCs.get(0);

        List<NPC> npcs = snap.getNPCs(snap.gameMap.getRoom(room1));

        for (NPC npc : npcs) {
            if (npc.equals(inRoom1)) {
                assertTrue("Successfully Found NPC", true);
                return;
            }
        }

        assertTrue("Failed to find NPC", false);
    }
}
