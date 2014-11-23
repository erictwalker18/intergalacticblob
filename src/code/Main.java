package code; /**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/16/2014.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Timer;
import java.util.TimerTask;
import javafx.geometry.Point2D;

/**
 * Main class that starts everything up.
 */
public class Main extends Application {
    /**
     * Start method for the application. Overridden from Application so that it
     * will close everything upon exiting.
     *
     * @param primaryStage the primary stage for the application to run on
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("intergalactic.fxml"));
        FXMLLoader scoresLoader = new FXMLLoader(getClass().getResource("highScores.fxml"));

        Parent root = (Parent)gameLoader.load();
        Parent scoresRoot = (Parent)scoresLoader.load();

        final Controller gameController = gameLoader.getController();
        final HighScoreController scoresController = scoresLoader.getController();

        root.setOnKeyPressed(gameController);

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("The Intergalactic Adventure of Blob");

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * Essentially the main method for the view. Launches the view and makes
     * everything pretty!
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
