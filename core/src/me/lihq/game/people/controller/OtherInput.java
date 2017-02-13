package me.lihq.game.people.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Universal input handler for non-Player related inputs
 * @author Andrew
 *
 */
public class OtherInput extends InputAdapter {
	
	/**
	 * Private array storing the boolean state of keys. The index of the array is the key value from libgdx. True means key is pressed, False means key is not pressed
	 */
	private boolean[] keys = new boolean[256];
	
	/**
	 * Public variable(s) representing if the key(s) corresponding to certain actions are pressed
	 * E.g. pause is made true by either ESC or P being pressed
	 */
	public boolean pause;
	
	/**
	 * Updates the state of the keys in keys array
	 */
	public void update() {
		pause = keys[Input.Keys.ESCAPE] || keys[Input.Keys.P];
	}
	
	@Override
	public boolean keyDown(int keycode) {
		keys[keycode] = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		keys[keycode] = false;
		return false;
	}

}
