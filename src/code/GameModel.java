package code;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * BlobStudios
 * Eric Walker
 * CS 257 Final Project
 * Created on 11/12/2014.
 */

/**
 * GameModel.java
 *
 * Model class that holds all the information that the scene is built from.
 * Notably, it holds the walls (SpaceJunk objects), the avatar (a Blob object)
 * and the current score.
 */
public class GameModel {
    //Instance Variables
    private ArrayList<SpaceJunk> spaceJunks;
    private Blob avatar;
    private Label scoreLabel;
    private int score;
    private boolean isOver;

    /**
     * Default constructor. Instantiates the avatar Blob and the list spaceJunks
     * which holds the various spaceJunks in it. Also instantiates various instance variables
     * that are used for keeping track of the score and the state of the game.
     */
    public GameModel() {
        this.spaceJunks = new ArrayList<SpaceJunk>();
        this.avatar = new Blob();
        this.score = 0;
        this.isOver = false;
    }

    /**
     * Updates all the SpaceJunks, Blob and the score. This also checks if the Blob
     * hit any of the horrible spaceJunk obstacles or is trying to go off the screen,
     * and deals with it.
     */
    public void step() {
        if (this.avatar.getPosition().getX() + this.avatar.getVelocity().getX() > 920) {
            this.avatar.setPosition(920, this.avatar.getPosition().getY());
            this.avatar.setVelocity(0, this.avatar.getVelocity().getY());
        }
        else if (this.avatar.getPosition().getX() + this.avatar.getVelocity().getX() < 0) {
            this.avatar.setPosition(0, this.avatar.getPosition().getY());
            this.avatar.setVelocity(0, this.avatar.getVelocity().getY());
        }
        this.avatar.step();
        for (SpaceObject s: spaceJunks) {
            s.step();
        }
        this.score++;
        if (isHit()) //Checks if the game has ended. Later versions might include lives, so this statement would be extended.
            isOver = true;
    }

    /**
     * Adds a spaceJunk object at the specified location.
     * @param x the x location for the spaceJunk
     * @param y the y location for the spaceJunk
     */
    public SpaceJunk addSpaceJunk(double x, double y) {
        SpaceJunk junkToAdd = new SpaceJunk();
        junkToAdd.setPosition(x, y);
        this.spaceJunks.add(junkToAdd);
        return junkToAdd;
    }


    /**
     * Getter for the spaceJunks list
     */
    public ArrayList<SpaceJunk> getSpaceJunks() {
        return this.spaceJunks;
    }

    /**
     * Getter for the score of the game.
     * @return the current score of the game
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Setter for the score of the game
     * @param score the score of the game
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Getter for the score label.
     * @return the scoreLabel Label object to put in the group
     */
    public Label getScoreLabel() {
        return this.scoreLabel;
    }

    /**
     * Setter for the scoreLabel. Instantiates a new label if it does not exist.
     * Otherwise, just sets the text and brings it to the front.
     */
    public void setScoreLabel(int score) {
        if (this.scoreLabel == null) {
            this.scoreLabel = new Label();
            this.scoreLabel.setLayoutY(30);
            this.scoreLabel.setFont(new Font(60.0));
            this.scoreLabel.setTextFill(Color.WHITE);
        }
        this.scoreLabel.setText("Score: " + score);
        this.scoreLabel.toFront();
    }

    /**
     * Getter for the avatar of the game. Useful for keyEvents.
     * @return the avatar to get its position.
     */
    public Blob getAvatar() {
        return this.avatar;
    }

    /**
     * Constructor for the avatar object.
     * @param x the x position of the avatar
     * @param y the y position of the avatar
     * @param vx the x velocity of the avatar
     * @param vy the y velocity of the avatar
     */
    public void setAvatar(double x, double y, double vx, double vy) {
        this.avatar = new Blob();
        this.avatar.setPosition(x, y);
        this.avatar.setVelocity(vx, vy);
    }

    /**
     * Tells the controller whether or not the game is over.
     * @return isOver, a boolean variable.
     */
    public boolean isOver() {
        return this.isOver;
    }

    /**
     * Method to check if the user has lost by hitting a spaceJunk.
     * @return true if the avatar has hit a spaceJunk, false if not
     */
    public boolean isHit() {
        Double avatarPositionX = this.avatar.getPosition().getX();
        Double avatarPositionY = this.avatar.getPosition().getY();
        Double avatarWidth = this.avatar.getSize().getX();
        Double avatarHeight = this.avatar.getSize().getY();

        for (SpaceObject spaceJunk : this.spaceJunks) {

            Double spaceJunkPositionX = spaceJunk.getPosition().getX();
            Double spaceJunkPositionY = spaceJunk.getPosition().getY();
            Double spaceJunkWidth = spaceJunk.getSize().getX();
            Double spaceJunkHeight = spaceJunk.getSize().getY();

            //if top left corner of blob is within the rectangle:
            if (avatarPositionX > spaceJunkPositionX && avatarPositionX < spaceJunkPositionX + spaceJunkWidth
                && avatarPositionY + 16 > spaceJunkPositionY && avatarPositionY + 16 < spaceJunkPositionY + spaceJunkHeight)
                return true;
            //if top right corner of the blob conflicts:
            else if (avatarPositionX + avatarWidth > spaceJunkPositionX && avatarPositionX + avatarWidth < spaceJunkPositionX + spaceJunkWidth
                    && avatarPositionY > spaceJunkPositionY && avatarPositionY < spaceJunkPositionY + spaceJunkHeight)
                return true;
                //if bottom left corner of the blob conflicts:
            else if (avatarPositionX > spaceJunkPositionX && avatarPositionX < spaceJunkPositionX + spaceJunkWidth
                    && avatarPositionY + avatarHeight > spaceJunkPositionY && avatarPositionY + avatarHeight < spaceJunkPositionY + spaceJunkHeight)
                return true;
                //if bottom right corner of the blob conflicts:
            else if (avatarPositionX + avatarWidth > spaceJunkPositionX && avatarPositionX + avatarWidth < spaceJunkPositionX + spaceJunkWidth
                    && avatarPositionY + avatarHeight > spaceJunkPositionY && avatarPositionY + avatarHeight < spaceJunkPositionY + spaceJunkHeight)
                return true;
        }
        return false;
    }
}