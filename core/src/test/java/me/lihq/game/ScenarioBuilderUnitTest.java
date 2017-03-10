package me.lihq.game;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by joeshuff on 10/03/2017.
 */
public class ScenarioBuilderUnitTest extends GameTester
{
    ScenarioBuilder builder;

    @Before
    public void before()
    {
        GameMain game = new GameMain();
        builder = new ScenarioBuilder(game);
    }

    @Test
    public void testGenerateGame()
    {
        try
        {
            List<GameSnapshot> snapshots = builder.generateGame(2);
            assertTrue("Scenario Builder failed to generate game", true);
        }
        catch (Exception e){
            assertTrue("Scenario Builder failed to generate game", false);
        }
    }
}
