package me.lihq.game.screen;

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
     * Screen displayed to prompt players to swap over.
     */
    public PlayerSwitchScreen playerSwitchScreen;

    /**
     * Menu screen displayed when the player asks for a multi-player game.
     */
    public NumberOfPlayersSelectionScreen numberOfPlayersSelectionScreen;

    /**
     * Universal input handler
     */
    public GlobalInput input;

    /**
     * This stores the previous menu that was shown. Used by settings to determine what screen to return to
     */
    public Boolean wasInMenu = true;

    /**
     * This is the next screen to be shown, it is shown after 1 render loop
     */
    public AbstractScreen nextScreen = null;

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
        pauseScreen = new PauseScreen(game);
        inventoryScreen = new InventoryScreen(game);
        settingsScreen = new SettingsScreen(game);
        playerSwitchScreen = new PlayerSwitchScreen(game);
        numberOfPlayersSelectionScreen = new NumberOfPlayersSelectionScreen(game);

    }

    /**
     * Changes currently displayed screen
     * @param screen Screen to display
     */
    public void setScreen(Screens screen) {
        switch (screen) {
            case mainMenu:
                nextScreen = menuScreen;
                wasInMenu = true;
                break;
            case pauseMenu:
                nextScreen = pauseScreen;
                wasInMenu = false;
                break;
            case navigation:
                nextScreen = navigationScreen;
                break;
            case inventory:
                nextScreen = inventoryScreen;
                break;
            case settings:
                nextScreen = settingsScreen;
                break;
            case playerSwitch:
                nextScreen = playerSwitchScreen;
                break;
            case numberOfPlayersSelection:
                nextScreen = numberOfPlayersSelectionScreen;
                break;
        }
        currentScreen = screen;
    }

    /**
     * This is called once a render loop to try and update the screen
     */
    public void update()
    {
        if (nextScreen != null)
        {
            game.setScreen(nextScreen);
            nextScreen = null;
        }
    }

    /**
     * Resets the screens for restarting the game
     */
    public void reset() {
        // Recreate the navigation screen, so the references to the player and NPCs are updated
        navigationScreen = new NavigationScreen(game);
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
        numberOfPlayersSelectionScreen.dispose();
        playerSwitchScreen.dispose();
    }
}
