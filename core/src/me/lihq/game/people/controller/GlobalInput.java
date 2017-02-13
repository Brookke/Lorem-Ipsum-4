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
	private boolean paused, inventory, showWalkable, showHideable, debug;
	
	public GlobalInput(GameMain game) {
		this.game = game;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.P) {
			paused = true;
			return true;
		}
		if (keycode == Input.Keys.I) {
			inventory = true;
			return true;
		}
		if (keycode == Input.Keys.J) {
			showWalkable = true;
			return true;
		}
		if (keycode == Input.Keys.H) {
			showHideable = true;
			return true;
		}
		if (keycode == Input.Keys.F3) {
			debug = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.P) {
			paused = false;
			return true;
		}
		if (keycode == Input.Keys.I) {
			inventory = false;
			return true;
		}
		if (keycode == Input.Keys.J) {
			showWalkable = false;
			return true;
		}
		if (keycode == Input.Keys.H) {
			showHideable = false;
			return true;
		}
		if (keycode == Input.Keys.F3) {
			debug = false;
			return true;
		}
		return false;
	}
	
	/**
	 * Called once a frame to transfer read input to the live game data in the logic Thread.
	 */
	public void update() {
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
		
		if (game.getScreen().getClass() == game.menuScreen.getClass()) return;
		
		if (paused && game.getScreen().getClass() != game.inventoryScreen.getClass()) {
			if (game.getScreen().getClass() != game.pauseScreen.getClass()) {
				game.getNavigationScreen().playerController.clear();
				game.setScreen(game.pauseScreen);
				paused = false;
			} else {
				game.setScreen(game.getNavigationScreen());
				paused = false;
			}
		}
		if (inventory && game.getScreen().getClass() != game.pauseScreen.getClass()) {
			if (game.getScreen().getClass() != game.inventoryScreen.getClass()) {
				game.getNavigationScreen().playerController.clear();
				game.setScreen(game.inventoryScreen);
				inventory = false;
			} else {
				game.setScreen(game.getNavigationScreen());
				inventory = false;
			}
		}
	}

}
