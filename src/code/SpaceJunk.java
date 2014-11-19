package code; /**
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

    /**
     * Default constructor. Instantiates the SpaceJunk with the default image.
     */
    public SpaceJunk() {
        super();
        this.setImage("/res/metalSpaceJunk.png");
        this.setSize(90,60);
    }
}
