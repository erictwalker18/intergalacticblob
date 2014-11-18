package code; /**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/12/2014.
 */

/**
 * Blob.java
 *
 * Class for the avatar of the user in the game.
 */
public class Blob extends SpaceObject {

    /**
     * Default constructor. Instantiates the Blob with the default image
     */
    public Blob() {
        super();
        this.setImage("/res/Blob.png");
    }
    /**
     * @param spaceJunk the SpaceJunk to check an intersection with
     * @return true if the mighty Blob has been crippled by a spaceJunk, false if
     *     Blob has managed to live another step.
     */
    /* DEPRECATED
    public boolean isHit(SpaceJunk spaceJunk) {
        Point2D spaceJunkPosition = spaceJunk.getPosition();
        if (this.intersects(spaceJunkPosition.getX(), spaceJunkPosition.getY(),
                spaceJunk.getSize().getX(), spaceJunk.getSize().getY() )) {
            return true;
        }
        return false;
    } */
}
