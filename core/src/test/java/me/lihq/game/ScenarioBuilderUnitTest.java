package me.lihq.game;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * JUnit tests for the ScenarioBuilder class
 */
public class ScenarioBuilderUnitTest extends GameTester {
    ScenarioBuilder builder;

    @Before
    public void before() {
        GameMain game = new GameMain();
        builder = new ScenarioBuilder(game);
    }

    @Test
    public void testGenerateGame() {
        try {
            List<GameSnapshot> snapshots = builder.generateGame(2);
            assertTrue("Scenario Builder has created the game", true);
        } catch (Exception e) {
            assertTrue("Scenario Builder failed to generate game", false);
        }
    }
}
