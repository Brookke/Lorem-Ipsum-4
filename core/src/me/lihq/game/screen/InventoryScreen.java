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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.models.Clue;

public class InventoryScreen extends AbstractScreen {
	
    private static final Color BACKGROUND_COLOR = Color.GRAY;
    
    private static final int CLUES_PER_ROW = 7;
    private static final float OFFSET = (Gdx.graphics.getWidth() - Settings.CLUE_SIZE*2*CLUES_PER_ROW)/2f;
	
	private Stage stage;
	private Stage zoomedStage;
	
	private Skin buttonSkins;
	
	private boolean zoomed;

	public InventoryScreen(GameMain game) {
		super(game);
		
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        zoomedStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        
        zoomed = false;
		
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
        
        Label text2 = new Label("Inventory", textStyle);
        text2.setFontScale(2, 2);
        text2.setBounds(Gdx.graphics.getWidth() / 2 - text.getWidth(), Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 16, text.getWidth(), text.getHeight());


        TextButton resumeButton = new TextButton("Close", buttonSkins);
        resumeButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);

        stage.addActor(text);
        stage.addActor(resumeButton);
        zoomedStage.addActor(text2);
        
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
				
				imgBtn.setPosition(OFFSET+Settings.CLUE_SIZE/2+Settings.CLUE_SIZE*2*((count-1)%CLUES_PER_ROW),
						Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 4 - Settings.CLUE_SIZE*2*(int)((count-1)/CLUES_PER_ROW));
				stage.addActor(imgBtn);
				
		        LabelStyle textStyle = new LabelStyle(buttonSkins.getFont("default"), Color.RED);
		        Label text = new Label(c.getName(), textStyle);
		        text.setPosition(OFFSET+(Settings.CLUE_SIZE*2-text.getWidth())/2+Settings.CLUE_SIZE*2*((count-1)%CLUES_PER_ROW),
		        		Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 4 - 20 - Settings.CLUE_SIZE*2*(int)((count-1)/CLUES_PER_ROW));
		        stage.addActor(text);
				

				imgBtn.addListener(new ClickListener()
		        {
		            @Override
		            public void clicked(InputEvent event, float x, float y)
		            {
		                Gdx.input.setInputProcessor(zoomedStage);
		            	
		            	Image img = new Image(buttonSkins.getDrawable(c.getName()));
		            	img.setPosition(Gdx.graphics.getWidth()/2-img.getWidth()/2,
		            			Gdx.graphics.getHeight()/2-img.getHeight()/2);
		            	zoomedStage.addActor(img);
		            	
		            	Label name = new Label(c.getName(), textStyle);
		            	name.setPosition(Gdx.graphics.getWidth()/2-name.getWidth()/2,
		            			Gdx.graphics.getHeight()/2+img.getHeight()/2+name.getHeight());
		            	zoomedStage.addActor(name);

		            	Label description = new Label(c.getDescription(), textStyle);
		            	description.setPosition(Gdx.graphics.getWidth()/2-description.getWidth()/2,
		            			Gdx.graphics.getHeight()/2-img.getHeight()/2-description.getHeight()*2);
		            	zoomedStage.addActor(description);
		            	
		                TextButton backButton = new TextButton("Back", buttonSkins);
		                backButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);
		                zoomedStage.addActor(backButton);

		                backButton.addListener(new ClickListener()
		                {
		                    @Override
		                    public void clicked(InputEvent event, float x, float y)
		                    {
		                        Gdx.input.setInputProcessor(stage);
		                        
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

	@Override
	public void show() {
        Gdx.input.setInputProcessor(stage);
        
        addButtons();
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if (zoomed) {
        	zoomedStage.act();
        	zoomedStage.draw();
        }
        else {
        	stage.act();
    		stage.draw();
        }
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
