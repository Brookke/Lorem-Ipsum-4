import me.lihq.game.Assets;
import me.lihq.game.models.Clue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for the Clue class.
 */
public class ClueUnitTest extends GameTester {
    public Clue shoe, book, knife;
    
    @Before
    public void createClues() {
        Assets.load();
        shoe = new Clue("Shoe", "I am a shoe", false, 0, 0);
        book = new Clue("book", "I am a book", false, 0, 0);
        // Set the knife to a murder weapon
        knife = new Clue("knife", "I am a knife", true, 0, 0);
    }

    @Test
    public void testName() {
        assertEquals("Name not what was expected", "Shoe", shoe.getName());
        assertEquals("Name not what was expected", "book", book.getName());
        assertEquals("Name not what was expected", "knife", knife.getName());
    }

    @Test
    public void testDescription() {
        assertEquals("Description not what was expected", "I am a shoe", shoe.getDescription());
        assertEquals("Description not what was expected", "I am a book", book.getDescription());
        assertEquals("Description not what was expected", "I am a knife", knife.getDescription());
    }

    @Test
    public void testTileCoordinates() {
        shoe.setTileCoordinates(10, 20);
        assertEquals("tile coordinate x not set correctly", 10, shoe.getTileX());
        assertEquals("tile coordinate y not set correctly", 20, shoe.getTileY());
    }

    @Test
    public void testEquality() {
        Clue shoe2 = shoe;
        assertEquals("Equality test failing", shoe, shoe2);
        assertNotEquals("Equality test always true", shoe, book);
    }
    
    /**
     * @author JAAPAN
     */
    @Test
    public void testMurderWeapon() {
    	assertFalse("Fail - Shoe shouldn't be a murder weapon", shoe.isMurderWeapon());
    	
    	assertTrue("Fail - Knife should be a murder weapon", knife.isMurderWeapon());
    }
    
    /**
     * @author JAAPAN
     */
    @Test
    public void testRedHerring() {
    	book.setRedHerring();
    	
    	assertFalse("Fail - Shoe shouldn't be a red herring", shoe.isRedHerring());
    	
    	assertTrue("Fail - Book should be a red herring", book.isRedHerring());
    }

}
