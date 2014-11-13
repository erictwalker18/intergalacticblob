package walkere.carleton.edu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/13/2014.
 */
public class GameView extends Application {
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
    }

    /**
     * Essentially the main method for the view. Launches the view and makes
     * everything pretty!
     * @param args "command-line" arguments
     */
    public static void startGameView(String[] args) {
        launch(args);
    }
}
