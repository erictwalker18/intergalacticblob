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
        scoresModel.addHighScore(-1301, "eric", "testing day");
        scoresModel.addHighScore(9001, "jeff", "finish day");
        HighScoreView scoreView1 = new HighScoreView();
        HighScoreView scoreView2 = new HighScoreView();
        scoreView1.setScoresModel(scoresModel);
        scoreView2.setScoresModel(scoresModel);
        scoreView1.setScoreIndex(0);
        scoreView2.setScoreIndex(1);
        scoreView1.update();
        scoreView2.update();
        anchorPane.getChildren().add(scoreView1);
        anchorPane.getChildren().add(scoreView2);
        anchorPane.setTopAnchor(scoreView1, 100.0);
        anchorPane.setTopAnchor(scoreView2, 200.0);
    }
}
