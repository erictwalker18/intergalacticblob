package walkere.carleton.edu;

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
    private int position;
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

}