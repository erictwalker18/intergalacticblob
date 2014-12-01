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
    final private String DEFAULT_IMAGE_NAME = "/res/Blob.png";
    final private int DEFAULT_SIZE = 80;

    /**
     * Default constructor. Instantiates the Blob with the default image
     */
    public Blob() {
        super();
        this.setImage(DEFAULT_IMAGE_NAME);
        this.setSize(DEFAULT_SIZE, DEFAULT_SIZE);
    }

    /**
     * Steps the Blob using its velocity. Overridden to account for gravity.
     */
    @Override
    public void step() {
        super.step();
        this.setVelocity(this.getVelocity().getX(), this.getVelocity().getY()+1); //Accounts for gravity
        if (this.getLayoutY() > 720) { //doesn't allow the Blob to fly off the bottom of the screen.
            this.setVelocity(this.getVelocity().getX(), -this.getVelocity().getY() + 1);
            this.setPosition(this.getPosition().getX(), 720);
        }
        else if (this.getLayoutY() < 0) { //doesn't allow the Blob to fly off the top of the screen.
            this.setVelocity(this.getVelocity().getX(), -this.getVelocity().getY() + 1);
            this.setPosition(this.getPosition().getX(), 0);
        }
    }
}
