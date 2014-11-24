package code;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/23/2014.
 */

/**
 * Controller for the high score view of the game. Contains methods to
 * initialize and go back to the main menu.
 */
public class HighScoresController {
    //Instance Variables
    private HighScoresModel scoresModel;
    final private String SAVE_FILE = "highScores.ser"; //For later use with serialization...

    @FXML private AnchorPane anchorPane;

    /**
     * Default constructor. Does absolutely nothing.
     */
    public HighScoresController() { }

    /**
     * Method for going back to the main menu. Loads the game view,
     * which includes the main menu, then transfers the scoresModel
     * to the GameController.
     * @throws IOException
     */
    public void onMainMenuButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();
        root.setFocusTraversable(true);
        GameController gameController = loader.getController();
        gameController.setScoresModel(this.scoresModel);
        root.setOnKeyPressed(gameController);
        this.anchorPane.getChildren().get(0).getScene().setRoot(root);
    }

    /**
     * Method for initializing the controller. Sets up the view and
     * adds it to the correct place on the anchor pane.
     * @param scoresModel the highScore model that holds all the high scores (surprisingly).
     */
    public void initialize(HighScoresModel scoresModel) {
        this.scoresModel = scoresModel;
        HighScoreView scoreView = new HighScoreView();
        scoreView.setScoresModel(scoresModel);
        scoreView.update();
        this.anchorPane.getChildren().add(scoreView);
        this.anchorPane.setTopAnchor(scoreView, 100.0);
        this.anchorPane.setLeftAnchor(scoreView, 350.0);
    }
}
