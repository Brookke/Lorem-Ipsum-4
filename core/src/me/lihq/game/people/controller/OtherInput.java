package me.lihq.game.people.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Universal input handler for non-Player related inputs
 * @author Andrew
 *
 */
public class OtherInput implements InputProcessor {
	
	/**
	 * Private variable representing the mouse's X coordinate on the screen
	 */
	private static int mouseX = -1;
	
	/** 
	 * Private variable representing the mouse's Y coordinate on the screen
	 */
	private static int mouseY = -1;
	
	/**
	 * Private variable representing the mouse button value being pressed
	 */
	private static int mouseB = -1;
	
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
	 * @return The x coordinate of the mouse
	 */
	public static int getX() {
		return mouseX;
	}
	
	/**
	 * @return The y coordinate of the mouse
	 */
	public static int getY() {
		return mouseY;
	}
	
	/**
	 * @return Which mouse button is pressed:
	 * <ul>
	 *     <li>-1: No button pressed</li>
	 *     <li>0: Left mouse button is pressed</li>
	 *     <li>1: Right mouse button is pressed</li>
	 *     <li>2: Middle mouse button is pressed</li>
	 * </ul>
	 */
	public static int getButton() {
		return mouseB;
	}
	
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

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		mouseB = button;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		mouseB = -1;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mouseX = screenX;
		mouseY = screenY;
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX;
		mouseY = screenY;
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
