package me.lihq.game.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Room;
import me.lihq.game.screen.Screens;
import me.lihq.game.screen.elements.SpeechBox;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the player that the person playing the game will be represented by.
 */
public class Player extends AbstractPerson {
    /**
     * This object stores the clues that the player has picked up.
     */
    public List<Clue> collectedClues = new ArrayList<>();

    /**
     * This stores whether the player is in the middle of a conversation or not.
     */
    public boolean inConversation = false;

    /**
     * Stores whether the player has picked up the murder weapon or not.
     */
    private boolean foundMurderWeapon = false;

    /**
     * The number of useful (non-red herring) clues the player has found.
     */
    private int usefulClues = 0;

    /**
     * The number of questions the player has asked.
     */
    private int questionsAsked = 0;

    /**
     * The personality will be a percent score (0-100) 0 being angry, 50 being neutral, and 100 being happy/nice.
     */
    private int personalityLevel = 50;

    /**
     * The score the player has earned so far.
     */
    private int score = 0;

    /**
     * The current duration of the game, excluding time paused. Used to calculate the time
     * bonus for the score.
     */
    private float gameDuration = 0f;

    /**
     * The number of false accusations the player has made, used in the WinScreen.
     */
    private int falseAccusations = 0;


    /**
     * This is the constructor for player, it creates a new playable person.
     *
     * @param name   The name for the new player.
     * @param imgSrc The image used to represent it.
     */
    public Player(GameMain game, String name, String imgSrc, int tileX, int tileY) {
        super(game, name, "people/player/" + imgSrc, tileX, tileY);
        importDialogue("Player.JSON");
    }

    /**
     * Reads in the JSON file of the character and stores dialogue in the dialogue HashMap
     *
     * @param fileName The file to read from
     */
    @Override
    public void importDialogue(String fileName) {
        jsonData = new JsonReader().parse(Gdx.files.internal("people/player/" + fileName));
    }

    /**
     * This Moves the player to a new tile.
     *
     * @param dir the direction that the player should move in.
     */
    public void move(Direction dir) {
        if (this.state != PersonState.STANDING) {
            return;
        }

        if (!canMove) return;

        //Taken the code that did this test and turned it into a method @author Lorem-Ipsum
        if (roomChangeCheck(dir)) return;

        if (!getRoom().isWalkableTile(this.tileCoordinates.x + dir.getDx(), this.tileCoordinates.y + dir.getDy())) {
            setDirection(dir);
            return;
        }

        initialiseMove(dir);
    }

    /**
     * This method is called when the player interacts with the map
     */
    public void interact() {
        if (inConversation) return;

        NPC npc = getFacingNPC();
        if (npc != null) {
            game.screenManager.navigationScreen.convMngt.startConversation(npc);
        } else if (checkForSecretRoom()) {
            game.screenManager.setScreen(Screens.puzzle);
        } else {
            checkForClue();
            checkForExtraScore();
        }

    }

    /**
     * This method tries to get an NPC if the player is facing one
     *
     * @return null if there isn't an NPC in front of them or the NPC is moving. Otherwise, it returns the NPC
     */
    private NPC getFacingNPC() {
        List<NPC> npcs;
        try {
            npcs = game.currentSnapshot.getNPCs(getRoom());
        } catch (NullPointerException e) {
            return null;
        }
        if (npcs.size() == 0) return null;

        for (NPC npc : npcs) {
            if ((npc.getTileCoordinates().x == getTileCoordinates().x + getDirection().getDx()) &&
                    (npc.getTileCoordinates().y == getTileCoordinates().y + getDirection().getDy())) {
                if (npc.getState() != PersonState.STANDING) return null;

                return npc;
            }
        }

        return null;
    }

    /**
     * This method checks to see if the tile the player is facing has a clue hidden in it or not
     */
    private void checkForClue() {
        int x = getTileCoordinates().x + getDirection().getDx();
        int y = getTileCoordinates().y + getDirection().getDy();


        if (!this.getRoom().isHidingPlace(x, y)) {
            return;
        }

        Clue clueFound = getRoom().getClue(x, y);
        if (clueFound != null) {
            try {
                game.screenManager.navigationScreen.speechboxMngr.addSpeechBox(new SpeechBox("You found: " + clueFound.getDescription()), true);
            } catch (NullPointerException e) {
                System.out.println("WARNING: Adding speech box was not possible, likely that the speech box manager does not exist \n" +
                        "         The content of the speech box would be: You found: " + clueFound.getDescription());
            }

            canMove = false;
            this.collectedClues.add(clueFound);
            if (clueFound.isMurderWeapon()) {
                this.foundMurderWeapon = true;
                score += 500;
            }

            if (!clueFound.isRedHerring()) {
                usefulClues++;
            }

            // set all NPCs ignored to false
            try {
                for (NPC character : game.currentSnapshot.NPCs) {
                    character.ignored = false;
                }
            } catch (NullPointerException e) {
                System.out.println("WARNING: either no snapshot or NPCs in the snapshot exist");
            }

            score += 250;

            if (!Settings.MUTED) {
                Assets.SOUND.play(Settings.SFX_VOLUME);
            }
        }
    }

