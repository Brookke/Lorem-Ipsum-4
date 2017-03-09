package me.lihq.game.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.lihq.game.*;
import me.lihq.game.models.Room;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.NPC;
import me.lihq.game.people.controller.PlayerController;
import me.lihq.game.screen.elements.*;

import java.util.List;

/**
 * This is the screen that is responsible for the navigation of the player around the game.
 * It displays the current room that the player is in, and allows the user to move the player around between rooms.
 */
public class NavigationScreen extends AbstractScreen {
    /**
     * The controller that listens for key inputs
     */
    public PlayerController playerController;

    /**
     * This is the SpeechboxManager for the main game
     */
    public SpeechboxManager speechboxMngr;

    /**
     * This is the main ConversationManager that controls the conversation mechanic
     */
    public ConversationManagement convMngt;

    /**
     * This boolean determines whether the black is fading in or out
     */
    private boolean fadeToBlack = true;

    /**
     * This boolean determines whether the map needs to be updated in the next render loop
     */
    private boolean changeMap = false;

    /**
     * This is the list of NPCs in the current Room
     */
    private List<NPC> currentNPCS;

    /**
     * This is the map renderer that also renders Sprites
     */
    private OrthogonalTiledMapRendererWithPeople tiledMapRenderer;

    /**
     * The camera to render the map to
     */
    private OrthographicCamera camera = new OrthographicCamera();
    private Viewport viewport;
    private SpriteBatch spriteBatch;

    /**
     * This stores whether the game is paused or not
     */
    private boolean pause = false;

    /**
     * This is the StatusBar that shows at the bottom
     */
    private StatusBar statusBar;

    /**
     * This contains the transition data for the player, if they are moving rooms.
     * @author Lorem-Ipsum
     */
    private Room.Transition roomTransition = null;

    /**
     * The amount of ticks it takes for the black to fade in and out
     */
    private float ANIM_TIME = Settings.TPS / 1.5f;

    /**
     * The black sprite that is used to fade in/out
     */
    private Sprite BLACK_BACKGROUND = new Sprite();

    /**
     * The current animation frame of the fading in/out
     */
    private float animTimer = 0.0f;

    /**
     * The Sprite that is to draw the arrows on the screen by doors
     */
    private RoomArrow arrow;

    /**
     * This is the room name tag that is to be rendered to the screen
     * <p>
     * If it is null then there is no tag to display
     */
    private RoomTag roomTag = null;

