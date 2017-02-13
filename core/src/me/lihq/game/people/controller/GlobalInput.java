package me.lihq.game.people.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.screen.PauseScreen;

/**
 * Universal input handler for non-Player related inputs
 * @author Andrew
 *
 */
public class GlobalInput extends InputAdapter {
	
	/**
	 * Variable for game for input to use
	 */
	private GameMain game;
	
	/**
	 * Variables storing state of inputs
	 */
	private boolean paused, showWalkable, showHideable, debug;
	
	public GlobalInput(GameMain game) {
		this.game = game;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.P)
		{
			paused = true;
		}
		if (keycode == Input.Keys.J) {
			showWalkable = true;
		}
		if (keycode == Input.Keys.H) {
			showHideable = true;
		}
		if (keycode == Input.Keys.F3) {
			debug = true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.P)
		{
			paused = false;
		}
		if (keycode == Input.Keys.J) {
			showWalkable = false;
		}
		if (keycode == Input.Keys.H) {
			showHideable = false;
		}
		if (keycode == Input.Keys.F3) {
			debug = false;
		}
		return false;
	}
	
	/**
	 * Called once a frame to transfer read input to the live game data in the logic Thread.
	 */
	public void update() {
		if (paused) {
			if (game.getScreen().getClass() == game.menuScreen.getClass()) return;
			PauseScreen pauseScreen = new PauseScreen(game);
			if (game.getScreen().getClass() != pauseScreen.getClass()) {
				game.getNavigationScreen().playerController.clear();
				game.setScreen(pauseScreen);
				paused = false;
			} else {
				game.setScreen(game.getNavigationScreen());
				paused = false;
			}
		}
		if (showWalkable) {
			Settings.DEBUG_OPTIONS.put("showWalkable", !Settings.DEBUG_OPTIONS.get("showWalkable"));
			showWalkable = false;
		}
		if (showHideable) {
			Settings.DEBUG_OPTIONS.put("showHideable", !Settings.DEBUG_OPTIONS.get("showHideable"));
			showHideable = false;
		}
		if (debug) {
			Settings.DEBUG = !Settings.DEBUG;
			debug = false;
		}
	}

}
