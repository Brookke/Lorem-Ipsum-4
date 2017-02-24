package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.GameMain;
import me.lihq.game.ScreenManager;
import me.lihq.game.screen.elements.UIHelpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The screen that is displayed when the player wins the game. Displays their score and time,
 * and allows them to return to the main menu.
 *
 * @author JAAPAN
 */
public class WinScreen extends AbstractScreen {

    /**
     * Vertical offset for label elements.
     */
    private static final float OFFSET = 50f;

    /**
     * The x-coordinate of the information labels.
     */
    private static final float LEFT_ALIGN = Gdx.graphics.getWidth() / 16;

    /**
     * The number of information labels on the screen. Used for animating their appearance.
     */
    private static final int INFO_LABELS = 14;

    /**
     * The amount of time to wait before showing the next label.
     */
    private static final float ANIMATION_TIME = 0.5f;

    /**
     * The stage used for rendering the UI and handling input.
     */
    private Stage stage;

    /**
     * The number of information labels that have completed their animation.
     */
    private int animationCount = 0;

    /**
     * Timer used to animate the information labels.
     */
    private float animationTimer = 0f;

    /**
     * Whether the player's score is entered into the highscores table.
     */
    private boolean setHighScore = false;

    /**
     * Constructs the Win screen
     *
     * @param game Reference to the main Game class, used so this screen can alter the current
     *             screen of the game, and access the player and input multiplexer
     */
    public WinScreen(GameMain game) {
        super(game);

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        initMenu();
    }

    /**
     * Initialises the UI elements on the screen, and sets up event handlers.
     */
    private void initMenu() {
        Label title = UIHelpers.createLabel("You Found the Killer!", true);
        stage.addActor(title);

        // Create all information labels, retrieving the necessary data from the player
        // Set their visibility to false, so they can be animated from the render() method
        Label cluesLabel = UIHelpers.createLabel("Clues Found: " + game.player.collectedClues.size(), false);
        cluesLabel.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 + OFFSET * 4);
        cluesLabel.setVisible(false);

