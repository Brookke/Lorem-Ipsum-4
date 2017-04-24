package me.lihq.game.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.people.AbstractPerson.Direction;
import me.lihq.game.people.NPC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class defines a room which is a game representation of a real world room in the Ron Cooke Hub.
 */
public class Room {


    /**
     * The secret room spot, this is selected from one of the bookcases
     */
    public Vector2Int hiddenRoomLocation;

    /**
     * This list stores the coordinates of all hideable slots in this room
     * <p>
     * Hideable slots are tiles that the clues can be hidden in
     */
    public List<Vector2Int> hidingSpots = null;
    /**
     * This list stores the coordinates of all secret bookcases in this room
     * <p>
     */
    private List<Vector2Int> bookcaseSpots = null;

    /**
     * @Lorem Ipsum
     * Assessment 4
     * This list stores the coordinates of all slots that provide extra points in this room
     *
     * @author Lorem-Ipsum
     */
    public List<Vector2Int> scoreSpots = null;


    /**
     * This is a reference to the main game class
     * <p>
     * The whole class now has reference to the main game. Removing all uses of `GameMain.me`
     *
     * @author Lorem-Ipsum
     */
    private GameMain game;
    /**
     * This stores the name of the room.
     * It is displayed on the tag when they enter the room
     */
    private String name;
    /**
     * The integer ID of the room
     */
    private int ID;
    /**
     * The string that points to the tmx map file for this room.
     */
    private String mapFile;
    /**
     * This is a list of the clues in the room.
     */
    private List<Clue> cluesInRoom = new ArrayList<>();
    /**
     * This stores whether or not the room is the room where the murder happened
     */
    private boolean murderRoom = false;
    /**
     * This stores the TMX map loaded from the String mapFile {@link #mapFile}
     */
    private TiledMap map;
    /**
     * This stores the coordinates of the map in a 2x2 array. If a player/NPC attempts to move to a location, it locks
     * the location before it moves, to avoid anything else moving to it.
     */
    private boolean[][] lockedTiles = null;
    /**
     * Room transitions stored as custom Transition object. Defines where the transition is from and where it goes to
     */
    private List<Transition> roomTransitions = new ArrayList<Transition>();
    private float animationStateTime = 0f;

    /**
     * Constructor that builds a Room object from the given parameters
     *
     * @param id      The integer ID of the room
     * @param mapFile The String that points to the tmx map file.
     * @param name    The name of the room
     */
    public Room(GameMain game, int id, String mapFile, String name) {
        this.game = game;
        this.ID = id;
        this.mapFile = mapFile;
        this.name = name;

        this.map = new TmxMapLoader().load("maps/" + this.mapFile);

        int roomWidth = ((TiledMapTileLayer) map.getLayers().get(0)).getWidth();
        int roomHeight = ((TiledMapTileLayer) map.getLayers().get(0)).getHeight();

        this.lockedTiles = new boolean[roomWidth][roomHeight];
        for (int w = 0; w < roomWidth; w++) {
            for (int h = 0; h < roomHeight; h++) {
                this.lockedTiles[w][h] = false;
            }
        }


        Collections.shuffle(getBookcaseSpots());
        if (bookcaseSpots.size() > 0) {
            hiddenRoomLocation = bookcaseSpots.get(0);
        }
        hidingSpots = getHidingSpots();

        scoreSpots = getExtraScoreSpots();
    }

    /**
     * This method checks equality between the Room and another object
     *
     * @param obj The object to be checked against
     * @return (boolean) Returns true if the obj is of type Room and has the exact same ID. {@link #ID}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Room) {
            Room r = (Room) obj;
            return r.getID() == this.getID();
        }

        return false;
    }

    /**
     * Returns True if it's the room the murder took place in
     *
     * @return (boolean) the value of {@link #murderRoom}
     */
    public boolean isMurderRoom() {
        return murderRoom;
    }

    /**
     * Sets the room to be the murder room
     */
    public void setMurderRoom() {
        this.murderRoom = true;
        System.out.println("Room " + getID() + " is the murder room");
    }

