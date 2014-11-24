package code;

import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/23/2014.
 */

/**
 * HighScoreView.java
 *
 * This is the view class for the High Score section of the Intergalactic Adventure of Blob.
 * It is relatively simple, as it just takes in information and converts it to a TableView
 * for the viewing pleasure of the user.
 */
public class HighScoreView extends Group {
    //Instance Variables
    private HighScoresModel scoresModel;
    private TableView tableView;

    /**
     * Constructor. Just instantiates a tableView for later use.
     */
    public HighScoreView() {
        this.tableView = new TableView();
    }

    /**
     * Setter for the HighScoresModel
     * @param scoresModel
     */
    public void setScoresModel(HighScoresModel scoresModel) {
        this.scoresModel = scoresModel;
    }

    /**
     * Method for updating the tableView. Three columns are created, and data is inputted
     * from the HighScoresModel associated with the class.
     */
    public void update() {
        //Some css to make everything look a little prettier.
        String styleString = "-fx-border-color: green; -fx-background-color: white;" +
                "-fx-padding: 5";

        TableColumn scoreColumn = new TableColumn("Score");
        scoreColumn.setMinWidth(100);
        scoreColumn.setCellValueFactory(
                new PropertyValueFactory<HighScore, Integer>("score"));

        TableColumn userColumn = new TableColumn("User");
        userColumn.setMinWidth(100);
        userColumn.setCellValueFactory(
                new PropertyValueFactory<HighScore, String>("user"));

        TableColumn dateColumn = new TableColumn("Date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<HighScore, String>("date"));

        this.tableView.setItems(this.scoresModel.getObservableList());
        tableView.getColumns().addAll(scoreColumn, userColumn, dateColumn);
        tableView.setStyle(styleString);

        this.getChildren().add(tableView);
    }
}
