package code; /**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/12/2014.
 */

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * SpaceJunk.java
 *
 * View for all the wall objects that are created.
 */
public class SpaceJunk extends SpaceObject {
    private Rectangle rectangle;

    /**
     * Default constructor. Instantiates the SpaceJunk with the default image.
     */
    public SpaceJunk() {
        //super();
        //this.setImage("/res/metalSpaceJunk.png");
        this.rectangle = new Rectangle(30, 20, Color.DARKSLATEGRAY);
        this.setVelocity(-5, 0);
        this.getChildren().add(this.rectangle);
    }

    /**
     * @return
     */
    @Override
    public Point2D getSize() {
        return new Point2D(this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    public void setSize(double x, double y) {
        this.resize(x, y);
        this.rectangle.setWidth(x);
        this.rectangle.setHeight(y);
    }

    public void setColor(Color color) {
        this.rectangle.setFill(color);
    }
}