    /**
     * Returns the integer ID of the room
     *
     * @return (int) the value of {@link #ID}
     */
    public int getID() {
        return this.ID;
    }

    /**
     * This returns the String name of the room
     *
     * @return (String) the value of {@link #name}
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method returns the clues currently stored in the room
     *
     * @return List<Clue> the clues in the room
     * @author Lorem-Ipsum
     */
    public List<Clue> getCluesInRoom() {
        return cluesInRoom;
    }

    /**
     * Adds a clue to the room.
     *
     * @param newClue The clue to add to the room
     */
    public void addClue(Clue newClue) {
        System.out.println("Added Clue " + newClue.getName() + " at location " + newClue.getPosition() + " in room \"" + getName() + "\"");

        if (!cluesInRoom.contains(newClue)) {
            cluesInRoom.add(newClue);
        }
    }

    /**
     * This method takes a location parameter and checks it for a clue, if a clue is found it is removed from the map and return
     *
     * @param x The x coordinate the player is at
     * @param y The y coordinate the player is at
     * @return (Clue) returns null if there is no clue at coordinate x,y and returns the clue itself otherwise
     */
    public Clue getClue(int x, int y) {
        //Apply direction change
        Clue out = null;
        //Check for a clue at that coordinate
        for (Clue c : cluesInRoom) {
            if (c.getPosition().x == x && c.getPosition().y == y) {
                out = c;
            }
        }
        if (out != null) {
            this.cluesInRoom.remove(out);
        }

        return out;
    }

    /**
     * This method draws the clues that are in the clue with the asset CLUE_GLINT
     *
     * @param delta The time passed since the last draw. Used for the animation
     * @param batch The batch to draw the sprites to
     */
    public void drawClues(float delta, Batch batch) {
        animationStateTime += delta;

        for (Clue c : cluesInRoom) {
            TextureRegion currentFrame = Assets.CLUE_GLINT.getKeyFrame(animationStateTime, true);
            batch.draw(currentFrame, c.getTileX() * Settings.TILE_SIZE, c.getTileY() * Settings.TILE_SIZE);
        }
    }

    /**
     * This method checks whether the tile at x, y is a tile you can hide a clue
     * in
     *
     * @param x The x coordinate to check
     * @param y The y coordinate to check
     * @return (boolean) whether the tile is a hideable tile.
     */
    public boolean isHidingPlace(int x, int y) {
        return hidingSpots.contains(new Vector2Int(x, y));
    }

    /**
     * Assessment 4
     * <p>
     * This method checks whether the tile at x, y is a tile that gives you extra points
     * in
     *
     * @param x The x coordinate to check
     * @param y The y coordinate to check
     * @return (boolean) whether the tile is an extraScore tile.
     * @Author Lorem-Ipsum
     */
    public boolean isExtraScoreTile(int x, int y) {
        return scoreSpots.contains(new Vector2Int(x, y));
    }

    /**
     * This method locks the specified coordinates so no other people object can move to it
     *
     * @param x The x coordinate to lock
     * @param y The y coordinate to lock
     */
    public void lockCoordinate(int x, int y) {
        this.lockedTiles[x][y] = true;
    }

    /**
     * This method unlocks the specified coordinates so other people object can move to it
     *
     * @param x The x coordinate to unlock
     * @param y The y coordinate to unlock
     */
    public void unlockCoordinate(int x, int y) {
        this.lockedTiles[x][y] = false;
    }

    /**
     * This method takes a current X and Y coordinate and checks through all the layers on the map to see if any tile IS NOT
     * movable. If any tile IS NOT movable, it returns false.
     *
     * @param x The x coordinate to check
     * @param y The y coordinate to check
     * @return (boolean) whether or not that tile can be walked on.
     */
    public boolean isWalkableTile(int x, int y) {
        //reduced by one because the last layer is to be displayed over the top of the player and therefore is ignored.
        int amountOfLayers = map.getLayers().getCount() - 1;
        int emptyCellCount = 0; //The amount of empty cells on the map in the location x and y.

        for (int currentLayer = 0; currentLayer < amountOfLayers; currentLayer++) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer) map.getLayers().get(currentLayer);

