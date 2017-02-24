package me.lihq.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import me.lihq.game.people.controller.GlobalInput;
import me.lihq.game.screen.*;

/**
 * Created by jason on 24/02/2017.
 */
public class ScreenManager {

    public GameMain game;

    public enum Screens {
        navigation, mainMenu, pauseMenu, inventory, settings
    }

    public Screens currentScreen = Screens.mainMenu;

    /**
     * A screen to be used to display standard gameplay within the game , including the status bar.
     */
    public NavigationScreen navigationScreen;
    /**
     * The main menu screen that shows up when the game is first started.
     */
    public MainMenuScreen menuScreen;
    /**
     * The screen that is displayed when the game is paused.
     */
    public PauseScreen pauseScreen;
    /**
     * The screen that displays the player's inventory.
     */
    public InventoryScreen inventoryScreen;
    /**
     * The screen that allows the player to modify settings.
     */
    public SettingsScreen settingsScreen;

    /**
     * Universal input handler
     */
    public GlobalInput input;
    /**
     * Input multiplexer to control multiple inputs across project
     */
    public InputMultiplexer inputMultiplexer;


    public ScreenManager(GameMain game) {
        this.game = game;

        // Set up the various screens
        menuScreen = new MainMenuScreen(game);
        navigationScreen = new NavigationScreen(game);
        navigationScreen.updateTiledMapRenderer();
        pauseScreen = new PauseScreen(game);
        inventoryScreen = new InventoryScreen(game);
        settingsScreen = new SettingsScreen(game);

        // Load universal input class
        input = new GlobalInput(this);

        // Load input multiplexer and add universal input to it
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(input);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void setScreen(Screens screen) {
        switch (screen) {
            case mainMenu:
                game.setScreen(menuScreen);
                break;
            case pauseMenu:
                game.setScreen(pauseScreen);
                break;
            case navigation:
                game.setScreen(navigationScreen);
                break;
            case inventory:
                game.setScreen(inventoryScreen);
                break;
            case settings:
                game.setScreen(settingsScreen);
                break;
        }
        currentScreen = screen;
    }

    public void update() {
        input.update(); // Update the global input controller
    }

    public void reset() {
        // Clear the input multiplexer, and add the global input controller
        inputMultiplexer.clear();
        inputMultiplexer.addProcessor(input);

        // Recreate the navigation screen, so the references to the player and NPCs are updated
        navigationScreen = new NavigationScreen(game);
        navigationScreen.updateTiledMapRenderer();
    }

    public void dispose() {
        navigationScreen.dispose();
        menuScreen.dispose();
        pauseScreen.dispose();
        inventoryScreen.dispose();
        settingsScreen.dispose();
    }
}
