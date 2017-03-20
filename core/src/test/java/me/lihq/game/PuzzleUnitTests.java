package me.lihq.game;
import me.lihq.game.models.Map;
import org.junit.Before;
import org.junit.Test;
import me.lihq.game.people.Player;
import me.lihq.game.screen.elements.Puzzle;
import static org.junit.Assert.*;
import me.lihq.game.models.Room;
import me.lihq.game.GameMain;

/**
 * Unit tests for everything puzzle related.
 */
public class PuzzleUnitTests extends GameTester {
    GameMain game = new GameMain();
    Map map = new Map(game);
    Puzzle puzzle = new Puzzle(game);
    Player player = new Player(game, "Test Name", "player.png", 0, 0);

    @Test
    public void TestGoToSecretRoom() {
        Integer roomBefore = player.getRoom().getID();
        System.out.println(roomBefore);
        puzzle.goToSecretRoom();
        Integer roomAfter = player.getRoom().getID();
        System.out.println(roomAfter);
        assertNotEquals(roomBefore,roomAfter);
    }

    @Test
    public void TestHandleSwitchFalse(){
        Integer switchesPressedBefore = puzzle.getSwitchesPressed();
        puzzle.handleSwitch(false);
        Integer switchesPressedAfter = puzzle.getSwitchesPressed();
        assertEquals(java.util.Optional.ofNullable(switchesPressedAfter),switchesPressedBefore + 1);
    }
}
