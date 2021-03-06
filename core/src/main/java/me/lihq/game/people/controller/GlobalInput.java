package me.lihq.game.people.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.screen.ScreenManager;
import me.lihq.game.screen.Screens;

/**
 * Universal input handler for non-player related input - e.g. opening the pause menu.
 *
 * @author JAAPAN
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

    /**
     * This method is called when a key press down event is heard
     *
     * @param keycode (int) the keycode of the key pressed
     * @return (Boolean) Whether the event was handled or not.
     */
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

    /**
     * This method is called when a key press up event is heard
     *
     * @param keycode (int) the keycode of the key released
     * @return (Boolean) Whether the event was handled or not.
     */
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

        ScreenManager screenManager = game.screenManager;

        if (screenManager.currentScreen == Screens.mainMenu) return;

        if (paused) {
            if (screenManager.currentScreen == Screens.navigation) {
                screenManager.setScreen(Screens.pauseMenu);
                paused = false;
            } else if (screenManager.currentScreen == Screens.pauseMenu) {
                screenManager.setScreen(Screens.navigation);
                paused = false;
            }
        }
        if (inventory) {
            if (screenManager.currentScreen == Screens.navigation) {
                screenManager.setScreen(Screens.inventory);
                inventory = false;
            } else if (screenManager.currentScreen == Screens.inventory) {
                screenManager.setScreen(Screens.navigation);
                inventory = false;
            }
        }
    }

}
