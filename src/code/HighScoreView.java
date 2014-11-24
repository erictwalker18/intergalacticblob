package code;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
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

    @FXML private double width;
    @FXML private Color backgroundColor;

    final double DEFAULT_WIDTH = 600.0;
    final Color DEFAULT_BACKGROUND_COLOR = Color.GREEN;

    public HighScoreView() {
        this.width = DEFAULT_WIDTH;
        this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
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
        Label label;

        this.getChildren().clear();

        label = new Label(this.scoresModel.get(this.scoreIndex).getDate());
        label.setStyle("-fx-border-color: red; -fx-background-color:white;");
        this.getChildren().add(label);

        label = new Label(Integer.toString(this.scoresModel.get(this.scoreIndex).getScore()));
        this.getChildren().add(label);
    }
}
