package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * @author Lorem-Ipsum
 */
public class Puzzle {

    Stage stage;

    int switchCount = 0;

    boolean solved = false;


    public Puzzle() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    /**
     * This handles what happens when each switch is pressed
     * @param resetSwitch
     */
    public void handleSwitch(boolean resetSwitch) {
        if (resetSwitch) {
            resetPuzzle();
        } else {
            switchCount++;
            if (switchCount >= 3) {
                solved = true;
            }

        }
    }


    public boolean hasBeenSolved() {
        return solved;
    }




    /**
     * This simply resets the puzzle
     */
    public void resetPuzzle() {
        switchCount = 0;
    }

}


/*

O 1 O
1 O 1
O O O


B B B
B B B
B B B

B P B
B P B
B B B
 */
