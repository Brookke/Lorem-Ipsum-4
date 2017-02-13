package me.lihq.game.people.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Universal input handler for non-Player related inputs
 * @author Andrew
 *
 */
public class GlobalInput extends InputAdapter {
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	
	/**
	 * Called once a frame to transfer read input to the live game data in the logic Thread.
	 */
	public void update() {
	}

}
