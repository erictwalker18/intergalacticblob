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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("intergalactic.fxml"));
        //Parent root = (Parent)loader.load();
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("The Intergalactic Adventure of Blob");
        Blob b = new Blob();
        b.setSize(50,50);
        b.setVelocity(1,1);
        b.setPosition(0,0);
        b.setImage("/res/Blob.png");
        root.getChildren().add(b);
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
