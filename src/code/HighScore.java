package code; /**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/14/2014.
 */

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * HighScore.java
 *
 * High score object for storing the values in a high score.
 */
public class HighScore implements Comparable<HighScore> {
    @Override
    public int compareTo(HighScore highScore) {
        if (this.score < highScore.score)
            return -1;
        else if (this.score > highScore.score)
            return 1;
        else
            return 0;
    }

    //Instance Variables
    private int score;
    private String user;
    private String date;

    /**
     * Useful constructor to make a high score.
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

    public int getScore() {
        return this.score;
    }

    public String getUser() {
        return this.user;
    }

    public String getDate() {
        return this.date;
    }
}