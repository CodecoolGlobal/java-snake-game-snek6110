package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Vector;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public Display display;
    public Game game;

    private GameLoop gameLoop;
    private Resources resources;

    private Vec2d currentHeadPosition;

    public void setCurrentHeadPosition(Vec2d currentHeadPosition) {
        this.currentHeadPosition = currentHeadPosition;
    }

    public Vec2d getCurrentHeadPosition() {
        return currentHeadPosition;
    }

    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_head.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("SecondEnemy", new Image("second_enemy.png"));
        resources.addImage("ThirdEnemy", new Image("third_enemy.png"));
        resources.addImage("PowerUpBerry", new Image("powerup_berry.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
