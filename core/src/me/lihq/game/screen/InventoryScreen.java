package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.models.Clue;

public class InventoryScreen extends AbstractScreen {
    
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
        
        buttonSkins = new Skin();
        
        zoomed = false;
		
		initMenu();
	}
	
	private void initMenu() {
        //Creating the label containing text and determining  its size and location on screen
        Label text = Assets.getLabel("Inventory", true);
        Label text2 = Assets.getLabel("Inventory", true);

        TextButton resumeButton = Assets.getTextButton("Close");
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
				
				Label.LabelStyle labelStyle = new Label.LabelStyle(Assets.FONT15, Color.RED);
		        Label text = new Label(c.getName(), labelStyle);
		        text.setPosition(OFFSET+(Settings.CLUE_SIZE*2-text.getWidth())/2+Settings.CLUE_SIZE*2*((count-1)%CLUES_PER_ROW),
		        		Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 4 - 20 - Settings.CLUE_SIZE*2*(int)((count-1)/CLUES_PER_ROW));
		        stage.addActor(text);
				

				imgBtn.addListener(new ClickListener()
		        {
		            @Override
		            public void clicked(InputEvent event, float x, float y)
		            {
		                game.inputMultiplexer.removeProcessor(stage);
		            	game.inputMultiplexer.addProcessor(zoomedStage);
		            	
		            	Image img = new Image(buttonSkins.getDrawable(c.getName()));
		            	img.setPosition(Gdx.graphics.getWidth()/2-img.getWidth()/2,
		            			Gdx.graphics.getHeight()/2-img.getHeight()/2);
		            	zoomedStage.addActor(img);
		            	
		            	Label name = Assets.getLabel(c.getName(), false);
		            	name.setPosition(Gdx.graphics.getWidth()/2-name.getWidth()/2,
		            			Gdx.graphics.getHeight()/2+img.getHeight()/2+name.getHeight());
		            	zoomedStage.addActor(name);

		            	Label description = new Label(c.getDescription(), labelStyle);
		            	description.setPosition(Gdx.graphics.getWidth()/2-description.getWidth()/2,
		            			Gdx.graphics.getHeight()/2-img.getHeight()/2-description.getHeight()*2);
		            	zoomedStage.addActor(description);
		            	
		                TextButton backButton = Assets.getTextButton("Back");
		                backButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);
		                zoomedStage.addActor(backButton);

		                backButton.addListener(new ClickListener()
		                {
		                    @Override
		                    public void clicked(InputEvent event, float x, float y)
		                    {
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

	@Override
	public void show() {
        addButtons();
        game.inputMultiplexer.addProcessor(stage);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.player.addPlayTime(delta);
        
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
        game.inputMultiplexer.removeProcessor(stage);
	}

	@Override
	public void dispose() {
		stage.dispose();
		zoomedStage.dispose();
		buttonSkins.dispose();
	}

}
