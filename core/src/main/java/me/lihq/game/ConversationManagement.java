package me.lihq.game;

import me.lihq.game.models.Clue;
import me.lihq.game.people.AbstractPerson.Personality;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Player;
import me.lihq.game.screen.WinScreen;
import me.lihq.game.screen.elements.SpeechBox;
import me.lihq.game.screen.elements.SpeechBoxButton;

import java.util.ArrayList;

/**
 * This class controls conversation flow between Player and NPCs.
 */
public class ConversationManagement {
    /**
     * This is a reference to the main game
     */
    private GameMain game;

    /**
     * The player that will be starting the conversation.
     */
    private Player player;

    /**
     * The manager for speechboxes, controls the flow of speechboxes.
     */
    private SpeechboxManager speechboxMngr;

    /**
     * Stores the current NPC that is being questioned.
     */
    private NPC tempNPC;

    /**
     * This stores the position of the clue in the players list for use in the questioning.
     */
    private int tempCluePos;

    /**
     * This stores the style of questioning for how the player wants to ask the question.
     */
    private Personality tempQuestionStyle;

    /**
     * Indicates whether the last conversation is finished or ongoing.
     *
     * @author JAAPAN
     */
    private boolean finished = false;

    /**
     * Indicates whether the player has correctly accused the killer.
     *
     * @author JAAPAN
     */
    private boolean won = false;

    /**
     * This constructs a conversation manager
     *
     * @param player           the player that will initiate the conversation
     * @param speechboxManager the speechbox manager that is in charge of displaying the conversation
     */
    public ConversationManagement(GameMain game, Player player, SpeechboxManager speechboxManager) {
        this.game = game;
        this.player = player;
        this.speechboxMngr = speechboxManager;
    }

    /**
     * This method starts a conversation with the specified NPC
     *
     * @param npc The NPC to have a conversation with
     */
    public void startConversation(NPC npc) {
        this.tempCluePos = -1;
        this.tempQuestionStyle = null;
        this.tempNPC = npc;

        npc.setDirection(player.getDirection().getOpposite());
        npc.canMove = false;
        player.canMove = false;
        player.inConversation = true;

        /******************** Added by team JAAPAN ********************/
        if (tempNPC.accused) {
            // If the NPC has been falsely accused in the past, they refuse to respond to the player
            speechboxMngr.addSpeechBox(new SpeechBox(this.player.getName(), this.player.getSpeech("Introduction"), 5));
            speechboxMngr.addSpeechBox(new SpeechBox(tempNPC.getName(), tempNPC.getSpeech("Falsely Accused"), 5));
            finished = true;
        } else if (tempNPC.ignored) {
            // If the NPC has been ignored, they also refuse to respond to the player, but this can change
            speechboxMngr.addSpeechBox(new SpeechBox(this.player.getName(), this.player.getSpeech("Introduction"), 5));
            speechboxMngr.addSpeechBox(new SpeechBox(tempNPC.getName(), tempNPC.getSpeech("Ignored Return"), 5));
            finished = true;
        } else {
            // Otherwise, begin a conversation with the NPC
            speechboxMngr.addSpeechBox(new SpeechBox(this.player.getName(), this.player.getSpeech("Introduction"), 5));
            speechboxMngr.addSpeechBox(new SpeechBox(this.tempNPC.getName(), this.tempNPC.getSpeech("Introduction"), 5));
            queryQuestionType();
        }
        /**************************** End *****************************/
    }

    /**
     * This constructs the speech box that finds out what question the player wishes to ask the NPC
     */
    private void queryQuestionType() {

        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (result) -> handleResponse(QuestionStage.TYPE, result);

        if (!player.collectedClues.isEmpty()) {
            buttons.add(new SpeechBoxButton("Question?", 0, eventHandler));
            buttons.add(new SpeechBoxButton("Ignore", 2, eventHandler));
        }
        /******************** Added by team JAAPAN ********************/
        // If the player can accuse the NPC, add the accuse button
        if (player.canAccuse()) {
            buttons.add(new SpeechBoxButton("Accuse?", 1, eventHandler));
        }
        /**************************** End *****************************/

        if (buttons.size() > 0) {
            speechboxMngr.addSpeechBox(new SpeechBox("What do you want to do?", buttons, -1));
        } else {
            speechboxMngr.addSpeechBox(new SpeechBox("You need to find some clues before you question a suspect", 5));
            finished = true;
        }
    }

    /**
     * This constructs the speechbox that asks the player how they wish to ask the question
     */
    private void queryQuestionStyle() {
        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (result) -> handleResponse(QuestionStage.STYLE, result);

        buttons.add(new SpeechBoxButton("Nicely", 0, eventHandler));
        buttons.add(new SpeechBoxButton("Neutrally", 1, eventHandler));
        buttons.add(new SpeechBoxButton("Aggressively", 2, eventHandler));
        speechboxMngr.addSpeechBox(new SpeechBox("How do you want to ask the question?", buttons, -1));
    }

    /**
     * This constructs the speechbox that asks the player what clue they wish to ask about
     */
    private void queryWhichClue() {
        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (result) -> {
            handleResponse(QuestionStage.CLUE, result);
        };

        int i = 0;
        for (Clue c : this.player.collectedClues) {
            buttons.add(new SpeechBoxButton(c.getName(), i, eventHandler));
            i++;
        }

        speechboxMngr.addSpeechBox(new SpeechBox("What clue do you want to ask about?", buttons, -1));
    }

