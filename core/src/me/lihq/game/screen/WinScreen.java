package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.Assets;
import me.lihq.game.GameMain;

/**
 * The screen that is displayed when the player wins the game. Displays their score and time,
 * and allows them to return to the main menu.
 * 
 * @author JAAPAN
 * 
 */
public class WinScreen extends AbstractScreen {
	
	private static final float OFFSET = 50f;
	private static final float LEFT_ALIGN = Gdx.graphics.getWidth() / 16;
	
	private Stage stage;
	
	private int animationCount;
	private float animationTimer;

	public WinScreen(GameMain game) {
		super(game);

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        
        initMenu();
        
        animationCount = 0;
        animationTimer = 0f;
	}
	
	private void initMenu()
	{
		Label title = Assets.getLabel("You Found the Killer!", true);
		stage.addActor(title);
		
		Label cluesLabel = Assets.getLabel("Clues Found: " + game.player.collectedClues.size(), false);
		cluesLabel.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 + OFFSET * 4);
		cluesLabel.setVisible(false);
		
		// TODO: Number of red herrings
		Label redHerringLabel = Assets.getLabel("Red Herrings Found: " + game.player.collectedClues.size(), false);
		redHerringLabel.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 + OFFSET * 3);
		redHerringLabel.setVisible(false);
		
		Label questionsAsked = Assets.getLabel("Questions Asked: ", false);
		questionsAsked.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 + OFFSET * 2);
		questionsAsked.setVisible(false);
		
		Label accusedNPCs = Assets.getLabel("Number of People Wrongly Accused: ", false);
		accusedNPCs.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 + OFFSET * 1);
		accusedNPCs.setVisible(false);
		
		Label basicScoreLabel = Assets.getLabel("Points Gained: " + game.player.getScore(), false);
		basicScoreLabel.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2);
		basicScoreLabel.setVisible(false);
		
		Label timeTaken = Assets.getLabel("Time Taken: " + game.player.getPlayTime() + "s", false);
		timeTaken.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 - OFFSET * 1);
		timeTaken.setVisible(false);
		
		Label bonusScoreLabel = Assets.getLabel("Time Bonus: ", false);
		bonusScoreLabel.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 - OFFSET * 2);
		bonusScoreLabel.setVisible(false);
		
		Label finalScoreLabel = Assets.getLabel("Total Score: ", false);
		finalScoreLabel.setPosition(LEFT_ALIGN, Gdx.graphics.getHeight() / 2 - OFFSET * 3);
		finalScoreLabel.setVisible(false);
		
		stage.addActor(cluesLabel);
		stage.addActor(redHerringLabel);
		stage.addActor(questionsAsked);
		stage.addActor(accusedNPCs);
		stage.addActor(basicScoreLabel);
		stage.addActor(timeTaken);
		stage.addActor(bonusScoreLabel);
		stage.addActor(finalScoreLabel);
		
		TextButton mainMenuButton = Assets.getTextButton("Main Menu");
		mainMenuButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);
		
		stage.addActor(mainMenuButton);
		
		mainMenuButton.addListener(new ClickListener()
        {
			/**
			 * Resets the state of the game, so it can be played again, and opens the main menu
			 */
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
            	game.resetAll();
            	game.setScreen(game.menuScreen);
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
		// Clear the screen
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        animationTimer += delta;
        
        if (animationCount < 8 && animationTimer > 0.5f) {
        	animationCount++;
        	animationTimer = 0f;
        	
        	stage.getActors().get(animationCount).setVisible(true);
        }
        
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
