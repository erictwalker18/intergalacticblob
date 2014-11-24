package code;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/16/2014.
 */

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

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = gameLoader.load();

        final GameController gameController = gameLoader.getController();

        root.setOnKeyPressed(gameController); //gives the gameController key events to deal with

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("The Intergalactic Adventure of Blob");
        primaryStage.setScene(scene);
        primaryStage.show();

        root.requestFocus();
    }
    /**
     * Main method. Launches the intergalactic adventure of Blob.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