            if (tiledLayer.getName().equals("Blood") && !isMurderRoom()) {
                //Don't check the layer as the blood splat isn't there
                emptyCellCount++;
                continue;
            }

            if (tiledLayer.getCell(x, y) == null) {
                emptyCellCount++; //for every empty cell increase the emptyCellCount by 1
                continue;
            }

            if (!tiledLayer.getCell(x, y).getTile().getProperties().containsKey("walkable")) {
                continue;
            }

            if (tiledLayer.getCell(x, y).getTile().getProperties().get("walkable").toString().equals("false")) {
                return false;
            }
        }

        /*
        Check to see if the number of empty layer cells matches the number of layers,
        if it does the this must be an empty area of the map that is not walkable
         */
        if (emptyCellCount == amountOfLayers) {
            return false;
        }

        try {
             /*
            Check to see if the player is standing in the target destination
            */
            if (game.currentSnapshot.player.getTileCoordinates().x == x && game.currentSnapshot.player.getTileCoordinates().y == y) {
                return false;
            }

             /*
             Check to see if any NPCs are standing in the target destination
             */
            for (Sprite sprite : game.screenManager.navigationScreen.getNPCs()) {
                NPC npc = (NPC) sprite;

                if (npc.getTileCoordinates().x == x && npc.getTileCoordinates().y == y) {
                    return false;
                }
            }
        } catch (Exception e) {

        }

        /*
        Check to see if any people object has locked the target destination for them to move to
         */
        try {
            if (this.lockedTiles[x][y] == true) {
                return false;
            }
        } catch (Exception e) {
        }

        return true;
    }

    /**
     * This method checks ALL layers for the tile at x, y to see if it is a trigger tile.
     * If any of them are true, it returns true
     *
     * @param x The x coordinate to check
     * @param y The y coordinate to check
     * @return (boolean) whether or not the tile is a trigger tile.
     */
    public boolean isTriggerTile(int x, int y) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);

        if (layer.getCell(x, y) == null) return false;

        int amountOfLayers = map.getLayers().getCount();

        for (int currentLayer = 0; currentLayer < amountOfLayers; currentLayer++) {
            TiledMapTileLayer tl = (TiledMapTileLayer) map.getLayers().get(currentLayer);

            if (tl.getCell(x, y) == null) {
                continue;
            }

            if (!tl.getCell(x, y).getTile().getProperties().containsKey("trigger")) {
                continue;
            }

            if (tl.getCell(x, y).getTile().getProperties().get("trigger").toString().equals("true")) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method gets the rotation that the map is that they are standing on.
     * If they aren't on a mat, it returns null
     *
     * @param x The x coordinate to check
     * @param y The y coordinate to  check
     * @return a String representing the direction they are facing
     */
    public String getMatRotation(int x, int y) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Doors");

        if (layer.getCell(x, y) == null) return null;

        return (String) layer.getCell(x, y).getTile().getProperties().get("dir");
    }

    /**
     * This returns the tiledMap for this room
     *
     * @return - (TiledMap) the map relating to this room.
     */
    public TiledMap getTiledMap() {
        return this.map;
    }

    /**
     * This adds a transition to the map.
     *
     * @param t The transition to be added
     * @return (Room) itself
     */
    public Room addTransition(Transition t) {
        roomTransitions.add(t);
        return this;
    }

    /**
     * This will check the map for any potential hiding locations, and returns them as a list of coordinates
     *
     * @return (List<Vector2Int>) list of coordinates of the hideable tiles
     */
    private List<Vector2Int> getBookcaseSpots() {
        if (bookcaseSpots != null) return bookcaseSpots;

        List<Vector2Int> bookcaseSpots = new ArrayList<>();

        int roomWidth = ((TiledMapTileLayer) this.getTiledMap().getLayers().get(0)).getWidth();
        int roomHeight = ((TiledMapTileLayer) this.getTiledMap().getLayers().get(0)).getHeight();

        for (int x = 0; x < roomWidth; x++) {
            for (int y = 0; y < roomHeight; y++) {
                for (MapLayer layer : this.getTiledMap().getLayers()) {
                    TiledMapTileLayer thisLayer = (TiledMapTileLayer) layer;
                    TiledMapTileLayer.Cell cellInTile = thisLayer.getCell(x, y);

                    if (cellInTile == null) continue;

                    if (!cellInTile.getTile().getProperties().containsKey("secretRoom")) continue;

                    if (cellInTile.getTile().getProperties().get("secretRoom").toString().equals("true")) {
                        bookcaseSpots.add(new Vector2Int(x, y));
                        break;
                    }
                }
            }
        }

        this.bookcaseSpots = bookcaseSpots;

        return this.bookcaseSpots;
    }

    /**
     * This will check the map for any potential hiding locations, and returns them as a list of coordinates
     *
     * @return (List<Vector2Int>) list of coordinates of the hideable tiles
     */
    public List<Vector2Int> getHidingSpots() {
        if (hidingSpots != null) return hidingSpots;

        List<Vector2Int> hidingSpots = new ArrayList<>();

        int roomWidth = ((TiledMapTileLayer) this.getTiledMap().getLayers().get(0)).getWidth();
        int roomHeight = ((TiledMapTileLayer) this.getTiledMap().getLayers().get(0)).getHeight();

        for (int x = 0; x < roomWidth; x++) {
            for (int y = 0; y < roomHeight; y++) {
                for (MapLayer layer : this.getTiledMap().getLayers()) {
                    TiledMapTileLayer thisLayer = (TiledMapTileLayer) layer;
                    TiledMapTileLayer.Cell cellInTile = thisLayer.getCell(x, y);

                    if (cellInTile == null) continue;

                    if (!cellInTile.getTile().getProperties().containsKey("hidingSpot")) continue;

                    if (cellInTile.getTile().getProperties().get("hidingSpot").toString().equals("true")
                            && !new Vector2Int(x, y).equals(hiddenRoomLocation)) {
                        hidingSpots.add(new Vector2Int(x, y));
                        break;
                    }
                }
            }
        }

        this.hidingSpots = hidingSpots;

        return this.hidingSpots;
    }

    /**
     * This will check the map for any potential extraScore locations, and returns them as a list of coordinates
     *
     * @return (List<Vector2Int>) list of coordinates of the extraScore tiles
     * @author Lorem-Ipsum
     */
    public List<Vector2Int> getExtraScoreSpots() {
        if (scoreSpots != null) return scoreSpots;

        List<Vector2Int> scoreSpots = new ArrayList<>();

        int roomWidth = ((TiledMapTileLayer) this.getTiledMap().getLayers().get(0)).getWidth();
        int roomHeight = ((TiledMapTileLayer) this.getTiledMap().getLayers().get(0)).getHeight();

        for (int x = 0; x < roomWidth; x++) {
            for (int y = 0; y < roomHeight; y++) {
                for (MapLayer layer : this.getTiledMap().getLayers()) {
                    TiledMapTileLayer thisLayer = (TiledMapTileLayer) layer;
                    TiledMapTileLayer.Cell cellInTile = thisLayer.getCell(x, y);

                    if (cellInTile == null) continue;

                    if (!cellInTile.getTile().getProperties().containsKey("extraScore")) continue;

                    if (cellInTile.getTile().getProperties().get("extraScore").toString().equals("true")) {
                        scoreSpots.add(new Vector2Int(x, y));
                        break;
                    }
                }
            }
        }
        this.scoreSpots = scoreSpots;

        return this.scoreSpots;
    }

    /**
     * Assessment 4
     * <p>
     * This method determines the extra score gained when the player finds an item that provides extra score
     * The score is determined through a gaussian distribution
     * The mean score is 500 and the standard deviation is 200.
     * <p>
     * If the player has already obtained extra score once then it makes the player unable to obtain more extra score
     *
     * @Author Lorem-Ipsum
     */
    public int extraScoreAmount() {
        if (!game.scoreObtained) {
            Random rand = new Random();
            int extraScore = rand.nextInt(400) + 300;
            return extraScore;
        } else {
            return 0;
        }
    }

    /**
     * This gets a random possible location to hide a clue in
     *
     * @return (Vector2Int) Coordinates of the tile where the clue is to be hidden, null if there are none available
     */
    public Vector2Int getRandHidingSpot() {
        if (!this.getHidingSpots().isEmpty()) {
            List<Vector2Int> potentialHidingSpots = getHidingSpots();
            Collections.shuffle(potentialHidingSpots);

            return potentialHidingSpots.get(0);
        } else {
            return null;
        }
    }

    /**
     * This method will get the transition data (if available)
     * for the associated door mat in this room at the location x y.
     *
     * @param x The current x coordinate in the room (in terms of tiles not pixels)
     * @param y The current y coordinate in the room (in terms of tiles not pixels)
     * @return (Transition) a Transition data type. Which stores the relevant information. null if there is no transition at x, y
     */
    public Transition getTransitionData(int x, int y) {
        return hasTransition(new Vector2Int(x, y));
    }

    /**
     * This method checks through all the transitions of this room and if one exists where the FROM property is equal to the parameter 'v' then
     * it returns that Transition, else, it returns null
     *
     * @param v The vector containing the FROM coordinates
     * @return (Transition) nullable Transition - the transition if it exists, else, null
     */
    private Transition hasTransition(Vector2Int v) {
        for (Transition l : roomTransitions) {
            if (l.from.equals(v)) {
                return (l);
            }
        }

        return null;
    }

    /**
     * This method returns a random location in the room that is walkable
     *
     * @return (Vector2Int) the random walkable tile generated.
     */
    public Vector2Int getRandomLocation() {
        int roomWidth = ((TiledMapTileLayer) getTiledMap().getLayers().get(0)).getWidth();
        int roomHeight = ((TiledMapTileLayer) getTiledMap().getLayers().get(0)).getHeight();

        List<Vector2Int> possibleLocations = new ArrayList<Vector2Int>();

        for (int w = 0; w < roomWidth; w++) {
            for (int h = 0; h < roomHeight; h++) {
                if (isWalkableTile(w, h)) {
                    possibleLocations.add(new Vector2Int(w, h));
                }
            }
        }

        Collections.shuffle(possibleLocations);

        return possibleLocations.get(0);
    }

    /**
     * This object stores data the links the rooms together
     */
    public static class Transition {
        /**
         * This is the coordinates that starts the transition
         */
        public Vector2Int from = new Vector2Int(0, 0);

        /**
         * The direction the player should face when they enter the room
         */
        public Direction newDirection = null;

        /**
         * The entry point to the room in terms of tiles
         */
        public Vector2Int newTileCoordinates = new Vector2Int(0, 0);

        /**
         * The new room to transition to
         */
        private Room newRoom;

        /**
         * Constructor
         */
        public Transition() {
        }

        /**
         * This method takes the parameters and sets the values of the relevant properties
         *
         * @param room               The room that the transition takes you to
         * @param newTileCoordinateX The x coordinate that the transition takes you to
         * @param newTileCoordinateY The y coordinates that the transition takes you to
         * @param newDirection       The direction that you will face after the transition
         * @return (Transition) this
         */
        public Transition setTo(Room room, int newTileCoordinateX, int newTileCoordinateY, Direction newDirection) {
            this.newRoom = room;
            this.newTileCoordinates = new Vector2Int(newTileCoordinateX, newTileCoordinateY);
            this.newDirection = newDirection;
            return this;
        }

        /**
         * This method takes the parameters and sets the values of the relevant properties
         *
         * @param oldTiledCoordinateX The x coordinate that the transition starts from
         * @param oldTiledCoordinateY The y coordinate that the transition starts from
         * @return (Transition) this
         */
        public Transition setFrom(int oldTiledCoordinateX, int oldTiledCoordinateY) {
            this.from = new Vector2Int(oldTiledCoordinateX, oldTiledCoordinateY);
            return this;
        }

        /**
         * getter for the value of newRoom {@link #newRoom}
         *
         * @return (Room) value of newRoom
         */
        public Room getNewRoom() {
            return newRoom;
        }
    }
}


