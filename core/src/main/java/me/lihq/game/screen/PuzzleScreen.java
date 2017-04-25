package me.lihq.game.screen;

import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.Puzzle;

/**
 * @author Lorem-Ipsum
 */
public class PuzzleScreen extends AbstractScreen {

    Puzzle puzzle;

    /**
     * This constructor sets the relevant properties of the class.
     *
     * @param game this provides access to the gameMain class so that screens can set the states of the game.
     */
    public PuzzleScreen(GameMain game) {
        super(game);
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {

        puzzle = game.currentSnapshot.puzzle;

        puzzle.init();

        if (puzzle.isSolved()) {
            puzzle.goToSecretRoom();
        }

        game.inputMultiplexer.addProcessor(puzzle.stage);
    }

    /**
     * Game related logic should take place here.
     */
    @Override
    public void update() {


    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last draw
     */
    @Override
    public void render(float delta) {

        puzzle.render();
    }

    /**
     * Called when the window is resized.
     *
     * @param width  The new window width
     * @param height The new window height
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * Called when focus on the window is lost.
     */
    @Override
    public void pause() {

    }

    /**
     * Called when the window regains focus.
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a Game.
     */
    @Override
    public void hide() {
        game.inputMultiplexer.removeProcessor(puzzle.stage);
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
