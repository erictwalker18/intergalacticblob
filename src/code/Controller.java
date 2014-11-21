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

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;

public class Controller implements EventHandler<KeyEvent> {
    //Instance Variables
    private GameModel gameModel;
    private HighScoresModel scoresModel;
    private final String SAVE_FILE = "";
    private Group rootGroup;

    private boolean hasStarted = false;

    @FXML
    private AnchorPane anchorPane;

    /**
     * Default constructor.
     */
    public Controller() {
        this.gameModel = new GameModel();
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

    public void showStartButton() {
        Button startButton = new Button("Start");
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                onStartButton();
                e.consume();
            }
        });
        this.anchorPane.getChildren().add(startButton);
    }

    /**
     * What happens when the user selects the start button!
     */
    public void onStartButton() {
        if (!hasStarted) {
            this.anchorPane.getChildren().get(0).setOpacity(0.0);
            this.gameModel.setPane(this.anchorPane);
            this.gameModel.getBoringWalls();
            hasStarted = true;
        }
    }

    /**
     * Simple getter for the model.
     * @return
     */
    public GameModel getGameModel() {
        return this.gameModel;
    }

    @Override
    public void handle(KeyEvent event) {
        System.out.println(event.getCharacter());
        Blob avatar= this.gameModel.getAvatar();
        if (event.getCode().isWhitespaceKey()) {
            this.gameModel.getAvatar().setVelocity(0, this.gameModel.getAvatar()
                .getVelocity().getY()-15);
            event.consume();
        }
    }
}