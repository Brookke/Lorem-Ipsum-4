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


    /**
     * The player that opened the door
     */
    private String playerWhoUnlocked;

    private final GameMain game;

    /**
     * The transition data for the secret room
     */
    public final Room.Transition secretRoomTrans;

    /**
     * The switches for the game 
     */
    private ArrayList<Button> switches;
    private Table table;
    public Stage stage;


    /**
     * The number of switches pressed
     */
    int switchesPressed = 0;

    /**
     * The number of reset switches in the puzzle, these are switches that if pressed cause the game to be reset
     */
    int resetSwitches = 6;

    /**
     * The total number of switches in the game
     */
    int totalSwitches = 9;


    /**
     * Whether the puzzle is solved
     */
    boolean solved = false;


    public Puzzle(GameMain game) {
        this.game = game;

        secretRoomTrans = game.gameMap.getSecretRoomTransition();


        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Image background = new Image(new Texture(Gdx.files.internal("puzzle-background.png")));
        stage.addActor(background);
        table = new Table();
        //table.setDebug(true);
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


        //This distributes all of the switches in a square
        for (int i = 0; i < switches.size(); i++) {

            table.add(switches.get(i)).width(65).height(185);
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
            if (switchesPressed >= totalSwitches - resetSwitches) {
                solved = true;
                table.clear();
                Button unlock = UIHelpers.createTextButton("Unlock");
                unlock.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        markSolved();
                        goToSecretRoom();
                    }
                });
                table.add(unlock);
            }

        }
    }


    /**
     * Sets the puzzle as solved and records who solved the puzzle
     */
    private void markSolved() {
        solved = true;
        //TODO: change to game snapshot
        playerWhoUnlocked = game.player.getName();
    }

    /**
     * This returns the player that completed the puzzle
     * @return
     */
    public String getPlayerWhoUnlocked() {
        return playerWhoUnlocked;
    }
    /**
     * This does all the changes necessary to change to the secret room.
     */
    public void goToSecretRoom() {
        game.screenManager.navigationScreen.initialiseRoomChange(secretRoomTrans);
        game.screenManager.setScreen(Screens.navigation);
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
        //Rendering the switches
        stage.act();
        stage.draw();
    }

    /**
     * This simply resets the puzzle
     */
    public void resetPuzzle() {
        switchesPressed = 0;

        for (Button b : switches) {
            b.setDisabled(false);
            b.setTouchable(Touchable.enabled);
            b.reset();
        }


    }

    public Integer getSwitchesPressed(){
        return switchesPressed;
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
