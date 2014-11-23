package code;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
 */
public class GameModel {
    //Instance Variables
    private AnchorPane anchorPane;
    private Group group;
    private ArrayList<SpaceObject> spaceObjects;
    private Blob avatar;
    private int score;
    private boolean isOver;
    private Random randomNumberGenerator;

    /**
     * Default constructor. Instantiates the avatar Blob and the list spaceObjects
     * which holds the various spaceJunks in it. Also instantiates various instance variables
     * that are used for keeping track of the score.
     */
    public GameModel() {
        this.group = new Group();
        this.randomNumberGenerator = new Random();
        this.spaceObjects = new ArrayList<SpaceObject>();
        this.avatar = new Blob();
        this.score = 0;
        this.isOver = false;
    }

    /**
     * Updates all the SpaceObjects and the score. This also checks if the blob
     * hit any of the horrible spaceJunk obstacles.
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
                //s.setPosition(1080, s.getPosition().getY());
                //spaceObjects.remove(s);
                this.group.getChildren().remove(s);
            }
            s.step();
        }
        this.score++;
        System.out.println(this.score);
        if (isHit()) {
            System.out.println("Ouchie");
        }
    }

    /**
     * Adds a spaceJunk object at the specified location
     * @param x the x location for the spaceJunk
     * @param y the y location for the spaceJunk
     */
    public void addSpaceJunk(double x, double y) {
        SpaceJunk junkToAdd = new SpaceJunk();
        junkToAdd.setPosition(x, y);
        this.spaceObjects.add(junkToAdd);
    }



    /**
     * Gets the score of the game
     * @return the current score of the game
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Gets the spaceObjects stored in the model (for testing purposes)
     * @return the spaceObjects arraylist associated with this instance of GameModel
     */
    public ArrayList<SpaceObject> getSpaceObjects() {
        return this.spaceObjects;
    }

    /**
     *
     * @return the avatar to get its position.
     */
    public Blob getAvatar() {
        return this.avatar;
    }

    public void setPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    /**
     * Tells the controller (or a test) whether or not the game is over.
     * @return isOver, a boolean variable.
     */
    public boolean isOver() {
        return this.isOver;
    }

    /**
     * Method to check if the user has lost!
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
            isOver = true;
            if (avatarPositionX > spaceJunkPositionX && avatarPositionX < spaceJunkPositionX + spaceJunkWidth
                && avatarPositionY + 16 > spaceJunkPositionY && avatarPositionY + 16 < spaceJunkPositionY + spaceJunkHeight)
                return true;
            //if top right corner of the blob conflicting:
            else if (avatarPositionX + avatarWidth > spaceJunkPositionX && avatarPositionX + avatarWidth < spaceJunkPositionX + spaceJunkWidth
                    && avatarPositionY > spaceJunkPositionY && avatarPositionY < spaceJunkPositionY + spaceJunkHeight)
                return true;
                //if bottom left corner of the blob conflicting:
            else if (avatarPositionX > spaceJunkPositionX && avatarPositionX < spaceJunkPositionX + spaceJunkWidth
                    && avatarPositionY + avatarHeight > spaceJunkPositionY && avatarPositionY + avatarHeight < spaceJunkPositionY + spaceJunkHeight)
                return true;
                //if bottom right corner of the blob conflicting:
            else if (avatarPositionX + avatarWidth > spaceJunkPositionX && avatarPositionX + avatarWidth < spaceJunkPositionX + spaceJunkWidth
                    && avatarPositionY + avatarHeight > spaceJunkPositionY && avatarPositionY + avatarHeight < spaceJunkPositionY + spaceJunkHeight)
                return true;
        }
        isOver = false;
        return false;
    }

    /**
     * Builds a bunch of boring walls...
     */
    public void getBoringWalls() {
        SpaceJunk background = new SpaceJunk();
        background.setVelocity(0,0);
        background.setSize(1000,800);
        background.setColor(Color.BLACK);
        this.group.getChildren().add(background);
        this.avatar = new Blob();
        this.avatar.setPosition(30, 350);
        this.avatar.setVelocity(0,0);
        this.group.getChildren().add(this.avatar);
        for (int i = 0; i < 72; i++) {
            SpaceJunk junk = new SpaceJunk();
            junk.setPosition(i * 30 / 2 - (i % 2) * 15, (i % 2) * 780);
            this.spaceObjects.add(junk);
            this.group.getChildren().add(junk);
        }
        this.anchorPane.getChildren().add(this.group);
    }

    /**
     * Adds onto the walls that were already built, in a more interesting way
     */
    public void getNextWallSection() {
        //int nextTopHeight = this.randomNumberGenerator.nextInt(3);
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