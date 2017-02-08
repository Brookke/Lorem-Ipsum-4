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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.GameMain;
import me.lihq.game.models.Clue;

public class InventoryScreen extends AbstractScreen {
	
    private static final Color BACKGROUND_COLOR = Color.GRAY;
	
	private Stage stage;
	
	private Skin buttonSkins;

	public InventoryScreen(GameMain game) {
		super(game);
		
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		
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
	}
	
	private void initMenu() {
        LabelStyle textStyle = new LabelStyle(buttonSkins.getFont("default"), Color.RED);

        //Creating the label containing text and determining  its size and location on screen
        Label text = new Label("Inventory", textStyle);
        text.setFontScale(2, 2);
        text.setBounds(Gdx.graphics.getWidth() / 2 - text.getWidth(), Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 16, text.getWidth(), text.getHeight());

        TextButton resumeButton = new TextButton("Resume", buttonSkins);
        resumeButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);

        stage.addActor(text);
        stage.addActor(resumeButton);
        
        resumeButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(game.navigationScreen);
            }
        });
	}
	
	private void addButtons() {
		int count = 0;
		for (Clue c : game.player.collectedClues) {
			count++;
			if (!buttonSkins.has(c.getName(), TextureRegion.class)) {
				buttonSkins.add(c.getName(), new TextureRegion(c.getTexture(), c.getRegionX(), c.getRegionY(), c.getRegionWidth(), c.getRegionHeight()));
				ImageButton imgBtn = new ImageButton(buttonSkins.getDrawable(c.getName()));
				
				imgBtn.setPosition(64*count, Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 4);
				stage.addActor(imgBtn);
				

				imgBtn.addListener(new ClickListener()
		        {
		            @Override
		            public void clicked(InputEvent event, float x, float y)
		            {
		                game.setScreen(game.navigationScreen);
		            }
		        });
			}
		}
		
		
	}

	@Override
	public void show() {
        Gdx.input.setInputProcessor(stage);
        
        addButtons();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(135, 206, 235, 1);
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
		stage.dispose();
		buttonSkins.dispose();
	}

}
