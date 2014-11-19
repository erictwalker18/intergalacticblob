package code; /**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/16/2014.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * View that puts everything together
 */
public class View extends Group implements Initializable{
    GameModel model;
    HighScoresModel highScoresModel;

    @FXML private GridPane gridPane;

    /**
     * Default constructor
     */
    public View() {

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Setter for the view's model
     * @return
     */
    public GameModel getModel() {
        return this.model;
    }

    /**
     * Getter for the View's model
     * @param model
     */
    public void setModel(GameModel model){
        this.model = model;
    }

    /**
     * Setter for the view's model
     * @return
     */
    public HighScoresModel getHighScoresModel() {
        return this.highScoresModel;
    }

    /**
     * Getter for the View's model
     * @param model
     */
    public void setModel(HighScoresModel model){
        this.highScoresModel = model;
    }
}
