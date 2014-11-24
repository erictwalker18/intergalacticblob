package code;
/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/12/2014
 */

/**
 * GameController.java
 *
 * Controller for the game view of the Intergalactic Adventure of Blob
 * The game view section consists of a few parts, a main menu where
 * the user can start playing the game or go look at high scores.
 * This doubles as the instruction screen.
 */

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameController implements EventHandler<KeyEvent> {
    //Instance Variables
    final private String SAVE_FILE = "highScores.ser";
    final private double FRAMES_PER_SECOND = 30.0;

    private GameModel gameModel;
    private HighScoresModel scoresModel;
    private Timer timer;
    private boolean paused;
    private int frameNumber;

    //Buttons that are included in the gameView. These are held in the controller
    //instead of the FXML file as they need to be recreated or referenced elsewhere.
    public Button pauseButton;
    public Button startButton;
    public Button highScoresButton;

    @FXML private AnchorPane anchorPane;

    /**
     * Default constructor. Instantiates the gameModel and the paused and frameNumber variables.
     */
    public GameController() {
        this.gameModel = new GameModel();
        this.paused = false;
        this.frameNumber = 0;
    }

    /**
     * Setter for the HighScoresModel. Useful for changing between views, but keeping the scoresModel.
     * @param scoresModel
     */
    public void setScoresModel(HighScoresModel scoresModel) {
        this.scoresModel = scoresModel;
    }

    /**
     * Shows the main menu. Essentially starts up the program again.
     */
    public void showMainMenu() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.FXML"));
        try {
            Parent root = loader.load();
            final GameController newGameController = loader.getController();
            root.setOnKeyPressed(newGameController);
            newGameController.setScoresModel(this.scoresModel);
            this.anchorPane.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update method. updates the model, the frame number, gets a new wall section if it is time,
     * and checks if the game is over, calling the lose method if it is.
     */
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

    /**
     * Lose method. This is called when a Blob runs into a wall. It cancels the timer,
     * and sets up a window for the user to input their credentials into the high
     * score system to see if they made it in with the heroes.
     */
    public void lose() {
        this.timer.cancel();
        if (this.scoresModel == null) {
            this.scoresModel = new HighScoresModel();
        }

        //User input section for name and date
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

        //There is a slight delay, as in user tests, people didn't already have their
        //hands on the keyboard when they clicked start and needed time to move.
        final long startTimeInMilliseconds = 500;
        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer = new java.util.Timer();
        this.timer.schedule(timerTask, startTimeInMilliseconds, frameTimeInMilliseconds);
    }

    /**
     * Reset method. This is used for when the game ends, but we want to go back to the
     * main menu, and maybe play again.
     */
    public void resetToStart() {
        this.anchorPane.getChildren().clear();
        showMainMenu();
        this.gameModel = new GameModel();
        this.frameNumber = 0;
    }

    /*
        Methods for various buttons are below:
        (Comment here to break up the code and make it more readable)
     */

    /**
     * Start button method. This sets up the animation timer, clears the AnchorPane,
     * then fills it back up with the Blob in intergalactic space and a pause button.
     */
    public void onStartButton() {
        setUpAnimationTimer();

        this.anchorPane.getChildren().clear();

        this.gameModel.setPane(this.anchorPane);
        this.gameModel.initializeModel();

        this.pauseButton = new Button("Pause");
        pauseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onPauseButton();
            }
        });
        this.anchorPane.getChildren().add(pauseButton);
    }

    /**
     * High scores button method. Switches to the high scores view, passing the
     * high scores controller the high scores model.
     * @throws IOException
     */
    public void onHighScoresButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("highScores.fxml"));
        Parent root = loader.load();
        HighScoresController highScoresController = loader.getController();
        if (this.scoresModel == null)
            this.scoresModel = new HighScoresModel();
        highScoresController.initialize(this.scoresModel);
        highScoresButton.getScene().setRoot(root);
    }

    /**
     * Pause button method. Pauses the game if it is not paused, otherwise resumes play.
     * Also called when the player presses 'p'
     */
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

    /**
     * Submit button method. This takes in the name and date the user provided,
     * and stores a new high score in the scores model. It also resets the game
     * back to the main menu.
     * @param name the name of the high scorer
     * @param date the date the high score was achieved
     */
    public void onSubmitButton(String name, String date) {
        this.scoresModel.addHighScore(this.gameModel.getScore(), name, date);
        resetToStart();
    }

    /**
     * KeyEvent handler. Used for keyboard input in controlling the glorious
     * Blob in his intergalactic adventure. This takes in enter, tab, a, d, and p.
     * See the instructions on the game.fxml file to see what each does.
     * @param event the key event
     */
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

        if (event.getCode().isWhitespaceKey()) { //enter or tab. Oddly, space does not work with this. Will continue to look into why.
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

    //Below are methods that have been implemented, but not incorporated into the project,
    //due to time constraints. Hopefully winter break will allow serialization to be included
    //so that scores may be remembered forever.

    /**
     * NOT YET USED (Sadly, time constraints on implementation made this not viable for incorporating for phase_3))
     *
     * Serialization method. Saves the current high score for later use.
     */
    public void saveHighScore() {
        try {
            FileOutputStream fileOut = new FileOutputStream(SAVE_FILE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.scoresModel);
            out.close();
            fileOut.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * NOT YET USED (Sadly, time constraints on implementation made this not viable for incorporating for phase_3)
     *
     * Deserialization method. Loads a list of high scores from the default file.
     */
    public void loadHighScores() {
        try {
            FileInputStream fileIn = new FileInputStream(SAVE_FILE);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.scoresModel = (HighScoresModel) in.readObject();
            in.close();
            fileIn.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}