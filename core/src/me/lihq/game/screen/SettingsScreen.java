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

public class SettingsScreen extends AbstractScreen {
    
    /**
     * Vertical offset for positioning UI elements
     */
    private static final float OFFSET = 20.0f;
	
	private Stage stage;

	public SettingsScreen(GameMain game) {
		super(game);
		
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        
        initMenu();
	}
	
	private void initMenu() {
        // Create and position the label containing title text
        Label title = Assets.createLabel("Settings", true);

        // Create and position the back button, using the default TextButtonStyle in uiSkins
        TextButton backButton = Assets.createTextButton("Back");
        backButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);
        
        // Create and position the mute check box, using the default CheckBoxStyle in uiSkins; 
        // the spaces in the text provide spacing between it and the check box texture
        CheckBox muteCheckBox = Assets.createCheckBox("  Mute");
        muteCheckBox.setPosition(Gdx.graphics.getWidth() / 2 - muteCheckBox.getWidth()/2, Gdx.graphics.getHeight() / 2 + OFFSET*6);
        
        Label musicLabel = Assets.createLabel("Music volume:", false);
        musicLabel.setPosition(Gdx.graphics.getWidth() / 2 - musicLabel.getWidth()/2, Gdx.graphics.getHeight() / 2 + OFFSET*2);
        
        // Create and position the horizontal music volume slider, using the default SliderStyle
        // in uiSkins
        Slider musicSlider = Assets.createSlider(0f, 1f, 0.1f, false);
        musicSlider.setPosition(Gdx.graphics.getWidth() / 2 - musicSlider.getWidth()/2, Gdx.graphics.getHeight() / 2 + OFFSET*0.5f);
        musicSlider.setValue(Settings.MUSIC_VOLUME);
        
        Label sfxLabel = Assets.createLabel("Sound effects volume:", false);
        sfxLabel.setPosition(Gdx.graphics.getWidth() / 2 - sfxLabel.getWidth()/2, Gdx.graphics.getHeight() / 2 - OFFSET*2.5f);
        
        Slider sfxSlider = Assets.createSlider(0f, 1f, 0.1f, false);
        sfxSlider.setPosition(Gdx.graphics.getWidth() / 2 - musicSlider.getWidth()/2, Gdx.graphics.getHeight() / 2 - OFFSET*4);
        sfxSlider.setValue(Settings.SFX_VOLUME);

        // Add all UI elements to the stage
        stage.addActor(title);
        stage.addActor(backButton);
        stage.addActor(muteCheckBox);
        stage.addActor(musicLabel);
        stage.addActor(musicSlider);
        stage.addActor(sfxLabel);
        stage.addActor(sfxSlider);

        
        // Event listeners for UI elements
        // Return to the previous screen (Main menu or pause)
        backButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
            	if (game.mainMenu)
            		game.setScreen(game.menuScreen);
            	else
            		game.setScreen(game.pauseScreen);
            }
        });
        
        // Toggle the muted state of the game
        muteCheckBox.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
            	if (Settings.MUTED)
            		Assets.MUSIC.play();
            	else
            		Assets.MUSIC.pause();
            	
            	Settings.MUTED = !Settings.MUTED;
            }
        });

        // Called when the slider is moved - changes the music volume
        musicSlider.addListener(new ChangeListener()
        {
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				Assets.MUSIC.setVolume(musicSlider.getValue());
				Settings.MUSIC_VOLUME = musicSlider.getValue();
			}
        });
        
        // Called when the slider is moved - changes the sound effects volume
        sfxSlider.addListener(new ChangeListener()
        {
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				Settings.SFX_VOLUME = sfxSlider.getValue();
			}
        });
        // Plays the sound effect when the user lets go of the slider, so they can
        // hear how loud it is.
        sfxSlider.addListener(new InputListener()
        {
        	// Needed for touchUp() to work.
        	@Override
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
        	{
        		return true;
        	}
        	@Override
        	public void touchUp(InputEvent event, float x, float y, int pointer, int button)
        	{
        		Assets.SOUND.play(Settings.SFX_VOLUME);
        	}
        });
	}

	@Override
	public void show() {
		// Add the stage to the input multiplexer, so it can receive input
		// without blocking other input controllers
        game.inputMultiplexer.addProcessor(stage);
	}

	@Override
	public void update() {
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
		// Remove the stage from the input multiplexer, so it doesn't fire
		// event listeners when the screen is not visible
        game.inputMultiplexer.removeProcessor(stage);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
