package me.lihq.game;

import me.lihq.game.screen.elements.SpeechBox;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputMultiplexer;

/**
 * This is to control the order that SpeechBoxes are shown in and controls flow of displaying the SpeechBoxes
 */
public class SpeechboxManager
{
	public GameMain game;
	
    public InputMultiplexer multiplexer;

    /**
     * This is the stack of SpeechBox's that are to be displayed
     */
    private List<SpeechBox> stack = new ArrayList<>();

    /**
     * Constructor to create a SpeechboxManager and initialise the input multiplexer
     */
    public SpeechboxManager(GameMain game)
    {
    	this.game = game;
    	multiplexer = new InputMultiplexer();
    }

    /**
     * This method is called once a render loop to render the menu
     */
    public void render()
    {
        if (!this.stack.isEmpty()) {
            this.stack.get(0).render();
        }
    }

    /**
     * This method is called once a tick
     */
    public void update()
    {
        if (!this.stack.isEmpty()) {
            if (this.stack.get(0).timeoutDuration == 0) {
            	multiplexer.removeProcessor(this.stack.get(0).stage);
                this.stack.remove(0);
            } else {
                this.stack.get(0).update();

            }
        }
        updateInputProcessor();
    }

    /**
     * This is called when the window is resized
     *
     * @param width - The new width
     * @param height - The new height
     */
    public void resize(int width, int height)
    {
        if (!this.stack.isEmpty()) {
            this.stack.get(0).resize(width, height);
        }
    }

    /**
     * This updates the input multiplexer based on whether there are any SpeechBoxes in the stack
     */
    private void updateInputProcessor()
    {
        if (multiplexer.getProcessors().size == 0 && !this.stack.isEmpty()) {
        	multiplexer.addProcessor(this.stack.get(0).stage);
        }
    }

    /**
     * This method adds a SpeechBox to the stack
     * @param speechBox - The SpeechBox to add to the stack
     */
    public void addSpeechBox(SpeechBox speechBox)
    {
        this.stack.add(speechBox);
    }

    /**
     * This method removes the current speechBox from the stack and progresses onto the next one if
     * there is one
     */
    public void removeCurrentSpeechBox()
    {
        if (!this.stack.isEmpty()) {
            this.stack.get(0).timeoutDuration = 0;
        }
    }

    public void skipMessage()
    {
        // ensure the message has a positive timeout, otherwise it contains buttons and shouldn't be skippable
        if (!this.stack.isEmpty() && (this.stack.get(0).timeoutDuration > 0)) {
            this.removeCurrentSpeechBox();
        }
    }
    
    /**
     * 
     * @return true if the stack of speechboxes is empty, false otherwise
     */
    public boolean isEmpty()
    {
    	return this.stack.isEmpty();
    }

}
