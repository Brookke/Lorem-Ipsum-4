package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Lorem-Ipsum
 */
public class Puzzle {

    private ArrayList<Button> buttons;
    private Table table;
    public Stage stage;

    /**
     * The number of switches pressed
     */
    int switchesPressed = 0;

    /**
     * The number of reset buttons in the puzzle, these are buttons that if pressed cause the game to be reset
     */
    int resetButtons = 6;

    /**
     * The total number of buttons in the game
     */
    int totalButtons = 9;

    boolean solved = false;


    public Puzzle() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        table = new Table();
        table.setDebug(true);
        table.setFillParent(true);


        buttons = new ArrayList<>();


        //Creates all the reset buttons
        for (int i = 0; i < resetButtons; i++) {
            Button b = UIHelpers.createButton();
            b.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    handleSwitch(true);

                }
            });
            buttons.add(b);
        }

        //Creates all of the other buttons
        for (int i = 0; i < totalButtons - resetButtons ; i++) {
            Button b = UIHelpers.createButton();
            b.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    //TODO: somehow disable the button
                    handleSwitch(false);
                }
            });
            buttons.add(b);
        }


        Collections.shuffle(buttons);


        //This distributes all of the buttons in a square
        for (int i = 0; i < buttons.size(); i++) {

            table.add(buttons.get(i)).width(100).height(100);
            if ((i + 1) % Math.sqrt(totalButtons) == 0 && i != 0) {
                table.row();
            }
        }

        stage.addActor(table);

    }

    /**
     * This handles what happens when each switch is pressed
     * @param resetSwitch
     */
    public void handleSwitch(boolean resetSwitch) {
        System.out.println(switchesPressed);
        if (resetSwitch) {
            resetPuzzle();
        } else {
            switchesPressed++;
            if (switchesPressed >= 3) {
                solved = true;
            }

        }
    }


    public boolean hasBeenSolved() {
        return solved;
    }


    /**
     * This method is called to render the main menu to the stage
     */
    public void render() {
        //Determining the background colour of the menu
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Rendering the buttons
        stage.act();
        stage.draw();
    }

    /**
     * This simply resets the puzzle
     */
    public void resetPuzzle() {
        switchesPressed = 0;

        for (Button b : buttons) {
            b.setDisabled(false);
            b.setTouchable(Touchable.enabled);
        }


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
