package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.screen.elements.UIHelpers;

/**
 * The screen that allows the user to change the various settings of the game.
 * Currently limited to volume controls.
 *
 * @author JAAPAN
 */
public class SettingsScreen extends AbstractScreen {

    /**
     * Vertical offset for positioning UI elements
     */
    private static final float OFFSET = 20.0f;

    /**
     * The stage used for rendering the UI and handling input.
     */
    private Stage stage;

    /**
     * Constructs the Settings screen.
     *
     * @param game Reference to the main Game class, used so this screen can alter the current
     *             screen of the game, and access the input multiplexer
     */
    public SettingsScreen(GameMain game) {
        super(game);

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        initMenu();
    }

    /**
     * Initialises the UI elements on the screen, and sets up event handlers.
     */
    private void initMenu() {
        // Create and position the label containing title text
        Label title = UIHelpers.createLabel("Settings", Assets.TITLE_FONT);
        title.setPosition(Gdx.graphics.getWidth()/2-title.getWidth()/2,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/6);


        // Create and position the back button, using the standard style
        TextButton backButton = UIHelpers.createTextButton("Back");
        backButton.setSize(Gdx.graphics.getWidth() / 3, 80);
        backButton.setPosition(Gdx.graphics.getWidth() / 2 - backButton.getWidth() / 2, Gdx.graphics.getHeight() / 16);

        // Create and position the mute check box, using the standard style
        CheckBox muteCheckBox = UIHelpers.createCheckBox("Mute");
        muteCheckBox.toggle();
        muteCheckBox.setPosition(Gdx.graphics.getWidth() / 2 - muteCheckBox.getWidth() / 2, Gdx.graphics.getHeight() / 2 + OFFSET * 6);

        Label musicLabel = new Label("Music volume:", Assets.CHECK_SKIN);
        musicLabel.setPosition(Gdx.graphics.getWidth() / 2 - musicLabel.getWidth() / 2, Gdx.graphics.getHeight() / 2 + OFFSET * 2);

        // Create and position the horizontal music volume slider, using the standard style
        Slider musicSlider = UIHelpers.createSlider(0f, 1f, 0.1f, false);
        musicSlider.setPosition(Gdx.graphics.getWidth() / 2 - musicSlider.getWidth() / 2, Gdx.graphics.getHeight() / 2 + OFFSET * 0.5f);
        musicSlider.setValue(Settings.MUSIC_VOLUME);

        Label sfxLabel = new Label("Sound Effects volume:", Assets.CHECK_SKIN);
        sfxLabel.setPosition(Gdx.graphics.getWidth() / 2 - sfxLabel.getWidth() / 2, Gdx.graphics.getHeight() / 2 - OFFSET * 2.5f);

        Slider sfxSlider = UIHelpers.createSlider(0f, 1f, 0.1f, false);
        sfxSlider.setPosition(Gdx.graphics.getWidth() / 2 - musicSlider.getWidth() / 2, Gdx.graphics.getHeight() / 2 - OFFSET * 4);
        sfxSlider.setValue(Settings.SFX_VOLUME);

        // Add all UI elements to the stage, to handle rendering and input
        stage.addActor(title);
        stage.addActor(backButton);
        stage.addActor(muteCheckBox);
        stage.addActor(musicLabel);
        stage.addActor(musicSlider);
        stage.addActor(sfxLabel);
        stage.addActor(sfxSlider);


        // Event listeners for UI elements
        // Return to the previous screen (Main menu or pause)
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.screenManager.currentScreen == Screens.mainMenu) {
                    game.screenManager.setScreen(Screens.mainMenu);
                } else {
                    game.screenManager.setScreen(Screens.pauseMenu);
                }
            }
        });

        // Toggle the muted state of the game
        muteCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Settings.MUTED) {
                    Assets.MUSIC.play();
                } else {
                    Assets.MUSIC.pause();
                }

                Settings.MUTED = !Settings.MUTED;
            }
        });

        // Called when the slider is moved - changes the music volume
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Assets.MUSIC.setVolume(musicSlider.getValue());
                Settings.MUSIC_VOLUME = musicSlider.getValue();
            }
        });

        // Called when the slider is moved - changes the sound effects volume
        sfxSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.SFX_VOLUME = sfxSlider.getValue();
            }
        });
        // Plays the sound effect when the user lets go of the slider, so they can
        // hear how loud it is.
        sfxSlider.addListener(new InputListener() {
            // Needed for touchUp() to work.
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (!Settings.MUTED) {
                    Assets.SOUND.play(Settings.SFX_VOLUME);
                }
            }
        });
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
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
