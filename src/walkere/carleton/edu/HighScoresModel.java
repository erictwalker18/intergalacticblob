package walkere.carleton.edu;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/14/2014.
 */

import java.util.ArrayList;

/**
 * HighScoresModel.java
 *
 * Model to hold all the high scores for later use.
 */
public class HighScoresModel {
    //Instance Variables
    private ArrayList<HighScore> highScores;

    /**
     * Default constructor
     */
    public HighScoresModel() {

    }

    /**
     * Adds a high score to the list
     * @param score the score obtained by the user
     * @param user the name of the user
     * @param date the time of the high score
     */
    public void addHighScore(int score, String user, String date) {

    }

    /**
     * @return the number of high scores in this model
     */
    public int getNumHighScores() {
        return highScores.size();
    }

    /**
     * Gets a specific number of scores for the user
     * @param numScores the number of scores the user wants
     * @return a string with all the scores in string format
     */
    public String getHighScores(int numScores) {
        return "";
    }
}