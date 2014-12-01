package code;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/14/2014.
 */

import java.io.Serializable;

/**
 * HighScore.java
 *
 * High score object for storing the values in a high score.
 * This object was created over using a dictionary, as a high score
 * consists of a score, name, and date. Storing 3 items in a
 * dictionary is just annoying.
 */
public class HighScore implements Comparable<HighScore>, Serializable { //Implements comparable for use in sorting

    //Instance Variables
    private int score;
    private String user;
    private String date;

    /**
     * Constructor to make a high score given variables.
     * @param score the score that was achieved
     * @param user the high scorer's name
     * @param date the date at which the score was made
     */
    public HighScore(int score, String user, String date) {
        this.score = score;
        this.user = user;
        this.date = date;
    }

    /**
     * Method to convert the high score to a string for use in printing to the view
     * @return a string containing the score, user, and date of the high score.
     */
    public String toString() {
        return Integer.toString(this.score) + "\t" + user + "\t" + date;
    }

    /**
     * Getter for the score of a HighScore object.
     * @return the score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Getter for the user's name in a HighScore object.
     * @return the name of the user.
     */
    public String getUser() {
        return this.user;
    }

    /**
     * Getter for the date the HighScore object was achieved.
     * (An object can be achieved...)
     * @return the date the high score was made.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Method for implementing comparable. Compares objects by their score.
     * @param highScore another HighScore object to compare to
     * @return -1 if this score is less than the highScore's score, 1 if this score is larger, 0 if they are equal.
     */
    @Override
    public int compareTo(HighScore highScore) {
        if (this.score < highScore.getScore())
            return -1;
        else if (this.score > highScore.getScore())
            return 1;
        else
            return 0;
    }
}