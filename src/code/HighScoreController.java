package code;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/23/2014.
 */
public class HighScoreController {
    private HighScoresModel scoresModel;
    final private String SAVE_FILE = "highScores.ser";

    @FXML private AnchorPane anchorPane;

    public HighScoreController() {
    }

    public void onHelloButton() {
        scoresModel = new HighScoresModel();
        scoresModel.addHighScore(-1301, "Eric", "testing day");
        scoresModel.addHighScore(9001, "Jeff", "finish day");
        HighScoreView scoreView = new HighScoreView();
        scoreView.setScoresModel(scoresModel);
        scoreView.update();
        anchorPane.getChildren().add(scoreView);
        anchorPane.setTopAnchor(scoreView, 100.0);
    }
}
