package me.lihq.game.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import me.lihq.game.GameMain;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * The class which is responsible for the non-playable characters within the game that the player will meet.
 */
public class NPC extends AbstractPerson {
    /**
     * List of clues NPC has already been asked about. Used to ensure the player only gets points
     * for asking an NPC about a clue once.
     *
     * @author JAAPAN
     */
    public List<Clue> alreadyAskedClues = new ArrayList<>();
    /**
     * Used to track whether the NPC has been ignored, and thus won't respond to the player
     * until another clue has been found.
     *
     * @author JAAPAN
     */
    public boolean ignored = false;
    /**
     * Used to track whether the NPC has been accused, so they can ignore the player after a false accusation.
     *
     * @author JAAPAN
     */
    public boolean accused = false;
    /**
     * The motive string details why the NPC committed the murder.
     */
    private String motive = "";
    /**
     * Whether the NPC is the killer.
     */
    private boolean isKiller = false;
    /**
     * Whether the NPC is the victim.
     */
    private boolean isVictim = false;
    /**
     * This stores the players personality {@link me.lihq.game.people.AbstractPerson.Personality}
     */
    private Personality personality;

    /**
     * Define an NPC with location coordinates, room, spritesheet and name
     *
     * @param tileX       x coordinate of tile that the NPC will be initially rendered on.
     * @param tileY       y coordinate of tile that the NPC will be initially rendered on.
     * @param room        ID of room they are in
     * @param spriteSheet Spritesheet for this NPC
     */
    public NPC(GameMain game, String name, String spriteSheet, int tileX, int tileY, Room room, String jsonFile) {
        super(game, name, "people/NPCs/" + spriteSheet, tileX, tileY);  
        this.setRoom(room);

        importDialogue(jsonFile);
    }

    /**
     * This method is called once a game tick to randomise movement.
     */
    @Override
    public void update() {
        super.update();
        this.randomMove();
    }

    /**
     * Reads in the JSON file of the character.
     *
     * @param fileName The filename to read from
     */
    @Override
    public void importDialogue(String fileName) {
        jsonData = new JsonReader().parse(Gdx.files.internal("people/NPCs/" + fileName));
        this.personality = Personality.valueOf(jsonData.getString("personality"));
    }

    /**
     * Allow the NPC to move around their room.
     *
     * @param dir the direction person should move in
     */
    public void move(Direction dir) {
        if (this.state != PersonState.STANDING) {
            return;
        }

        if (!canMove) return;

        if (!getRoom().isWalkableTile(this.tileCoordinates.x + dir.getDx(), this.tileCoordinates.y + dir.getDy())) {
            setDirection(dir);
            return;
        }

        initialiseMove(dir);
    }

    /**
     * This method attempts to move the NPC in a random direction
     */
    private void randomMove() {
        if (getState() == PersonState.WALKING) return;

        if (random.nextDouble() > 0.01) {
            return;
        }

        Direction dir;

        Double dirRand = random.nextDouble();
        if (dirRand < 0.5) {
            dir = this.direction;
        } else if (dirRand < 0.62) {
            dir = Direction.NORTH;
        } else if (dirRand < 0.74) {
            dir = Direction.SOUTH;
        } else if (dirRand < 0.86) {
            dir = Direction.EAST;
        } else {
            dir = Direction.WEST;
        }

        move(dir);
    }

    /*************************************************************************/
    /****************************** Set Methods ******************************/
    /*************************************************************************/

    /**
     * Sets the NPC as the killer for this game.
     * <p>
     * It first checks they aren't the victim.
     * </p>
     *
     * @return Whether it successfully set the NPC to the killer or not
     */
    public boolean setKiller() {
        if (isVictim) return false;

        isKiller = true;
        System.out.println(getName() + " is the killer");
        return true;
    }