        Label redHerringLabel = UIHelpers.createLabel("Red Herrings Found: " + game.player.getRedHerrings(), false);
        redHerringLabel.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 + OFFSET * 3);
        redHerringLabel.setVisible(false);

        Label questionsAsked = UIHelpers.createLabel("Questions Asked: " + game.player.getQuestions(), false);
        questionsAsked.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 + OFFSET * 2);
        questionsAsked.setVisible(false);

        Label accusedNPCs = UIHelpers.createLabel("Number of People Falsely Accused: " + game.player.getFalseAccusations(), false);
        accusedNPCs.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 + OFFSET * 1);
        accusedNPCs.setVisible(false);

        Label basicScoreLabel = UIHelpers.createLabel("Points Gained: " + game.player.getScore(), false);
        basicScoreLabel.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2);
        basicScoreLabel.setVisible(false);

        Label timeTaken = UIHelpers.createLabel("Time Taken: " + game.player.getFormattedPlayTime(), false);
        timeTaken.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 - OFFSET * 1);
        timeTaken.setVisible(false);

        Label bonusScoreLabel = UIHelpers.createLabel("Time Bonus: " + game.player.getTimeBonus(), false);
        bonusScoreLabel.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 - OFFSET * 2);
        bonusScoreLabel.setVisible(false);

        Label finalScoreLabel = UIHelpers.createLabel("Total Score: " + game.player.getTotalScore(), false);
        finalScoreLabel.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 - OFFSET * 3);
        finalScoreLabel.setVisible(false);

        // Add all labels to the stage, to handle rendering
        stage.addActor(cluesLabel);
        stage.addActor(redHerringLabel);
        stage.addActor(questionsAsked);
        stage.addActor(accusedNPCs);
        stage.addActor(basicScoreLabel);
        stage.addActor(timeTaken);
        stage.addActor(bonusScoreLabel);
        stage.addActor(finalScoreLabel);

        // Generate the leaderboard
        int[] highscores;
        List<Label> highscoreLabels = new ArrayList<>();
        Label highscoresTitleLabel = UIHelpers.createLabel("Highscores", false);
        highscoresTitleLabel.setPosition(Gdx.graphics.getWidth() * 0.75f - highscoresTitleLabel.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 + OFFSET * 3);
        highscoresTitleLabel.setVisible(false);

        try {
            highscores = getHighScores();
            boolean highlighted = false;
            for (int i = 0; i < 5; i++) {
                if (setHighScore && game.player.getTotalScore() == highscores[i] && !highlighted) {
                    highscoreLabels.add(UIHelpers.createLabel("*" + highscores[i] + "*", false));
                    // Only highlight first instance of a highscore (to prevent highlighting duplicates)
                    highlighted = true;
                } else {
                    highscoreLabels.add(UIHelpers.createLabel(String.valueOf(highscores[i]), false));
                }

                highscoreLabels.get(i).setPosition(Gdx.graphics.getWidth() * 0.75f - highscoreLabels.get(i).getWidth() / 2,
                        Gdx.graphics.getHeight() / 2 + OFFSET * 2 - (i * OFFSET));
                highscoreLabels.get(i).setVisible(false);
            }
        } catch (Throwable e) {
            highscoreLabels.add(UIHelpers.createLabel("Error loading high scores.", false));
        }

        // Add all highscores labels to the stage
        stage.addActor(highscoresTitleLabel);
        for (int i = 0; i < highscoreLabels.size(); i++) {
            stage.addActor(highscoreLabels.get(i));
        }

        // Create the button to return to the main menu and reset the game state
        TextButton mainMenuButton = UIHelpers.createTextButton("Main Menu");
        mainMenuButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);

        stage.addActor(mainMenuButton);

        mainMenuButton.addListener(new ClickListener() {
            /**
             * Resets the state of the game, so it can be played again, and opens the main menu.
             * Also calls dispose(), to free resources.
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.resetAll();
                game.screenManager.setScreen(ScreenManager.Screens.mainMenu);
                dispose();
            }
        });
    }

    /**
     * Updates the leaderboards with the players current score (if necessary)
     *
     * @return Integer array containing the 5 highscores
     */
    private int[] getHighScores() throws IOException {
        List<String> scoresList;
        String filePath = "MITRCH-Leaderboards.txt";

        // Get the contents of the leaderboards file, or create it and populate it with 0s
        try {
            scoresList = Files.readAllLines(Paths.get(filePath));
        } catch (NoSuchFileException e) {
            File scoresFile = new File(filePath);
            scoresFile.createNewFile();

            List<String> initialHighscore = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                initialHighscore.add(i, "0");
            }

            Files.write(Paths.get(filePath), initialHighscore);
            scoresList = Files.readAllLines(Paths.get(filePath));
        }

        // Check if the current score is a high score and insert it into appropriate index in scoresList
        for (int j = 0; j < 5; j++) {
            if (game.player.getTotalScore() > Integer.parseInt(scoresList.get(j))) {
                scoresList.add(j, String.valueOf(game.player.getTotalScore()));
                scoresList.remove(scoresList.size() - 1);
                setHighScore = true;
                break;
            }
        }

        // Write the updated leaderboards back to the file
        Files.write(Paths.get(filePath), scoresList);

        //Store the highscores in an integer array
        int[] highscores = new int[5];
        for (int k = 0; k < 5; k++) {
            highscores[k] = Integer.parseInt(scoresList.get(k));
        }

        return highscores;
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        // Add the stage to the input multiplexer, so it can receive input
        // without blocking other input controllers
        game.inputMultiplexer.addProcessor(stage);
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
        // Clear the screen
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        animationTimer += delta;

        // Show a new label every 0.5s until all labels are visible
        if (animationCount < INFO_LABELS && animationTimer > ANIMATION_TIME) {
            // The first label is the title label, so increment the counter BEFORE using it to get
            // the actor from the stage
            animationCount++;
            animationTimer = 0f;

            stage.getActors().get(animationCount).setVisible(true);
        }

        stage.act();
        stage.draw();
    }

    /**
     * Called when the window is resized.
     *
     * @param width  The new window width
     * @param height The new window height
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        // Remove the stage from the input multiplexer, so it doesn't fire
        // event listeners when the screen is not visible
        game.inputMultiplexer.removeProcessor(stage);
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
