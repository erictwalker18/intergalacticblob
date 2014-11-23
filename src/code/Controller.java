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

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements EventHandler<KeyEvent> {
    //Instance Variables
    final private String SAVE_FILE = "highScores.ser";
    final private double FRAMES_PER_SECOND = 30.0;

    private GameModel gameModel;
    private HighScoresModel scoresModel;
    private boolean paused;
    private Timer timer;
    private int frameNumber;

    private boolean hasStarted = false;

    @FXML
    private AnchorPane anchorPane;

    /**
     * Default constructor.
     */
    public Controller() {
        this.gameModel = new GameModel();
        this.paused = false;
        this.frameNumber = 0;
    }

    /**
     * Serialization method. Saves the current high score for later use.
     */
    public void saveHighScore() {
        try {
            FileOutputStream fileOut = new FileOutputStream(SAVE_FILE);
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
            FileInputStream fileIn = new FileInputStream(SAVE_FILE);
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

    /**
     * What happens when the user selects the start button!
     */
    public void onStartButton() {
        if (!hasStarted) {
            setUpAnimationTimer();
            this.anchorPane.getChildren().get(0).setOpacity(0.0);
            this.gameModel.setPane(this.anchorPane);
            this.gameModel.getBoringWalls();
            hasStarted = true;
        }
    }

    @Override
    public void handle(KeyEvent event) {
        System.out.println(event.getCharacter());
        Blob avatar= this.gameModel.getAvatar();
        if (event.getCode().isWhitespaceKey()) {
            this.gameModel.getAvatar().setVelocity(this.gameModel.getAvatar().getVelocity().getX(),
                    this.gameModel.getAvatar().getVelocity().getY()-15);
            event.consume();
        }
        else if (event.getCode() == KeyCode.D) {
            this.gameModel.getAvatar().setVelocity(this.gameModel.getAvatar().getVelocity().getX()+1,
                    this.gameModel.getAvatar().getVelocity().getY());
        }
        else if (event.getCode() == KeyCode.A) {
            this.gameModel.getAvatar().setVelocity(this.gameModel.getAvatar().getVelocity().getX()-1,
                    this.gameModel.getAvatar().getVelocity().getY());
        }
    }

    public void onPauseButton(ActionEvent actionEvent) {
        if (this.paused) {
            this.setUpAnimationTimer();
        } else {
            this.timer.cancel();
        }
        this.paused = !this.paused;
    }

    public void updateAnimation() {
        this.gameModel.step();
        if (this.frameNumber % 6 == 0 && this.frameNumber < 905) {
            this.gameModel.getNextWallSection();
        }
        this.frameNumber = (this.frameNumber + 5) % 900;
        if (this.gameModel.isOver()) {
            this.timer.cancel();
            if (this.scoresModel == null) {
                this.scoresModel = new HighScoresModel();
            }
            this.scoresModel.addHighScore(this.gameModel.getScore(), "Eric", "11-23-2014");
            System.out.println(this.scoresModel.getHighScores(1));
        }
    }

    /**
     * Sets up the animation that the model works through.
     */
    public void setUpAnimationTimer() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        updateAnimation();
                    }
                });
            }
        };

        final long startTimeInMilliseconds = 0;
        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer = new java.util.Timer();
        this.timer.schedule(timerTask, startTimeInMilliseconds, frameTimeInMilliseconds);
    }
}