    /**
     * Sets the NPC as the victim for this game.
     * <p>
     * It first checks the NPC isn't the killer.
     * </p>
     *
     * @return Whether it successfully set the NPC to the victim or not
     */
    public boolean setVictim() {
        if (isKiller) return false;

        isVictim = true;
        System.out.println(getName() + " is the victim");
        return true;
    }

    /**
     * Handles speech for a question about a clue. If the style of question matches both the
     * player's personality and the NPC's personality, generates an improved response. If it
     * matches just one of the player's or NPC's personalities, generates a normal response.
     * If it matches neither, generates a non-response.
     *
     * @param clue   The clue to be questioned about
     * @param style  The style of questioning
     * @param player The personality type of the player
     * @return The appropriate line of dialogue.
     * @author JAAPAN
     */
    public String getSpeech(Clue clue, Personality style, Personality player) {
        if (style == personality && player == style) {
            // Increment the player's question counter
            game.player.addQuestion();

            String response = getSpeech(clue);

            // If this NPC is the killer, point the player in the direction of a
            // random NPC. Otherwise, point them towards the killer. As this is an improved
            // response, whether the clue is a red herring or not is unimportant.
            if (isKiller) {
                String name = game.NPCs.get(random.nextInt(game.NPCs.size())).getName();
                while (name == getName()) {
                    name = game.NPCs.get(random.nextInt(game.NPCs.size())).getName();
                }
                // Replace the NPC tag in the string with the name of the NPC
                response = response.replace("%NPC", name);
            } else {
                // Replace the NPC tag in the string with the name of the NPC
                response = response.replace("%NPC", game.killer.getName());
                // Add the room of the killer to the response
                String room = game.killer.getRoom().getName();
                if (room != "Outside Ron Cooke Hub") {
                    response += " Last I saw them, they were in the " + room + ".";
                } else {
                    response += " Last I saw them, they were outside.";
                }
            }

            return response;
        } else if (style == personality || style == player) {
            // Increment the player's question counter
            game.player.addQuestion();

            String response = getSpeech(clue);

            // If this NPC is the killer, or the clue is a red herring, point the player
            // in the direction of a random NPC. Otherwise, point them towards the killer
            if (isKiller || clue.isRedHerring()) {
                String name = game.NPCs.get(random.nextInt(game.NPCs.size())).getName();
                while (name == getName() || name == game.killer.getName()) {
                    name = game.NPCs.get(random.nextInt(game.NPCs.size())).getName();
                }
                // Replace the NPC tag in the string with the name of the NPC
                response = response.replace("%NPC", name);
            } else {
                // Replace the NPC tag in the string with the name of the NPC
                response = response.replace("%NPC", game.killer.getName());
            }

            return response;
        } else {
            return getSpeech("");
        }
    }

    /*************************************************************************/
    /****************************** Get Methods ******************************/
    /*************************************************************************/

    /**
     * @return The NPC's personality {@link me.lihq.game.people.AbstractPerson.Personality}
     */
    @Override
    public Personality getPersonality() {
        return personality;
    }

    /**
     * @return The {@link #motive} string for this object.
     */
    public String getMotive() {
        return motive;
    }

    /**
     * Reads and sets the NPC's motive for killing the victim from the JSON file.
     *
     * @param victim The victim of the heinous crime.
     * @return This object once the motive has been set.
     * @author JAAPAN
     */
    public NPC setMotive(NPC victim) {
        try {
            motive = jsonData.get("motives").getString(victim.getName());
        } catch (Exception e) {
            motive = "Error: Motive not working";
        }
        System.out.println(motive);
        return this;
    }

    /**
     * Getter for isKiller.
     *
     * @return (boolean) Return a value of isKiller for this object. {@link #isKiller}
     */
    public boolean isKiller() {
        return isKiller;
    }

    /**
     * Getter for isVictim
     *
     * @return (boolean) Returns the value of isVictim for this object {@link #isVictim}
     */
    public boolean isVictim() {
        return isVictim;
    }
}
