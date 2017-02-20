package me.lihq.game.screen;

import com.badlogic.gdx.Screen;
import me.lihq.game.GameMain;


/**
 * This is defines all the common methods and properties for each screen. It implements the screen interface from com.badlogic.gdx.Screen.
 * A screen is a like the current view of the game. E.g. Main Menu or Pause screen or dialogue screen.
 */
public abstract class AbstractScreen implements Screen {
    /**
     * The game that the Screen is live in
     */
    protected GameMain game;

    /**
     * This constructor sets the relevant properties of the class.
     *
     * @param game this provides access to the gameMain class so that screens can set the states of the game.
     */
    public AbstractScreen(GameMain game) {
        this.game = game;
    }

	/**
	 * Called when this screen becomes the current screen for a Game.
	 */
    @Override
    public abstract void show();

	/**
	 * Game related logic should take place here.
	 */
    public abstract void update();

	/**
	 * Called when the screen should render itself.
	 * 
	 * @param delta The time in seconds since the last draw
	 */
    @Override
    public abstract void render(float delta);

	/**
	 * Called when the window is resized.
	 * 
	 * @param width The new window width
	 * @param height The new window height
	 */
    @Override
    public abstract void resize(int width, int height);

	/**
	 * Called when focus on the window is lost.
	 */
    @Override
    public abstract void pause();

	/**
	 * Called when the window regains focus.
	 */
    @Override
    public abstract void resume();

	/**
	 * Called when this screen is no longer the current screen for a Game.
	 */
    @Override
    public abstract void hide();

	/**
	 * Called when this screen should release all resources.
	 */
    @Override
    public abstract void dispose();

}