    /**
     * Checks for secret room location
     */
    private boolean checkForSecretRoom() {
        int x = getTileCoordinates().x + getDirection().getDx();
        int y = getTileCoordinates().y + getDirection().getDy();

        return this.getRoom().getName().equals("Main Foyer") && game.hiddenRoomLocation.x == x && game.hiddenRoomLocation.y == y;
    }

    /**
     * Assessment 4
     * <p>
     * This method checks to see if the tile the player is facing has a clue hidden in it or not
     *
     * @Author Lorem-Ipsum
     */
    private void checkForExtraScore() {
        int x = getTileCoordinates().x + getDirection().getDx();
        int y = getTileCoordinates().y + getDirection().getDy();

        if (this.getRoom().isExtraScoreTile(x, y)) {
            int extra = this.getRoom().extraScoreAmount();
            score += extra;

            game.currentSnapshot.player.canMove = false;

            if (extra == 0) {
                game.screenManager.navigationScreen.speechboxMngr.addSpeechBox(new SpeechBox("There appear to be no extra points here, the cash pile before you is fake!"));
            } else {
                SpeechBox speechBox = new SpeechBox("You gained " + extra + " extra points! Lucky you :D");
                speechBox.setTrigger(true);
                game.screenManager.navigationScreen.speechboxMngr.addSpeechBox(speechBox);
            }
            game.scoreObtained = true;
            if (!Settings.MUTED) {
                Assets.SOUND.play(Settings.SFX_VOLUME);
            }
        }
    }

    /**
     * This method returns whether or not the player is standing on a tile that initiates a Transition to another room
     *
     * @return (boolean) Whether the player is on a trigger tile or not
     */
    public boolean isOnTriggerTile() {
        return this.getRoom().isTriggerTile(this.tileCoordinates.x, this.tileCoordinates.y);
    }

    /**
     * Determines whether the player has enough information to accuse an NPC yet.
     *
     * @return True if the murder weapon has been found, at least 3 other useful (i.e. not
     * a red herring) clues have been found, and at least 5 questions have been asked, otherwise
     * false
     * @author JAAPAN
     */
    public boolean canAccuse() {
        return (foundMurderWeapon && usefulClues >= 3 && questionsAsked >= 5);
    }

    /**
     * This checks to see if you are on a room mat and facing the right way and if you are it initialises a room change
     *
     * @param dir the direction the player is to move into
     * @return boolean, true if there is a room change false otherwise
     * @author Lorem-Ipsum
     */
    private boolean roomChangeCheck(Direction dir) {
        if (this.isOnTriggerTile() && dir.toString().equals(getRoom().getMatRotation(this.tileCoordinates.x, this.tileCoordinates.y))) {
            setDirection(dir);
            game.screenManager.navigationScreen.initialiseRoomChange(this.getRoom().getTransitionData(this.getTileCoordinates().x, this.getTileCoordinates().y));
            return true;
        }
        return false;
    }

    /**
     * This changes the room of the player based on the transition data provided to it
     *
     * @author Lorem-Ipsum
     */
    public void moveRoom(Room.Transition transition) {

        for (NPC character : game.currentSnapshot.NPCs) {
            character.ignored = false;
        }

        this.setRoom(transition.getNewRoom());

        if (transition.newDirection != null) {
            this.setDirection(transition.newDirection);
            this.updateTextureRegion();
        }

        this.setTileCoordinates(transition.newTileCoordinates.x, transition.newTileCoordinates.y);

        game.screenManager.navigationScreen.updateTiledMapRenderer();
    }


    /*************************************************************************/
    /****************************** Set Methods ******************************/
    /*************************************************************************/

