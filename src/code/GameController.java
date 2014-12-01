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
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Group group;

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
        this.group = new Group();
        this.paused = false;
        this.frameNumber = 0;
        loadHighScores();
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
     * Gets the initial frame of the game. This instantiates the avatar and
     * a bunch of boring walls, as well as the background ("Space: the
     * final frontier"), and the score label (in the bottom right).
     */
    public void initializeModel() {
        SpaceJunk background = new SpaceJunk();
        background.setVelocity(0,0);
        background.setSize(1000,800);
        background.setColor(Color.BLACK);
        this.group.getChildren().add(background);

        this.gameModel.setAvatar(30, 350, 0, 0);
        this.group.getChildren().add(this.gameModel.getAvatar());

        this.gameModel.setScore(0);
        this.gameModel.setScoreLabel(this.gameModel.getScore());
        this.group.getChildren().add(this.gameModel.getScoreLabel());

        //A bunch of boring walls just on top and bottom of the screen.
        for (int i = 0; i < 72; i++) {
            this.group.getChildren().add(this.gameModel.addSpaceJunk(i * 30 / 2 - (i % 2) * 15, (i % 2) * 780));
        }
        this.anchorPane.getChildren().add(this.group);
    }

    /**
     * Update method. updates the model, the frame number, gets a new wall section if it is time,
     * and checks if the game is over, calling the lose method if it is.
     */
    public void updateAnimation() {
        this.gameModel.step();
        if (this.frameNumber % 6 == 0) {
            getNextWallSection();
        }
        this.frameNumber++;
        this.gameModel.setScoreLabel(this.gameModel.getScore());
        this.gameModel.getAvatar().toFront();
        if (this.gameModel.isOver()) {
            lose();
        }

        //Garbage collection every 1000 steps
        if (this.gameModel.getScore() % 1000 == 0) {
            int i = 0;
            boolean isGarbage = false;
            SpaceJunk junkToDelete = null;
            while (i < this.gameModel.getSpaceJunks().size()) {
                if (this.gameModel.getSpaceJunks().get(i).getPosition().getX() < 0) {
                    isGarbage = true;
                    junkToDelete = this.gameModel.getSpaceJunks().get(i);
                }
                if (isGarbage) {
                    this.gameModel.getSpaceJunks().remove(junkToDelete);
                    this.group.getChildren().remove(junkToDelete);
                    isGarbage = false;
                }
                i++;
            }
        }
    }

    /**
     * Adds onto the boring walls that were already built, in a more interesting way.
     * The composition of the walls is governed by a sine function,
     * and the gap between the top and bottom walls gets increasingly smaller,
     * to a point.
     */
    public void getNextWallSection() {
        double nextSinHeight = Math.sin(this.gameModel.getScore()) * (this.gameModel.getScore() % 1000 / 100) + (this.gameModel.getScore() % 1000 / 100);

        for (int i = 0; i < nextSinHeight; i++) {
            this.group.getChildren().add(this.gameModel.addSpaceJunk(1075, i*20));
        }

        for (int i = 0; i < 38 - nextSinHeight - (36 - this.gameModel.getScore() / 500 - this.gameModel.getScore() % 1000 / 50); i++) {
            this.group.getChildren().add(this.gameModel.addSpaceJunk(1075, 800 - i * 20));
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
        newStage.initStyle(StageStyle.UNDECORATED);

        newStage.setTitle("High Score Entry");
        final VBox comp = new VBox();
        comp.setSpacing(10.0);
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

        initializeModel();

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
     * Save button method. Should correctly serialize the high scores.
     */
    public void onSaveButton() {
        saveHighScore();
    }

    /**
     * Load button method. Should correctly load the high scores in the highScores.ser file
     */
    public void onLoadButton() {
        loadHighScores();
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
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}