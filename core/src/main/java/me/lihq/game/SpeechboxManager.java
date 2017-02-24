package me.lihq.game;

import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.SpeechBox;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputMultiplexer;

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
    public SpeechboxManager(GameMain game)
    {
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
                stack.remove(0);
            } else {
                stack.get(0).update();
            }
        }
        updateInputProcessor();
    }

    /**
     * This is called when the window is resized
     *
     * @param width The new width
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
     * @param speechBox The SpeechBox to add to the stack
     */
    public void addSpeechBox(SpeechBox speechBox) {
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
     * Removes the current speechbox, if it contains only text. If it contains buttons (i.e.
     * is waiting for user input), it is not removed. Allows the player to skip dialogue.
     * 
     * @author JAAPAN
     */
    public void skipMessage() {
        // ensure the message has a positive timeout, otherwise it contains buttons and shouldn't be skippable
        if (!stack.isEmpty() && (stack.get(0).timeoutDuration > 0)) {
            removeCurrentSpeechBox();
        }
    }
    
    /**
     * @return True if the stack of speechboxes is empty, false otherwise
     * 
     * @author JAAPAN
     */
    public boolean isEmpty() {
    	return stack.isEmpty();
    }

}
