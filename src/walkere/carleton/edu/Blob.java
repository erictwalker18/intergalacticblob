package walkere.carleton.edu;

/**
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
    }

    /**
     * Constructor for making a more interesting blob with a different image
     * @param pictureFileName the name of the file that holds the picture for the blob.
     */
    public Blob(String pictureFileName) {

    }

    /**
     *
     * @param spaceJunk the SpaceJunk to check an intersection with
     * @return true if the mighty Blob has been crippled by a spaceJunk, false if
     *     Blob has managed to live another step.
     */
    public boolean isHit(SpaceJunk spaceJunk) {
        //uses intersects(double localX, double localY, double localWidth, double localHeight)
        //method to tell if Blob has hit spaceJunk
        return true;
    }
}
