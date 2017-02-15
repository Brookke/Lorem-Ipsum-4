package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;

public class SettingsScreen extends AbstractScreen {

	/**
	 * Background colour for buttons
	 */
    private static final Color BACKGROUND_COLOUR = Color.GRAY;
    
    /**
     * Vertical offset for positioning UI elements
     */
    private static final float OFFSET = 20.0f;
	
	private Stage stage;
	
	private Skin uiSkins;

	public SettingsScreen(GameMain game) {
		super(game);
		
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        
        initSkin();
        initMenu();
	}
	
	private void initSkin() {
        // Create a font
        BitmapFont font = new BitmapFont();
        uiSkins = new Skin();
        uiSkins.add("default", font);

        // Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.ORANGE);
        pixmap.fill();
        uiSkins.add("background", new Texture(pixmap));

        // Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = uiSkins.newDrawable("background", BACKGROUND_COLOUR);
        textButtonStyle.down = uiSkins.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = uiSkins.newDrawable("background", BACKGROUND_COLOUR);
        textButtonStyle.over = uiSkins.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = uiSkins.getFont("default");
        uiSkins.add("default", textButtonStyle);

        // Create the CheckBoxStyle, using the textures in Assets
        uiSkins.add("uncheck", Assets.UNCHECKED_BOX);
        uiSkins.add("check", Assets.CHECKED_BOX);

        CheckBoxStyle checkBoxStyle = new CheckBoxStyle();
        checkBoxStyle.checkboxOff = uiSkins.getDrawable("uncheck");
        checkBoxStyle.checkboxOn = uiSkins.getDrawable("check");
        checkBoxStyle.font = game.font20;
        checkBoxStyle.fontColor = Color.RED;
        uiSkins.add("default", checkBoxStyle);
        
        // Create the SliderStyle, using generated block textures
        Pixmap slider = new Pixmap(20, 20, Pixmap.Format.RGB888);
        slider.setColor(Color.BLACK);
        slider.fill();
        Pixmap knob = new Pixmap(10, 10, Pixmap.Format.RGB888);
        knob.setColor(Color.GRAY);
        knob.fill();
        uiSkins.add("slider", new Texture(slider));
        uiSkins.add("knob", new Texture(knob));
        
        SliderStyle sliderStyle = new SliderStyle();
        sliderStyle.background = uiSkins.getDrawable("slider");
        sliderStyle.knob = uiSkins.getDrawable("knob");
        uiSkins.add("default-horizontal", sliderStyle);
	}
	
	private void initMenu() {
		// Style for the title text, using 30pt font
        LabelStyle titleStyle = new LabelStyle(game.font30, Color.RED);
        // Style for the labels, using 20pt font
        LabelStyle labelStyle = new LabelStyle(game.font20, Color.RED);
        
        // Create and position the label containing title text
        Label title = new Label("Settings", titleStyle);
        title.setPosition(Gdx.graphics.getWidth() / 2 - title.getWidth()/2, 
        		Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 16);

        // Create and position the back button, using the default TextButtonStyle in uiSkins
        TextButton backButton = new TextButton("Back", uiSkins);
        backButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);
        
        // Create and position the mute check box, using the default CheckBoxStyle in uiSkins; 
        // the spaces in the text provide spacing between it and the check box texture
        CheckBox muteCheckBox = new CheckBox("  Mute", uiSkins);
        muteCheckBox.setPosition(Gdx.graphics.getWidth() / 2 - muteCheckBox.getWidth()/2, Gdx.graphics.getHeight() / 2 + OFFSET*6);
        
        Label musicLabel = new Label("Music volume:", labelStyle);
        musicLabel.setPosition(Gdx.graphics.getWidth() / 2 - musicLabel.getWidth()/2, Gdx.graphics.getHeight() / 2 + OFFSET*2);
        
        // Create and position the horizontal music volume slider, using the default SliderStyle
        // in uiSkins
        Slider musicSlider = new Slider(0, 1, 0.1f, false, uiSkins);
        musicSlider.setPosition(Gdx.graphics.getWidth() / 2 - musicSlider.getWidth()/2, Gdx.graphics.getHeight() / 2 + OFFSET*0.5f);
        musicSlider.setValue(Settings.MUSIC_VOLUME);
        
        Label sfxLabel = new Label("Sound effects volume:", labelStyle);
        sfxLabel.setPosition(Gdx.graphics.getWidth() / 2 - sfxLabel.getWidth()/2, Gdx.graphics.getHeight() / 2 - OFFSET*2.5f);
        
        Slider sfxSlider = new Slider(0, 1, 0.1f, false, uiSkins);
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
            		game.music.play();
            	else
            		game.music.pause();
            	
            	Settings.MUTED = !Settings.MUTED;
            }
        });

        // Called when the slider is moved - changes the music volume
        musicSlider.addListener(new ChangeListener()
        {
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				game.music.setVolume(musicSlider.getValue());
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
				game.sound.play(Settings.SFX_VOLUME);
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
