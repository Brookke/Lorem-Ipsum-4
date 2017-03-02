package me.lihq.game;

import me.lihq.game.models.Room;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.NPC;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUnit tests for the NPC class.
 */
public class NPCUnitTests extends GameTester {
    public NPC colin;
    public Room room;

    @Before
    public void makeNPC() {
        room = new Room(null, 0, "testRoom0.tmx", "Test Room 0");
        colin = new NPC(null, "bob", "colin.png", 1, 1, room, "Colin.JSON");
    }

    @Test
    public void testGetName() {
        assertEquals("getting the name of the NPC failing", "bob", colin.getName());
    }

    @Test
    public void testPersonality() {
        assertEquals(AbstractPerson.Personality.NICE, colin.getPersonality());
    }

}

