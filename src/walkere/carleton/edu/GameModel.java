package walkere.carleton.edu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/12/2014.
 */

/**
 * GameModel.java
 *
 * Model class that holds all the information that the scene is built from.
 */
public class GameModel {
    //Instance Variables
    private ArrayList<SpaceObject> spaceObjects;
    private Blob avatar;
    private int avatarPosition;
    private int score;
    private boolean isOver;

    /**
     * Default constructor. Instantiates the avatar Blob and the list spaceObjects
     * which holds the various spaceJunks in it. Also instantiates various instance variables
     * that are used for keeping track of the score.
     */
    public GameModel() {
        //These aren't the implementations you're looking for...
        //... Move along.
        //spaceObjects = new ArrayList<SpaceObject>();
        //position = 0;
        //score = 0;
        //score = new Label(score);
    }

    /**
     * Updates all the SpaceObjects and the score. This also checks if the blob
     * hit any of the horrible spaceJunk obstacles.
     */
    public void step() {
    }

    /**
     * Adds a spaceJunk object at the specified location
     * @param x the x location for the spaceJunk
     * @param y the y location for the spaceJunk
     */
    public void addSpaceJunk(double x, double y) {

    }

    /**
     * Gets the score of the game
     * @return the current score of the game
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Gets the spaceObjects stored in the model (for testing purposes)
     * @return the spaceObjects arraylist associated with this instance of GameModel
     */
    public ArrayList<SpaceObject> getSpaceObjects() {
        return this.spaceObjects;
    }

    /**
     *
     * @return the avatar to get its position.
     */
    public Blob getAvatar() {
        return this.avatar;
    }

    /**
     * Tells the controller (or a test) whether or not the game is over.
     * @return isOver, a boolean variable.
     */
    public boolean isOver() {
        return this.isOver;
    }
}