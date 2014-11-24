package code;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane anchorPane;
    private Group group;
    private ArrayList<SpaceObject> spaceObjects;
    private Blob avatar;
    private Label scoreLabel;
    private int score;
    private boolean isOver;

    /**
     * Default constructor. Instantiates the avatar Blob and the list spaceObjects
     * which holds the various spaceJunks in it. Also instantiates various instance variables
     * that are used for keeping track of the score and the state of the game.
     */
    public GameModel() {
        this.group = new Group();
        this.spaceObjects = new ArrayList<SpaceObject>();
        this.avatar = new Blob();
        this.scoreLabel = new Label();
        this.score = 0;
        this.isOver = false;
    }

    /**
     * Updates all the SpaceJunks, Blob and the score. This also checks if the Blob
     * hit any of the horrible spaceJunk obstacles or is trying to go off the screen,
     * and deals with it.
     */
    public void step() {
        this.avatar.step();
        if (this.avatar.getPosition().getX() > 920) {
            this.avatar.setPosition(920, this.avatar.getPosition().getY());
            this.avatar.setVelocity(0, this.avatar.getVelocity().getY());
        }
        else if (this.avatar.getPosition().getX() < 0) {
            this.avatar.setPosition(0, this.avatar.getPosition().getY());
            this.avatar.setVelocity(0, this.avatar.getVelocity().getY());
        }
        for (SpaceObject s: spaceObjects) {
            if (s.getPosition().getX() == -30 && s.getVelocity().getX() != 0) {
                this.group.getChildren().remove(s);
            }
            s.step();
        }
        this.score++;
        this.scoreLabel.setText("Score: "+this.score);
        if (isHit()) //Checks if the game has ended. Later versions might include lives, so this statement would be extended.
            isOver = true;
    }

    /**
     * Adds a spaceJunk object at the specified location.
     * @param x the x location for the spaceJunk
     * @param y the y location for the spaceJunk
     */
    public void addSpaceJunk(double x, double y) {
        SpaceJunk junkToAdd = new SpaceJunk();
        junkToAdd.setPosition(x, y);
        this.spaceObjects.add(junkToAdd);
    }



    /**
     * Getter for the score of the game.
     * @return the current score of the game
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Getter for the avatar of the game. Useful for keyEvents.
     * @return the avatar to get its position.
     */
    public Blob getAvatar() {
        return this.avatar;
    }

    /**
     * Setter for the AnchorPane that the model will use to put views in.
     * @param anchorPane the anchorPane that the scene has.
     */
    public void setPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
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

        for (SpaceObject spaceJunk : this.spaceObjects) {

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

    /**
     * Gets the initial frame of the game. This instantiates the avatar and
     * a bunch of boring walls, as well as the background ("Space: the
     * final frontier"), and the score label (in the bottom right).
     */
    public void initializeModel() {
        SpaceJunk background = new SpaceJunk();
        background.setVelocity(0,0);
        background.setSize(1000,800);
        background.setColor(Color.BLACK);
        this.group.getChildren().add(background);

        this.avatar = new Blob();
        this.avatar.setPosition(30, 350);
        this.avatar.setVelocity(0,0);
        this.group.getChildren().add(this.avatar);

        this.scoreLabel.setText("Score: " + this.score);
        this.scoreLabel.setLayoutY(30);
        this.scoreLabel.setTextFill(Color.WHITE);
        this.group.getChildren().add(this.scoreLabel);

        //A bunch of boring walls just on top and bottom of the screen.
        for (int i = 0; i < 72; i++) {
            SpaceJunk junk = new SpaceJunk();
            junk.setPosition(i * 30 / 2 - (i % 2) * 15, (i % 2) * 780);
            this.spaceObjects.add(junk);
            this.group.getChildren().add(junk);
        }
        this.anchorPane.getChildren().add(this.group);
    }

    /**
     * Adds onto the boring walls that were already built, in a more interesting way.
     * The composition of the walls is governed by a sine function,
     * and the gap between the top and bottom walls gets increasingly smaller,
     * to a point.
     */
    public void getNextWallSection() {
        double nextSinHeight = Math.sin(this.score) * (this.score % 1500 / 100) + (this.score % 1500 / 100);

        for (int i = 0; i < nextSinHeight; i++) {
            SpaceJunk junk = new SpaceJunk();
            junk.setPosition(1075, i*20);
            this.spaceObjects.add(junk);
            this.group.getChildren().add(junk);
        }

        for (int i = 0; i < 38 - nextSinHeight - (25 - this.score % 1000 / 100); i++) {
            SpaceJunk junk = new SpaceJunk();
            junk.setPosition(1075, 800 - i * 20);
            this.spaceObjects.add(junk);
            this.group.getChildren().add(junk);
        }
    }
}