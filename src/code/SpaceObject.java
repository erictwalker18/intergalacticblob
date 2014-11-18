package code;

import javafx.scene.Group;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/12/2014.
 */

/**
 * SpaceObject.java
 * This class is the abstract class that all space objects will extend.
 * It contains methods that are necessary for building the model of
 * The Intergalactic Adventure of Blob.
 *
 * Much of this src is borrowed from Jeff Ondich's Sprite Class
 */
public abstract class SpaceObject extends Group {
    private String name;
    private Point2D velocity;
    private ImageView imageView;
    private Image image;

    public SpaceObject() {
        this.imageView = new ImageView();
        this.name = "Hi";
        this.velocity = new Point2D(0,0);
    }

    /**
     * Gets the name of a SpaceObject
     * @return this SpaceObject's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of a SpaceObject
     * @param newName for the SpaceObject
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Gets the position of a SpaceObject
     * @return this SpaceObject's position
     */
    public Point2D getPosition() {
        return new Point2D(this.getLayoutX(), this.getLayoutY());
    }

    /**
     * Sets the position of a SpaceObject
     * @param x the new x position for the SpaceObject
     * @param y the new y position for the SpaceObject
     */
    public void setPosition(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    /**
     * Gets the velocity of a SpaceObject
     * @return the velocity of the SpaceObject
     */
    public Point2D getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the velocity of a SpaceObject
     * @param vx the new x velocity for the SpaceObject
     * @param vy the new y velocity for the SpaceObject
     */
    public void setVelocity(double vx, double vy) {
        this.velocity = new Point2D(vx, vy);
    }

    /**
     * Gets the size of the ImageView that is the view of the SpaceObject
     * @return the size of the ImageView that makes up the picture of the SpaceObject
     */
    public Point2D getSize() {
        Bounds bounds = this.getLayoutBounds();
        Point2D size = new Point2D(this.imageView.getFitWidth(), this.imageView.getFitHeight());
        return size;
    }

    /**
     * Sets the size of the ImageView that is the view of the SpaceObject
     * @param width
     * @param height
     */
    public void setSize(double width, double height) {
        this.resize(width, height);
        this.imageView.setFitWidth(width);
        this.imageView.setFitHeight(height);
    }


    public void setImage(String filename) {
        this.image = new Image(getClass().getResourceAsStream("/res/Blob.png"));
        this.imageView.setImage(this.image);
    }
    /**
     * Moves the SpaceObject one step in the direction and magnitude of its velocity.
     */
    public void step() {
        Point2D position = this.getPosition();
        this.setPosition(position.getX() + this.velocity.getX(), position.getY() + this.velocity.getY());
    }
}
