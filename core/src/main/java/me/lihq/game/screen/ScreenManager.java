package me.lihq.game.screen;

import com.badlogic.gdx.InputMultiplexer;
import me.lihq.game.GameMain;
import me.lihq.game.people.controller.GlobalInput;

/**
 * ScreenManager handles the GUI screens within the game
 */
public class ScreenManager {


    /**
     * Reference to game
     */
    public GameMain game;

    /**
     * Defines screen currently shown
     */
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

    /**
     * This stores the previous menu that was shown. Used by settings to determine what screen to return to
     */
    public Boolean wasInMenu = true;

    /**
     * Constructor for ScreenManager
     * Initialises screens ready to render
     * @param game reference to GameMain
     */
    public ScreenManager(GameMain game) {
        this.game = game;

        // Set up the various screens
        menuScreen = new MainMenuScreen(game);
        navigationScreen = new NavigationScreen(game);
        navigationScreen.updateTiledMapRenderer();
        pauseScreen = new PauseScreen(game);
        inventoryScreen = new InventoryScreen(game);
        settingsScreen = new SettingsScreen(game);

    }

    /**
     * Changes currently displayed screen
     * @param screen Screen to display
     */
    public void setScreen(Screens screen) {
        switch (screen) {
            case mainMenu:
                game.setScreen(menuScreen);
                wasInMenu = true;
                break;
            case pauseMenu:
                game.setScreen(pauseScreen);
                wasInMenu = false;
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

    /**
     * Resets the screens for restarting the game
     */
    public void reset() {
        // Recreate the navigation screen, so the references to the player and NPCs are updated
        navigationScreen = new NavigationScreen(game);
        navigationScreen.updateTiledMapRenderer();
    }

    /**
     * Disposes of associated resources
     */
    public void dispose() {
        navigationScreen.dispose();
        menuScreen.dispose();
        pauseScreen.dispose();
        inventoryScreen.dispose();
        settingsScreen.dispose();
    }
}
