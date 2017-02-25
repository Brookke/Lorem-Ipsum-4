package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.models.Clue;
import me.lihq.game.screen.elements.UIHelpers;

/**
 * The screen that shows the user what clues they have collected.
 *
 * @author JAAPAN
 */
public class InventoryScreen extends AbstractScreen {

    /**
     * Number of clues per row; used for calculating the width of each 'slot'.
     */
    private static final int CLUES_PER_ROW = 7;

    /**
     * The border offset between the end clues of a row, and the edge of the window.
     */
    private static final float OFFSET = (Gdx.graphics.getWidth() - Settings.CLUE_SIZE * 2 * CLUES_PER_ROW) / 2f;

    /**
     * The main stage for rendering and handling input.
     */
    private Stage stage;

    /**
     * The stage used when zoomed in on an individual clue.
     */
    private Stage zoomedStage;

    /**
     * The skin for storing the textures of the clues, for use in the ImageButtons.
     */
    private Skin buttonSkins;

    /**
     * Whether the inventory is zoomed in on a particular clue or not.
     */
    private boolean zoomed = false;

    /**
     * Constructs the Inventory screen.
     *
     * @param game Reference to the main Game class, used so this screen can alter the current
     *             screen of the game, and access the player and input multiplexer
     */
    public InventoryScreen(GameMain game) {
        super(game);

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        zoomedStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        buttonSkins = new Skin();

        initMenu();
    }

    /**
     * Initialises the UI elements on the screen, and sets up event handlers.
     */
    private void initMenu() {
        // Create and position the label containing title text
        Label text = UIHelpers.createLabel("Inventory", true);
        // We need 2 copies, one for each stage
        Label text2 = UIHelpers.createLabel("Inventory", true);

        TextButton resumeButton = UIHelpers.createTextButton("Close");
        resumeButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);

        stage.addActor(text);
        stage.addActor(resumeButton);
        zoomedStage.addActor(text2);

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.screenManager.setScreen(Screens.navigation);
            }
        });
    }

    /**
     * Checks whether any new clues have been collected since the last time the inventory was open;
     * if any have, they are added to the stage.
     */
    private void addButtons() {
        int count = 0;
        for (Clue c : game.player.collectedClues) {
            count++;
            // If there is no TextureRegion in the skin with the clue's name, add it to the skin, create a button
            // and a label with its name and add them to the stage
            if (!buttonSkins.has(c.getName(), TextureRegion.class)) {
                buttonSkins.add(c.getName(), new TextureRegion(c.getTexture(), c.getRegionX(), c.getRegionY(), c.getRegionWidth(), c.getRegionHeight()));
                ImageButton imgBtn = new ImageButton(buttonSkins.getDrawable(c.getName()));

                imgBtn.setPosition(OFFSET + Settings.CLUE_SIZE / 2 + Settings.CLUE_SIZE * 2 * ((count - 1) % CLUES_PER_ROW),
                        Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 4 - Settings.CLUE_SIZE * 2 * (int) ((count - 1) / CLUES_PER_ROW));
                stage.addActor(imgBtn);

                Label text = UIHelpers.createLabel(c.getName(), Assets.FONT15);
                text.setPosition(OFFSET + (Settings.CLUE_SIZE * 2 - text.getWidth()) / 2 + Settings.CLUE_SIZE * 2 * ((count - 1) % CLUES_PER_ROW),
                        Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 4 - 20 - Settings.CLUE_SIZE * 2 * (int) ((count - 1) / CLUES_PER_ROW));
                stage.addActor(text);

                // When a clue is clicked, center it on the screen, display its name and description,
                // and hide all other clues
                imgBtn.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.inputMultiplexer.removeProcessor(stage);
                        game.inputMultiplexer.addProcessor(zoomedStage);

                        Image img = new Image(buttonSkins.getDrawable(c.getName()));
                        img.setPosition(Gdx.graphics.getWidth() / 2 - img.getWidth() / 2,
                                Gdx.graphics.getHeight() / 2 - img.getHeight() / 2);
                        zoomedStage.addActor(img);

                        Label name = UIHelpers.createLabel(c.getName(), false);
                        name.setPosition(Gdx.graphics.getWidth() / 2 - name.getWidth() / 2,
                                Gdx.graphics.getHeight() / 2 + img.getHeight() / 2 + name.getHeight());
                        zoomedStage.addActor(name);

                        Label description = UIHelpers.createLabel(c.getDescription(), Assets.FONT15);
                        description.setPosition(Gdx.graphics.getWidth() / 2 - description.getWidth() / 2,
                                Gdx.graphics.getHeight() / 2 - img.getHeight() / 2 - description.getHeight() * 2);
                        zoomedStage.addActor(description);

                        TextButton backButton = UIHelpers.createTextButton("Back");
                        backButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);
                        zoomedStage.addActor(backButton);

                        // Remove the zoomed elements from the stage, and return to the normal inventory view
                        backButton.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                game.inputMultiplexer.removeProcessor(zoomedStage);
                                game.inputMultiplexer.addProcessor(stage);

                                img.remove();
                                name.remove();
                                description.remove();
                                backButton.remove();

                                zoomed = false;
                            }
                        });

                        zoomed = true;
                    }
                });
            }
        }
    }

    /**
     * Called when this screen becomes the current screen for a Game.
     */
    @Override
    public void show() {
        addButtons();
        if (zoomed) {
            game.inputMultiplexer.addProcessor(zoomedStage);
        } else {
            game.inputMultiplexer.addProcessor(stage);
        }
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
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.player.addPlayTime(delta);

        if (zoomed) {
            zoomedStage.act();
            zoomedStage.draw();
        } else {
            stage.act();
            stage.draw();
        }
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
        // Pause the game, so the gameDuration counter isn't updated
        game.screenManager.setScreen(Screens.pauseMenu);
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
        if (zoomed) {
            game.inputMultiplexer.removeProcessor(zoomedStage);
        } else {
            game.inputMultiplexer.removeProcessor(stage);
        }
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        stage.dispose();
        zoomedStage.dispose();
        buttonSkins.dispose();
    }

}
