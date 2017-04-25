package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.GameMain;
import me.lihq.game.models.Room;
import me.lihq.game.screen.Screens;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Lorem-Ipsum
 */
public class Puzzle {

    private final GameMain game;

    /**
     * The transition data for the secret room
     */
    public Room.Transition secretRoomTrans;

    /**
     * The switches for the game 
     */
    private ArrayList<Button> switches;
    private Table table;
    private Table unlockTable;
    public Stage stage;


    /**
     * The number of switches pressed
     */
    int switchesPressed = 0;

    /**
     * Whether the puzzle has been solved or not
     */
    private boolean solved = false;

    /**
     * The number of reset switches in the puzzle, these are switches that if pressed cause the game to be reset
     */
    int resetSwitches = 6;

    /**
     * The total number of switches in the game
     */
    int totalSwitches = 9;

    public Puzzle(GameMain game) {
        this.game = game;

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Image background = new Image(new Texture(Gdx.files.internal("puzzle-background.png")));
        stage.addActor(background);
        table = new Table();
        unlockTable = new Table();
        unlockTable.setFillParent(true);
        unlockTable.pad(143,0,0,0);
        unlockTable.setVisible(false);
        table.setFillParent(true);
        table.pad(143,0,0,0);

        switches = new ArrayList<>();


        //Creates all the reset switches
        for (int i = 0; i < resetSwitches; i++) {
            final Button b = UIHelpers.createButton();
            b.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    handleSwitch(true);

                }
            });
            switches.add(b);
        }

        //Creates all of the other switches
        for (int i = 0; i < totalSwitches - resetSwitches; i++) {
            final Button b = UIHelpers.createButton();
            b.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    b.setDisabled(true);
                    b.moveBy(0,-20);
                    b.setTouchable(Touchable.disabled);
                    handleSwitch(false);
                }
            });
            switches.add(b);
        }

        Collections.shuffle(switches);

        for (int i = 0; i < switches.size(); i++) {

            table.add(switches.get(i)).width(65).height(185);
        }

        Button unlock = UIHelpers.createTextButton("Unlock");
        unlock.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                markSolved();
                goToSecretRoom();
                }
        });
        unlockTable.add(unlock);


        stage.addActor(table);
        stage.addActor(unlockTable);
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
            if (switchesPressed >= totalSwitches - resetSwitches) {
                table.setVisible(false);
                unlockTable.setVisible(true);
            }

        }
    }


    /**
     * Sets the puzzle as solved
     */
    private void markSolved() {
        this.solved = true;
    }

    /**
     * This method checks if the puzzle has been solved
     * @return true if it has been solved
     */
    public boolean isSolved() {
        return this.solved;
    }

    /**
     * This does all the changes necessary to change to the secret room.
     */
    public void goToSecretRoom() {
        game.screenManager.navigationScreen.initialiseRoomChange(secretRoomTrans);
        game.screenManager.setScreen(Screens.navigation);
    }

    public void init() {
        secretRoomTrans = game.currentSnapshot.gameMap.getSecretRoomTransition();
    }

    /**
     * This method is called to render the main menu to the stage
     */
    public void render() {
        //Determining the background colour of the menu
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Rendering the switches
        stage.act();
        stage.draw();
    }

    /**
     * This simply resets the puzzle
     */
    public void resetPuzzle() {
        switchesPressed = 0;
        table.setVisible(true);
        unlockTable.setVisible(false);
        for (Button b : switches) {
            b.setDisabled(false);
            b.setTouchable(Touchable.enabled);
            b.reset();
        }


    }

}
