package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.Assets;
import me.lihq.game.GameMain;

public class SettingsScreen extends AbstractScreen {

    private static final Color BACKGROUND_COLOR = Color.GRAY;
	
	private Stage stage;
	
	private Skin buttonSkins;

	public SettingsScreen(GameMain game) {
		super(game);
		
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        game.inputMultiplexer.addProcessor(stage);
        
        initSkin();
        initMenu();
	}
	
	private void initSkin() {
        //Create a font
        BitmapFont font = new BitmapFont();
        buttonSkins = new Skin();
        buttonSkins.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.ORANGE);
        pixmap.fill();
        buttonSkins.add("background", new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonSkins.newDrawable("background", BACKGROUND_COLOR);
        textButtonStyle.down = buttonSkins.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = buttonSkins.newDrawable("background", BACKGROUND_COLOR);
        textButtonStyle.over = buttonSkins.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = buttonSkins.getFont("default");
        buttonSkins.add("default", textButtonStyle);

        buttonSkins.add("uncheck", Assets.UNCHECKED_BOX);
        buttonSkins.add("check", Assets.CHECKED_BOX);

        CheckBoxStyle checkBoxStyle = new CheckBoxStyle();
        checkBoxStyle.checkboxOff = buttonSkins.getDrawable("uncheck");
        checkBoxStyle.checkboxOn = buttonSkins.getDrawable("check");
        checkBoxStyle.font = buttonSkins.getFont("default");
        buttonSkins.add("default", checkBoxStyle);
	}
	
	private void initMenu() {
        LabelStyle textStyle = new LabelStyle(game.font30, Color.RED);
        
        //Creating the label containing text and determining  its size and location on screen
        Label text = new Label("Settings", textStyle);
        text.setBounds(Gdx.graphics.getWidth() / 2 - text.getWidth()/2, Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 16, text.getWidth(), text.getHeight());

        TextButton backButton = new TextButton("Back", buttonSkins);
        backButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);
        
        stage.addActor(text);
        stage.addActor(backButton);
        
        
        CheckBox muteCheckBox = new CheckBox("Mute", buttonSkins);
        stage.addActor(muteCheckBox);

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
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
