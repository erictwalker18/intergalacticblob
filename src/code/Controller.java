package code;
/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/12/2014
 */

/**
 * Controller.java
 *
 * Main class that runs the entire program.
 */

import javafx.fxml.FXML;

import java.io.*;

public class Controller {
    //Instance Variables
    private GameModel gameModel;
    private HighScoresModel scoresModel;
    private final String SAVE_FILE = "";

    /**
     * Default constructor.
     */
    public Controller() {
    }

    /**
     * Serialization method. Saves the current model for later use.
     */
    public void saveGameModel() {

    }

    /**
     * Deserialization method. Loads a model from the default file.
     */
    public void loadGameModel() {

    }

    /**
     * Serialization method. Saves the current high score for later use.
     */
    public void saveHighScore() {
        try {
            FileOutputStream fileOut = new FileOutputStream("highscores.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.scoresModel);
            out.close();
            fileOut.close();
            System.out.println("Data has been saved!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserialization method. Loads a list of high scores from the default file.
     */
    public void loadHighScores() {
        try {
            FileInputStream fileIn = new FileInputStream("highscores.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.scoresModel = (HighScoresModel) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Data has been saved!");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStuff() {
        System.out.println("I did a thing!");
    }
}