    /**
     * Initialises the navigation screen
     *
     * @param game The main game instance
     */
    public NavigationScreen(GameMain game) {
        super(game);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera.setToOrtho(false, w, h);
        camera.update();

        /******************** Added by team JAAPAN ********************/
        BLACK_BACKGROUND = new Sprite(UIHelpers.createBackgroundTexture(Color.BLACK, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        /**************************** End *****************************/

        viewport = new FitViewport(w / Settings.ZOOM, h / Settings.ZOOM, camera);

        tiledMapRenderer = new OrthogonalTiledMapRendererWithPeople(game.player.getRoom().getTiledMap(), game);

        playerController = new PlayerController(game, game.player);

        spriteBatch = new SpriteBatch();

        statusBar = new StatusBar(game);

        speechboxMngr = new SpeechboxManager(game);

        convMngt = new ConversationManagement(game, game.player, speechboxMngr);

        tiledMapRenderer.addPerson(game.player);

        arrow = new RoomArrow(game.player);


    }

    /**
     * This is ran when the navigation screen becomes the visible screen in GameMain
     */
    @Override
    public void show() {
        game.inputMultiplexer.addProcessor(speechboxMngr.multiplexer);
        game.inputMultiplexer.addProcessor(statusBar.stage);
        game.inputMultiplexer.addProcessor(playerController);
    }

    /**
     * This method is called once a game tick
     */
    @Override
    public void update() {
        convMngt.finishConversation();

        if (!pause) { //this statement contains updates that shouldn't happen during a pause
            playerController.update();
            game.player.update();
            arrow.update();

            for (AbstractPerson n : currentNPCS) {
                n.update();
            }

            speechboxMngr.update();
        }

        //Some things should be updated all the time.
        updateTransition();

        if (roomTag != null) {
            roomTag.update();
        }
    }


    /**
     * This method is called once a game tick to update the room transition animation
     */
    private void updateTransition() {
        if (roomTransition != null) {
            BLACK_BACKGROUND.setAlpha(Interpolation.pow4.apply(0, 1, animTimer / ANIM_TIME));

            if (fadeToBlack) {
                animTimer++;

                if (animTimer == ANIM_TIME) {
                    game.player.moveRoom(roomTransition);
                }

                if (animTimer > ANIM_TIME) {
                    fadeToBlack = false;
                }
            } else {
                animTimer--;

                if (animTimer < 0) {
                    finishRoomTransition();
                }
            }
        }
    }

    /**
     * This is called when the player decides to move to another room
     *
     * Changed to now take transition data
     * @author Lorem Ipsum
     */
    public void initialiseRoomChange(Room.Transition transition) {
        pause = true; //pause all non necessary updates like player movement
        roomTransition = transition;
    }

    /**
     * This is called when the room transition animation has completed so the necessary variables
     * can be returned to their normal values
     */
    public void finishRoomTransition() {
        animTimer = 0;
        roomTransition = null;
        fadeToBlack = true;
        pause = false;
        roomTag = new RoomTag(game, game.player.getRoom().getName());
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();
        game.player.addPlayTime(delta);

        game.player.pushCoordinatesToSprite();
        for (AbstractPerson n : currentNPCS) {
            n.pushCoordinatesToSprite();

        }

        if (changeMap) {
            tiledMapRenderer.setMap(game.player.getRoom().getTiledMap());
            tiledMapRenderer.clearPeople();
            tiledMapRenderer.addPerson((List<AbstractPerson>) ((List<? extends AbstractPerson>) currentNPCS));
            tiledMapRenderer.addPerson(game.player);
            changeMap = false;
        }

        camera.position.x = game.player.getX();
        camera.position.y = game.player.getY();
        camera.update();

        tiledMapRenderer.setView(camera);

        tiledMapRenderer.render();
        //Everything to be drawn relative to bottom left of the map
        tiledMapRenderer.getBatch().begin();

        arrow.draw(tiledMapRenderer.getBatch());

        game.player.getRoom().drawClues(delta, tiledMapRenderer.getBatch());

        tiledMapRenderer.getBatch().end();

        //Everything to be drawn relative to bottom left of the screen
        spriteBatch.begin();

        if (roomTransition != null) {
            BLACK_BACKGROUND.draw(spriteBatch);
        }

        if (game.player.getRoom().getName().equals("Secret Room")){
            spriteBatch.draw(Assets.Cover, Gdx.graphics.getWidth()/2 - Assets.Cover.getWidth()/2 + Settings.TILE_SIZE/2, Gdx.graphics.getHeight()/2 - Assets.Cover.getHeight()/2 + Settings.TILE_SIZE);
        }

        if (roomTag != null) {
            roomTag.render(spriteBatch);
        }

        if (Settings.DEBUG) {
            DebugOverlay.renderDebugInfo(spriteBatch);
        }


        spriteBatch.end();

        statusBar.render();
        speechboxMngr.render();

    }

    /**
     * This is called when the window is resized
     *
     * @param width  The new width
     * @param height The new height
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        statusBar.resize(width, height);
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
     * This method is called when the window is brought back into focus
     */
    @Override
    public void resume() {
    }

    /**
     * This method is called when the user hides the window
     */
    @Override
    public void hide() {
        game.inputMultiplexer.removeProcessor(statusBar.stage);
        game.inputMultiplexer.removeProcessor(speechboxMngr.multiplexer);
        game.inputMultiplexer.removeProcessor(playerController);
    }

    /**
     * This is to be called when you want to dispose of all data
     */
    @Override
    public void dispose() {
        tiledMapRenderer.dispose();
        statusBar.dispose();
        spriteBatch.dispose();
    }

    /**
     * This lets the tiledMapRenderer know that on the next render it should reload the current room from the player.
     */
    public void updateTiledMapRenderer() {
        this.changeMap = true;
        this.currentNPCS = game.getNPCS(game.player.getRoom());
    }

    /**
     * This method sets the RoomTag to the parameter which is then rendered next render loop
     *
     * @param tag The RoomTag to be rendered
     */
    public void setRoomTag(RoomTag tag) {
        this.roomTag = tag;
    }

    /**
     * This method gets the list of current NPCs in the current room
     *
     * @return (List<NPC>) the value of currentNPCs {@link #currentNPCS}
     */
    public List<NPC> getNPCs() {
        return currentNPCS;
    }
}