    /**
     * Changes the player's personality towards the given type, and
     * caps the personality between 0 and 100.
     *
     * @param change The personality type to move in the direction of
     * @author JAAPAN
     */
    public void changePersonality(Personality change) {
        switch (change) {
            case AGGRESSIVE:
                personalityLevel -= Math.ceil(10 * (personalityLevel / 100f));
                break;
            case NEUTRAL:
                if (personalityLevel < 50) {
                    personalityLevel += Math.ceil(10 * ((50 - personalityLevel) / 100f));
                } else {
                    personalityLevel -= Math.ceil(10 * ((personalityLevel - 50) / 100f));
                }
                break;
            case NICE:
                personalityLevel += Math.ceil(10 * ((100 - personalityLevel) / 100f));
                break;
        }

        if (personalityLevel < 0) {
            personalityLevel = 0;
        } else if (personalityLevel > 100) {
            personalityLevel = 100;
        }
    }

    /**
     * Changes the player's score by the specified amount, and caps it at 0.
     *
     * @param scoreToAdd The number of points to add to the score. Can be negative
     * @author JAAPAN
     */
    public void addToScore(int scoreToAdd) {
        score += scoreToAdd;
        if (score < 0) score = 0;
    }

    /**
     * Increases the gameDuration variable by the appropriate amount. Should be called
     * from the Screen.render() method of all screens that count as playing (i.e. not the
     * pause screen).
     *
     * @param delta The time difference
     * @author JAAPAN
     */
    public void addPlayTime(float delta) {
        gameDuration += delta;
    }

    /**
     * Increments the counter for questions asked. Should be called when a question receives
     * an actual response (i.e. not a non-response).
     *
     * @author JAAPAN
     */
    public void addQuestion() {
        questionsAsked++;
    }

    /**
     * Increments the counter for false accusations.
     *
     * @author JAAPAN
     */
    public void addFalseAccusation() {
        falseAccusations++;
    }

    /*************************************************************************/
    /****************************** Get Methods ******************************/
    /*************************************************************************/

    /**
     * Handles speech for a question about a clue.
     *
     * @param clue  The clue to be questioned about
     * @param style The style of questioning
     * @return The appropriate line of dialogue.
     */
    public String getSpeech(Clue clue, Personality style) {
        String key = clue.getName();

        return jsonData.get("Responses").get(key).getString(style.toString());
    }

    /**
     * @return The current personality of the player
     * @author JAAPAN
     */
    @Override
    public Personality getPersonality() {
        if (personalityLevel < 33) {
            return Personality.AGGRESSIVE;
        } else if (personalityLevel < 66) {
            return Personality.NEUTRAL;
        } else {
            return Personality.NICE;
        }
    }

    /**
     * This gets the players personality level; this similar to Personality but a integer representation
     *
     * @return Value between 0-100
     */
    public int getPersonalityLevel() {
        return this.personalityLevel;
    }

    /**
     * @return The number of points the player has earned so far this game
     * @author JAAPAN
     */
    public int getScore() {
        return score;
    }

    /**
     * Calculates a logarithmic time bonus. No points are awarded after approximately 45 minutes
     * (The game shouldn't take anywhere near that long to complete).
     *
     * @return The number of bonus points the player has earned
     * @author JAAPAN
     */
    public int getTimeBonus() {
        int penalty = (int) (Math.log(gameDuration / 20) * 1000);

        int bonus = 5000 - penalty;
        if (bonus < 0) bonus = 0;
        return bonus;
    }

    /**
     * @return The total score the player has achieved throughout the game
     * @author JAAPAN
     */
    public int getTotalScore() {
        return score + getTimeBonus();
    }

    /**
     * @return The duration of the game (excluding time paused) as a whole number of seconds
     * @author JAAPAN
     */
    public int getPlayTime() {
        return (int) gameDuration;
    }

    /**
     * @return The duration of the game (excluding time paused) formatted as HH:MM:SS. Caps time to
     * 99:59:59.
     * @author JAAPAN
     */
    public String getFormattedPlayTime() {
        int time = (int) gameDuration;
        int hours, minutes;
        hours = time / 3600;
        // Cap time if exceeded 100 hours
        if (hours > 99) {
            hours = 99;
            minutes = 59;
            time = 59;
        } else {
            time %= 3600;

            minutes = time / 60;
            time %= 60;
        }

        return String.format("%1$02d:%2$02d:%3$02d", hours, minutes, time);
    }

    /**
     * @return The number of red herrings the player has found
     * @author JAAPAN
     */
    public int getRedHerrings() {
        return collectedClues.size() - usefulClues;
    }

    /**
     * @return The number of questions the player has asked (excluding non-responses)
     * @author JAAPAN
     */
    public int getQuestions() {
        return questionsAsked;
    }

    /**
     * @return The number of false accusations made by the player during this game
     * @author JAAPAN
     */
    public int getFalseAccusations() {
        return falseAccusations;
    }
}
