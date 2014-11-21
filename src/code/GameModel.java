package code;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;
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

    /**
     * Default constructor. Instantiates the avatar Blob and the list spaceObjects
     * which holds the various spaceJunks in it. Also instantiates various instance variables
     * that are used for keeping track of the score.
     */
    public GameModel() {
        this.group = new Group();
        //ImageView imageView = new ImageView();
        //imageView.setFitWidth(1000);
        //imageView.setFitHeight(800);
        //this.group.getChildren().add(imageView);
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
        for (SpaceObject s: spaceObjects) {
            if (s.getPosition().getX() == 0 && s.getVelocity().getX() != 0)
                s.setPosition(1080, s.getPosition().getY());
            s.step();
        }
        this.score++;
        System.out.println(this.score);
        //if (isHit()) {
            //System.out.println("Ouchie");
        //}
    }

    /**
     * Adds a spaceJunk object at the specified location
     * @param x the x location for the spaceJunk
     * @param y the y location for the spaceJunk
     */
    public void addSpaceJunk(double x, double y) {

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
        for (SpaceObject spaceJunk : this.spaceObjects) {
            Point2D spaceJunkPosition = spaceJunk.getPosition();
            Point2D avatarPosition = this.avatar.getPosition();
            if (this.avatar.intersects(spaceJunk.getBoundsInLocal())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Builds a bunch of boring walls...
     */
    public void getBoringWalls() {
        this.avatar = new Blob();
        this.avatar.setPosition(0, 350);
        this.avatar.setVelocity(0,0);
        this.group.getChildren().add(this.avatar);
        for (int i=0; i<74; i++) {
            SpaceJunk junk = new SpaceJunk();
            if (i < 72) {
                junk.setPosition(i * 30 / 2 - (i % 2) * 15, (i % 2) * 780);
                junk.setVelocity(-5, 0);
            } else {
                junk.setPosition(0, (i % 2) * 780);
                junk.setVelocity(0, 0);
            }
            this.spaceObjects.add(junk);
            this.group.getChildren().add(junk);
        }
        this.anchorPane.getChildren().add(this.group);
    }

    /**
     * Sets up the animation that the model works through.
     */
    public void setUpAnimationTimer() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        step();
                    }
                });
            }
        };

        final long startTimeInMilliseconds = 2000;
        final long repetitionPeriodInMilliseconds = 100;
        long frameTimeInMilliseconds = (long)(1000.0 / 30);
        Timer timer = new java.util.Timer();
        timer.schedule(timerTask, startTimeInMilliseconds, frameTimeInMilliseconds);
    }
}