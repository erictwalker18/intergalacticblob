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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    //Instance Variables
    private GameModel gameModel;
    private HighScoresModel scoresModel;
    private final String SAVE_FILE = "";

    @FXML
    private GridPane gridPane;

    /**
     * Default constructor.
     */
    public Controller() {}

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

    public void onStartButton() {
        System.out.println("I did a thingy yo");
        Blob b = new Blob();
        b.setSize(100,100);
        SpaceJunk s = new SpaceJunk();
        gridPane.getChildren().clear();
        gridPane.add(b, 0, 1);
        gridPane.add(s, 0, 2);
    }
}
