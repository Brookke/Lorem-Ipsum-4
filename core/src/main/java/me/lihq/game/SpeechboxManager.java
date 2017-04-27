package me.lihq.game;

import com.badlogic.gdx.InputMultiplexer;
import me.lihq.game.screen.elements.SpeechBox;

import java.util.ArrayList;
import java.util.List;

/**
 * This is to control the order that SpeechBoxes are shown in and controls flow of displaying the SpeechBoxes
 */
public class SpeechboxManager {
    /**
     * Reference to the main game class.
     */
    public GameMain game;

    /**
     * Allows speechboxes to handle input, without blocking other input controllers.
     */
    public InputMultiplexer multiplexer;

    /**
     * This is the stack of SpeechBox's that are to be displayed.
     */
    private List<SpeechBox> stack = new ArrayList<>();

    /**
     * Constructor to create a SpeechboxManager and initialise the input multiplexer.
     */
    public SpeechboxManager(GameMain game) {
        this.game = game;
        multiplexer = new InputMultiplexer();
    }

    /**
     * This method is called once a render loop to render the menu
     */
    public void render() {
        if (!stack.isEmpty()) {
            stack.get(0).render();
        }
    }

    /**
     * This method is called once a tick
     */
    public void update() {
        if (!stack.isEmpty()) {
            if (stack.get(0).timeoutDuration == 0) {
                multiplexer.removeProcessor(stack.get(0).stage);

                if (stack.get(0).isTrigger()) {
                    game.currentSnapshot.finishedInteraction();
                }

                stack.remove(0);

                if (stack.isEmpty()) {
                    game.currentSnapshot.player.canMove = true;
                }
            } else {
                stack.get(0).update();
            }
        }
        updateInputProcessor();
    }

    /**
     * This is called when the window is resized
     *
     * @param width  The new width
     * @param height The new height
     */
    public void resize(int width, int height) {
        if (!stack.isEmpty()) {
            stack.get(0).resize(width, height);
        }
    }

    /**
     * This updates the input multiplexer based on whether there are any SpeechBoxes in the stack
     */
    private void updateInputProcessor() {
        if (multiplexer.getProcessors().size == 0 && !stack.isEmpty()) {
            multiplexer.addProcessor(stack.get(0).stage);
        }
    }

    /**
     * This method adds a SpeechBox to the stack
     *
     * @param speechBox The SpeechBox to add to the stack
     */
    public void addSpeechBox(SpeechBox speechBox) {
        stack.add(speechBox);
    }

    /**
     * This method adds a SpeechBox to the stack and sets the interactOnClose variable
     *
     * @param speechBox       the SpeechBox to add to the stack
     * @param interactOnClose whether to interact when this is closed or not
     */
    public void addSpeechBox(SpeechBox speechBox, Boolean interactOnClose) {
        speechBox.setTrigger(interactOnClose);
        stack.add(speechBox);
    }

    /**
     * This method removes the current speechBox from the stack and progresses onto the next one if
     * there is one
     */
    public void removeCurrentSpeechBox() {
        if (!stack.isEmpty()) {
            stack.get(0).timeoutDuration = 0;
        }
    }


    /**
     * @return True if the stack of speechboxes is empty, false otherwise
     * @author JAAPAN
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
