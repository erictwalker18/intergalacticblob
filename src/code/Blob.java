package code; /**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/12/2014.
 */

/**
 * Blob.java
 *
 * View for the avatar of the user in the game.
 */
public class Blob extends SpaceObject {

    /**
     * Default constructor. Instantiates the Blob with the default image
     */
    public Blob() {
        super();
        this.setImage("/res/Blob.png");
        this.setSize(80,80);
    }

    /**
     * Steps the blob. Overridden to account for gravity.
     */
    @Override
    public void step() {
        super.step();
        this.setVelocity(this.getVelocity().getX(), this.getVelocity().getY()+1);
        if (this.getLayoutY() > 700)
            this.setVelocity(this.getVelocity().getX(), -this.getVelocity().getY()+1);
        else if (this.getLayoutY() < 0)
            this.setVelocity(this.getVelocity().getX(), -this.getVelocity().getY()+1);
    }
}
