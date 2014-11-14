package walkere.carleton.edu;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/14/2014.
 */

/**
 * HighScore.java
 *
 * High score object for storing the values in a high score.
 */
public class HighScore {
    //Instance Variables
    private int score;
    private String user;
    private String date;

    /**
     * Default constructor.
     */
    public HighScore() {

    }

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
}