    /**
     * This method initialises a questioning user interface
     */
    private void questionNPC() {
        /******************** Added by team JAAPAN ********************/
        // Change the player's personality towards the style of questioning they chose
        player.changePersonality(tempQuestionStyle);
        /**************************** End *****************************/
        speechboxMngr.addSpeechBox(new SpeechBox(player.getName(), player.getSpeech(player.collectedClues.get(tempCluePos), tempQuestionStyle), 5));
        speechboxMngr.addSpeechBox(new SpeechBox(tempNPC.getName(), tempNPC.getSpeech(player.collectedClues.get(tempCluePos), tempQuestionStyle, player.getPersonality()), 5));
        finished = true;
    }

    /**
     * Initialises an accusing user interface.
     *
     * @author JAAPAN
     */
    private void accuseNPC() {
        // Display the accusation dialogue
        speechboxMngr.addSpeechBox(new SpeechBox(player.getName(), player.getSpeech("Accuse"), 5));
        if (this.tempNPC.isKiller()) {
            // If the NPC is the killer, respond with the motive
            speechboxMngr.addSpeechBox(new SpeechBox(tempNPC.getName(), tempNPC.getMotive(), 5));
            player.addToScore(1000);
            // Set the won flag, so the game ends
            won = true;
        } else {
            // Otherwise, respond with falsely accused dialogue and set the NPC's accused flag so they
            // don't respond to the player anymore
            speechboxMngr.addSpeechBox(new SpeechBox(tempNPC.getName(), tempNPC.getSpeech("Falsely Accused"), 5));
            this.tempNPC.accused = true;
            player.addToScore(-2000);
            // Increment false accusation counter; used for endgame stats
            player.addFalseAccusation();
        }
        finished = true;
    }

    /**
     * Initialises the ignore user interface.
     *
     * @author JAAPAN
     */
    private void ignoreNPC() {
        speechboxMngr.addSpeechBox(new SpeechBox(tempNPC.getName(), tempNPC.getSpeech("Ignored Initial"), 5));
        // Set the NPC's ignored flag, so they don't respond until another clue has been found
        this.tempNPC.ignored = true;
        finished = true;
    }

    /**
     * Called continuously from NavigationScreen.update(). Resets variables so the player can move
     * and normal gameplay can resume, but only if the conversation is over and all speechboxes are
     * closed. If the game has been won, opens the WinScreen.
     *
     * @author JAAPAN
     */
    public void finishConversation() {
        // Checks the player is in a conversation, and it is finished
        if (!this.speechboxMngr.isEmpty() || !this.player.inConversation || !this.finished)
            return;

        this.tempNPC.canMove = true;
        this.player.canMove = true;
        this.player.inConversation = false;
        this.finished = false;

        // End the game, and show the winning screen
        if (won) {
            game.setScreen(new WinScreen(game));
            won = false;
        }
    }

    /**
     * This method is called to handle a users input
     *
     * @param stage  The stage of the questioning process that they are currently at
     * @param option The option chosen by the user
     */
    private void handleResponse(QuestionStage stage, int option) {
        speechboxMngr.removeCurrentSpeechBox();

        switch (stage) {
            case TYPE:
                if (option == 0) {
                    queryQuestionStyle();
                } else if (option == 1) {
                    accuseNPC();
                    /******************** Added by team JAAPAN ********************/
                } else if (option == 2) {
                    ignoreNPC();
                }
                /**************************** End *****************************/
                break;

            case STYLE:
                this.tempQuestionStyle = convertToQuestionStyle(option);
                queryWhichClue();
                break;

            case CLUE:
                this.tempCluePos = option;
                questionNPC();
                /******************** Added by team JAAPAN ********************/
                // Check the player hasn't already asked this NPC about this clue, and add points
                if (!tempNPC.alreadyAskedClues.contains(player.collectedClues.get(tempCluePos))) {
                    player.addToScore(100);
                    tempNPC.alreadyAskedClues.add(player.collectedClues.get(tempCluePos));
                }
                /**************************** End *****************************/
                break;
        }

    }

    /**
     * Takes an int and returns a personality style
     *
     * @param style 0 = Nice
     *              1 = Neutral
     *              2 = AGGRESSIVE
     *              default is Neutral
     * @return
     */
    private Personality convertToQuestionStyle(int style) {
        switch (style) {
            case 0:
                return Personality.NICE;
            case 1:
                return Personality.NEUTRAL;
            case 2:
                return Personality.AGGRESSIVE;
            default:
                //defaults to Neutral
                return Personality.NEUTRAL;
        }
    }

    /**
     * This is the enumeration for the different stages of questioning the NPC
     */
    public enum QuestionStage {

        /**
         * This stage indicates that the player has been asked what type of question they have asked
         * e.g. question or accuse
         */
        TYPE,

        /**
         * Thus stage means that the player has been asked the how they want to ask the question
         * e.g. nice, neutral or harsh
         */
        STYLE,

        /**
         * This stage indicates that the player has been asked what clue they want to ask about.
         */
        CLUE
    }

}
