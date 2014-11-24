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
public class HighScoreController {
    private HighScoresModel scoresModel;
    final private String SAVE_FILE = "highScores.ser";

    @FXML private AnchorPane anchorPane;

    public HighScoreController() {
    }

    public void onMainMenuButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();
        root.setFocusTraversable(true);
        Controller controller = loader.getController();
        controller.setScoresModel(this.scoresModel);
        root.setOnKeyPressed(controller);
        this.anchorPane.getChildren().get(0).getScene().setRoot(root);
    }

    public void initialize(HighScoresModel scoresModel) {
        this.scoresModel = scoresModel;
        HighScoreView scoreView = new HighScoreView();
        scoreView.setScoresModel(scoresModel);
        scoreView.update();
        anchorPane.getChildren().add(scoreView);
        anchorPane.setTopAnchor(scoreView, 100.0);
    }
}
