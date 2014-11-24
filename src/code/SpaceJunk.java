package code;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/12/2014.
 */

/**
 * SpaceJunk.java
 *
 * View for all the wall objects that are created.
 */
public class SpaceJunk extends SpaceObject {
    //Instance Variables
    private Rectangle rectangle;

    final private int DEFAULT_WIDTH = 30;
    final private int DEFAULT_HEIGHT = 20;
    final private int DEFAULT_X_VELOCITY = -5;
    final private int DEFAULT_Y_VELOCITY = 0;

    /**
     * Default constructor. Instantiates the SpaceJunk with a DARKSlATEGRAY
     * rectangle. Overrides SpaceObject's use of an imageView due to
     * speed constraints, because we all like our games to run very
     * fast. Also gives the space junk the default size and velocity.
     */
    public SpaceJunk() {
        this.rectangle = new Rectangle(DEFAULT_WIDTH, DEFAULT_HEIGHT, Color.DARKSLATEGRAY);
        this.setVelocity(DEFAULT_X_VELOCITY, DEFAULT_Y_VELOCITY);
        this.getChildren().add(this.rectangle);
    }

    /**
     * Getter for the size of the object. Overridden to account for
     * the object being a rectangle, not an imageView.
     * @return the size of the object
     */
    @Override
    public Point2D getSize() {
        return new Point2D(this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Setter for the size. Overridden to account for the object
     * being a rectangle, not an imageView.
     * @param x the desired width
     * @param y the desired height
     */
    public void setSize(double x, double y) {
        this.resize(x, y);
        this.rectangle.setWidth(x);
        this.rectangle.setHeight(y);
    }

    /**
     * Setter for the color of the spaceJunk. Should never be used, as
     * DARKSLATEGRAY is far too awesome a name.
     * @param color the desired color.
     */
    public void setColor(Color color) {
        this.rectangle.setFill(color);
    }
}
