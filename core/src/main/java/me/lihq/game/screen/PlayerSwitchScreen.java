package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.UIHelpers;

/**
 * Class added by Lorem Ipsum
 *
 * Screen displayed when the second player switches in and the first player switches out.
 *
 * @AUTHOR Lorem Ipsum
 */
public class PlayerSwitchScreen extends AbstractScreen {

    /**
     * The main stage for rendering and handling input.
     */
    private Stage stage;

    public PlayerSwitchScreen(GameMain game)
    {
        super(game);

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        /**
         * Set up the Text Label that tells teh players to switch
         */
        Label text;
        text = UIHelpers.createLabel("Time to switch players!", true);//<<<<<<<<NOTE<<<<<<<<<<<<<<<<NOTE<<<<<<<<<<<<<NOTE<<<<<<<<<<<<NOTE<<<<<<<<<not sure what to say here, i assume this is fine.
        text.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 3/4);

        /**
         * Set up the button to click
         */
        TextButton continueButton;
        continueButton = UIHelpers.createTextButton("Ready?");
        continueButton.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 2);

        /**
         * Add the actors and a button listener
         */
        stage.addActor(text);
        stage.addActor(continueButton);
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //change to player 2 here //wait till GameStatus (or whatever its called) is made
            }
        });
    }

    @Override
    public void show()
    {
        game.inputMultiplexer.addProcessor(stage);
    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height, true);
    }
    @Override
    public void update()
    {

    }

    @Override
    public void render(float delta)
    {
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose()
    {
        stage.dispose();
    }

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
