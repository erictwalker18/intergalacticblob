package code;

import javafx.scene.Group;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/12/2014.
 */

/**
 * SpaceObject.java
 *
 * This class is the abstract class that all space objects will extend.
 * It contains methods that are necessary for building the views of
 * The Intergalactic Adventure of Blob.
 *
 * Much of this code is borrowed from Jeff Ondich's Sprite Class
 */
public abstract class SpaceObject extends Group {
    //Instance Variables
    //This contains an ImageView as further work on this program
    //will add to the obstacles Blob must face in his intergalactic
    //adventure, which should have images associated with them.
    private Point2D velocity;
    public ImageView imageView;
    private Image image;

    /**
     * Constructor. Instantiates the ImageView and sets the velocity to 0.
     */
    public SpaceObject() {
        this.imageView = new ImageView();
        this.getChildren().add(this.imageView);
        this.velocity = new Point2D(0,0);
    }

    /**
     * Getter for the position of a SpaceObject
     * @return this SpaceObject's position
     */
    public Point2D getPosition() {
        return new Point2D(this.getLayoutX(), this.getLayoutY());
    }

    /**
     * Setter the position of a SpaceObject
     * @param x the new x position for the SpaceObject
     * @param y the new y position for the SpaceObject
     */
    public void setPosition(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    /**
     * Getter the velocity of a SpaceObject
     * @return the velocity of the SpaceObject
     */
    public Point2D getVelocity() {
        return this.velocity;
    }

    /**
     * Setter the velocity of a SpaceObject
     * @param vx the new x velocity for the SpaceObject
     * @param vy the new y velocity for the SpaceObject
     */
    public void setVelocity(double vx, double vy) {
        this.velocity = new Point2D(vx, vy);
    }

    /**
     * Getter the size of the ImageView that is the view of the SpaceObject
     * @return the size of the ImageView that makes up the picture of the SpaceObject
     */
    public Point2D getSize() {
        Point2D size = new Point2D(this.imageView.getFitWidth(), this.imageView.getFitHeight());
        return size;
    }

    /**
     * Setter the size of the ImageView that is the view of the SpaceObject
     * @param width
     * @param height
     */
    public void setSize(double width, double height) {
        this.resize(width, height);
        this.imageView.setFitWidth(width);
        this.imageView.setFitHeight(height);
    }

    /**
     * Setter the image to the given filename
     * @param filename the name of the image file
     */
    public void setImage(String filename) {
        this.image = new Image(getClass().getResourceAsStream(filename));
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