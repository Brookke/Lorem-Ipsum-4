import me.lihq.game.Settings;
import me.lihq.game.people.Player;
import me.lihq.game.models.Room;
import org.junit.Before;
import org.junit.Test;

import static me.lihq.game.people.AbstractPerson.*;
import static org.junit.Assert.*;

/**
 * JUnit tests for the Player class.
 */
public class PlayerUnitTests extends GameTester {
    Player p = null;

    @Before
    public void before() {
        p = new Player("Test Name", "player.png", 0, 0);
        p.setRoom(new Room(0, "testMap.tmx", "Test Map"));
    }

    @Test
    public void testPlayername() {
        assertEquals("Fail - Not returning correct playername", p.getName(), "Test Name");
    }

    /**
     * @author JAAPAN
     */
    @Test
    public void testPlayerPersonality() {
        //Personality Level is default 50 - NEUTRAL
        p.changePersonality(Personality.NICE);
        //Should have moved towards NICE (i.e. increased)
        assertTrue("Fail - Personality level has not increased", p.getPersonalityLevel() > 50);

        // Should cap at 100
        for (int i = 0; i < 100; i++) {
        	p.changePersonality(Personality.NICE);
        }
        assertEquals("Fail - Personality isn't NICE", p.getPersonality(), Personality.NICE);
        assertEquals("Fail - Personality not upper capped", 100, p.getPersonalityLevel());

        // Should lock at 50 - i.e. not go lower
        for (int i = 0; i < 100; i++) {
        	p.changePersonality(Personality.NEUTRAL);
        }
        assertEquals("Fail - Personality isn't NEUTRAL", p.getPersonality(), Personality.NEUTRAL);
        assertEquals("Fail - Personality not reduced to 50", 50, p.getPersonalityLevel());

        // Should cap at 0
        for (int i = 0; i < 100; i++) {
        	p.changePersonality(Personality.AGGRESSIVE);
        }
        assertEquals("Fail - Personality isn't AGGRESSIVE", p.getPersonality(), Personality.AGGRESSIVE);
        assertEquals("Fail - Personality not Lower Capped", 0, p.getPersonalityLevel());
    }


    /**
     * This tests if the move function is working correctly.
     * The player move function is in terms of tiles so an increase of 1 is actually 1 tile width.
     */
    @Test
    public void doesPlayerMove() {
        p.setTileCoordinates(0, 0);
        p.setAnimTime(0f);
        assertEquals(0, p.getX(), 0.0f);
        assertEquals(0, p.getY(), 0.0f);

        p.move(Direction.NORTH);
        p.update();
        p.pushCoordinatesToSprite();

        assertEquals(Settings.TILE_SIZE, p.getY(), 0.0f);

        p.move(Direction.EAST);
        p.update();
        p.pushCoordinatesToSprite();

        assertEquals(Settings.TILE_SIZE, p.getX(), 0.0f);
        assertEquals(Settings.TILE_SIZE, p.getY(), 0.0f);

        p.move(Direction.SOUTH);
        p.update();
        p.pushCoordinatesToSprite();

        assertEquals(Settings.TILE_SIZE, p.getX(), 0.0f);
        assertEquals(0, p.getY(), 0.0f);

        p.move(Direction.WEST);
        p.update();
        p.pushCoordinatesToSprite();

        assertEquals(0, p.getX(), 0.0f);
        assertEquals(0, p.getY(), 0.0f);
    }
    
    /**
     * @author JAAPAN
     */
    @Test
    public void testCanAccuse() {
    	// The player has no information yet (clues, questions etc), so should not be able to
    	// accuse anyone
    	assertFalse("Fail - Player should not be able to accuse", p.canAccuse());
    }
    
    /**
     * @author JAAPAN
     */
    @Test
    public void testScore() {
    	// Check the player's score starts at 0
    	assertEquals("Fail - Player's score should start at 0", p.getScore(), 0);
    	
    	p.addToScore(500);
    	assertEquals("Fail - Score not changing", p.getScore(), 500);
    	
    	// Should cap at 0
    	p.addToScore(-5000);
    	assertEquals("Fail - Score not lower capping", p.getScore(), 0);
    }
    
    /**
     * @author JAAPAN
     */
    @Test
    public void testPlayTime() {
    	// Add 1 hour, 1 minute, 1.5 seconds to the play time
    	p.addPlayTime(3661.5f);
    	assertEquals("Fail - Play time not flooring", p.getPlayTime(), 3661);
    	assertEquals("Fail - Play time not correctly formatted", p.getFormattedPlayTime(), "01:01:01");
    	
    	// Add 10 minutes, 43.5 seconds
    	p.addPlayTime(643.5f);
    	assertEquals("Fail - Fractional time lost", p.getPlayTime(), 4305);
    	assertEquals("Fail - Play time not correctly formatted", p.getFormattedPlayTime(), "01:11:45");
    	
    	// Add 100 hours - should cap
    	p.addPlayTime(360000f);
    	assertEquals("Fail - Formatted play time not capped", p.getFormattedPlayTime(), "99:59:59");
    }
    
}
