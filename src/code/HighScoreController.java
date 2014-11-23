package code;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("highScores.fxml"));

        Scene scene = new Scene(root, 1000, 800);

        stage.setTitle("High Scores");
        stage.setScene(scene);
        stage.show();
    }
}
