package code;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/23/2014.
 */

/**
 * HighScoresModel.java
 *
 * Model to hold all the high scores for later use. Has various methods for accessing the
 * Scores in different ways.
 */

public class HighScoresModel implements Serializable { //Implements Serializable for later use
    //Instance Variables
    private ArrayList<HighScore> highScores;

    /**
     * Default constructor
     */
    public HighScoresModel() {
        highScores = new ArrayList<HighScore>();
    }

    /**
     * Adds a high score to the list, ordered by score.
     * @param score the score obtained by the user
     * @param user the name of the user (or whatever the user happened to type)
     * @param date the time of the high score (or whatever the user happened to type)
     */
    public void addHighScore(int score, String user, String date) {
        int indexToAdd = 0;
        HighScore highScoreToAdd = new HighScore(score, user, date);
        while (indexToAdd < this.highScores.size()) {
            //if the high score we're adding is larger than the one at the index, add it
            if (highScoreToAdd.compareTo(this.highScores.get(indexToAdd)) > 0) {
                this.highScores.add(indexToAdd, highScoreToAdd);
                return;
            }
            indexToAdd++;
        }
        highScores.add(highScoreToAdd);
    }

    /**
     * Getter for the number of high scores in the model.
     * @return the number of high scores in this model
     */
    public int getNumHighScores() {
        return highScores.size();
    }

    /**
     * Getter for a particular high score (in String Form), given an index.
     * @param index the index of the high score in the list
     * @return the String form of the high score in question
     */
    public String getHighScoreStringAt(int index) {
        return this.highScores.get(index).toString();
    }

    /**
     * Getter for a particular high score, given an index.
     * @param index the index of the high score in the list.
     * @return the High Score object at the given index.
     */
    public HighScore getHighScoreAt(int index) {
        return this.highScores.get(index);
    }

    /**
     * Getter for the entire list of high scores, ordered by score.
     * @return the highScores arraylist.
     */
    public ArrayList<HighScore> getScoreList() {
        return this.highScores;
    }

    /**
     * Getter for an ObservableList, useful in creating TableViews
     * @return an observableArrayList of high scores.
     */
    public ObservableList<HighScore> getObservableList() {
        return FXCollections.observableArrayList(this.highScores);
    }
}