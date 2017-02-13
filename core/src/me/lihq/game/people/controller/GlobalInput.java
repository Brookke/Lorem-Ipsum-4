package me.lihq.game.people.controller;

import java.awt.Menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import me.lihq.game.GameMain;
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
	private boolean paused;
	
	public GlobalInput(GameMain game) {
		this.game = game;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.P)
		{
			paused = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.P)
		{
			paused = false;
			return true;
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
	}

}
