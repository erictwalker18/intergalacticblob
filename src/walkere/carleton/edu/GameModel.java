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
    private int avatarPosition;
    private int score;
    private HashMap<Integer, String> highscores;

    /**
     * Default constructor. Instantiates the list spaceObjects which holds the
     * various walls and actors in it. Also instantiates various instance variables
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
     * Updates all the SpaceObjects and the score.
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
}