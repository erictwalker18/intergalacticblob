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
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    public Button pauseButton;
    public Button startButton;
    public Button highScoresButton;

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

    public void setScoresModel(HighScoresModel scoresModel) {
        this.scoresModel = scoresModel;
    }

    public void showStartButton() {
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.FXML"));
        try {
            Parent root = loader.load();
            root.setOnKeyPressed(this);
            this.anchorPane.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        this.startButton = new Button("Start");
        this.startButton.setFocusTraversable(true);
        this.startButton.setFont(new Font(80.0));
        this.startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                onStartButton();
                e.consume();
            }
        });
        this.anchorPane.getChildren().add(this.startButton);
        this.anchorPane.setTopAnchor(this.startButton, 300.0);
        this.anchorPane.setLeftAnchor(this.startButton, 350.0);

        this.highScoresButton = new Button("High Scores");
        this.highScoresButton.setFocusTraversable(true);
        this.highScoresButton.setFont(new Font(60.0));
        this.highScoresButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    onHighScoresButton();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.consume();
            }
        });
        this.anchorPane.getChildren().add(this.highScoresButton);
        this.anchorPane.setTopAnchor(this.highScoresButton, 500.0);
        this.anchorPane.setLeftAnchor(this.highScoresButton, 300.0);
    }

    /**
     * What happens when the user selects the start button!
     */
    public void onStartButton() {
        if (!hasStarted) {
            setUpAnimationTimer();
            this.anchorPane.getChildren().clear();
            this.gameModel.setPane(this.anchorPane);
            this.gameModel.getInitialWallsAndAvatar();
            this.pauseButton = new Button("Pause");
            pauseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    onPauseButton();
                }
            });
            this.anchorPane.getChildren().add(pauseButton);
            hasStarted = true;
        }
    }

    public void onPauseButton() {
        if (this.paused) {
            this.setUpAnimationTimer();
            this.pauseButton.setText("Pause");
        } else {
            this.timer.cancel();
            this.pauseButton.setText("Resume");
        }
        this.paused = !this.paused;
    }

    public void onHighScoresButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("highScores.fxml"));
        Parent root = loader.load();
        HighScoreController highScoreController = loader.getController();
        if (this.scoresModel == null)
            this.scoresModel = new HighScoresModel();
        highScoreController.initialize(this.scoresModel);
        highScoresButton.getScene().setRoot(root);
    }

    public void onSubmitButton(String name, String date) {
        this.scoresModel.addHighScore(this.gameModel.getScore(), name, date);
        resetToStart();
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.P) {
            onPauseButton();
            event.consume();
            return;
        }
        if (paused) //we don't want any surprises after the pause!
            return;
        Blob avatar= this.gameModel.getAvatar();
        if (event.getCode().isWhitespaceKey()) {
            this.gameModel.getAvatar().setVelocity(this.gameModel.getAvatar().getVelocity().getX(),
                    this.gameModel.getAvatar().getVelocity().getY()-15);
            event.consume();
        }
        else if (event.getCode() == KeyCode.D) {
            this.gameModel.getAvatar().setVelocity(this.gameModel.getAvatar().getVelocity().getX()+1,
                    this.gameModel.getAvatar().getVelocity().getY());
            event.consume();
        }
        else if (event.getCode() == KeyCode.A) {
            this.gameModel.getAvatar().setVelocity(this.gameModel.getAvatar().getVelocity().getX()-1,
                    this.gameModel.getAvatar().getVelocity().getY());
            event.consume();
        }
    }

    public void updateAnimation() {
        this.gameModel.step();
        if (this.frameNumber % 6 == 0 && this.frameNumber < 905) {
            this.gameModel.getNextWallSection();
        }
        this.frameNumber = (this.frameNumber + 5) % 900;
        if (this.gameModel.isOver()) {
            lose();
        }
    }

    public void lose() {
        this.timer.cancel();
        if (this.scoresModel == null) {
            this.scoresModel = new HighScoresModel();
        }
        Stage newStage = new Stage();
        newStage.setTitle("High Score Entry");
        final VBox comp = new VBox();
        Text yourScore = new Text("Your Score: \n "+Integer.toString(this.gameModel.getScore()));
        final TextField nameField= new TextField("Name");
        final TextField dateField = new TextField("Date");
        Button submitButton = new Button("Submit");
        submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (nameField.getText() != null && dateField.getText() != null) {
                    onSubmitButton(nameField.getText(), dateField.getText());
                    ((Stage)nameField.getScene().getWindow()).close();
                }
                else {
                    Text errorMessage = new Text("Please enter both a name and a date");
                    comp.getChildren().add(errorMessage);
                }
            }
        });
        comp.getChildren().add(yourScore);
        comp.getChildren().add(nameField);
        comp.getChildren().add(dateField);
        comp.getChildren().add(submitButton);

        Scene stageScene = new Scene(comp, 300, 300);
        newStage.setScene(stageScene);
        newStage.show();
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

        final long startTimeInMilliseconds = 500;
        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer = new java.util.Timer();
        this.timer.schedule(timerTask, startTimeInMilliseconds, frameTimeInMilliseconds);
    }

    public void resetToStart() {
        this.anchorPane.getChildren().clear();
        showStartButton();
        this.gameModel = new GameModel();
        this.frameNumber = 0;
        this.hasStarted = false;
    }
}