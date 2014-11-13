package walkere.carleton.edu;

import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/12/2014.
 */

public class Model {
    //Instance Variables
    private ArrayList<SpaceObject> spaceObjects;
    private int position;
    private int score;
    private Label scoreField;

    /**
     * Default constructor. Instantiates the list spaceObjects which holds the
     * various walls and actors in it. Also instantiates various instance variables
     * that are used for keeping track of the score.
     */
    public Model() {
        //These aren't the implementations you're looking for...
        //spaceObjects = new ArrayList<SpaceObject>();
        //position = 0;
        //score = 0;
        //score = new Label(score);
    }

    /**
     * Updates all the SpaceObjects, the score, and the scoreField.
     */
    public void update() {

    }
}