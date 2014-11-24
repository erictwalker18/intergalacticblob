package code;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/23/2014.
 */
public class HighScoreView extends Group {
    private HighScoresModel scoresModel;
    private int scoreIndex;
    private TableView tableView;

    private double width;
    private Color backgroundColor;

    final double DEFAULT_WIDTH = 600.0;
    final Color DEFAULT_BACKGROUND_COLOR = Color.GREEN;

    public HighScoreView() {
        this.width = DEFAULT_WIDTH;
        this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
        this.tableView = new TableView();
    }

    public void setScoresModel(HighScoresModel scoresModel) {
        this.scoresModel = scoresModel;
    }

    public int getScoreIndex() {
        return this.scoreIndex;
    }

    public void setScoreIndex(int index) {
        this.scoreIndex = index;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Point2D getPosition() {
        return new Point2D(this.getLayoutX(), this.getLayoutY());
    }

    public void setPosition(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void update() {
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
        /*
        Label label;

        this.getChildren().clear();
        String styleString = "-fx-border-color: green; -fx-background-color: white;" +
                "-fx-padding: 5";

        label = new Label(Integer.toString(this.scoresModel.get(this.scoreIndex).getScore()));
        label.setStyle(styleString);
        this.getChildren().add(label);

        label = new Label(this.scoresModel.get(this.scoreIndex).getUser());
        label.setStyle(styleString);
        label.setLayoutX(100);
        this.getChildren().add(label);

        label = new Label(this.scoresModel.get(this.scoreIndex).getDate());
        label.setStyle(styleString);
        label.setLayoutX(200);
        this.getChildren().add(label);
        */
    }
}
