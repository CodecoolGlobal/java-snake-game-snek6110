package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;


// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public Display display;
    public Game game;

    public GameLoop gameLoop;
    private Resources resources;

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
        resources.addImage("SpeedUp", new Image("speed_up.png"));
        resources.addImage("SpeedDown", new Image("speed_down.png"));
        resources.addImage("SpeedDownMirrored", new Image("speed_down_mirrored.png"));

        resources.addImage("SnakeHead1", new Image("snake_head_pink1.png"));
        resources.addImage("SnakeHead2", new Image("snake_head_purple2.png"));
        resources.addImage("SnakeHead3", new Image("snake_head_blue3.png"));
        resources.addImage("SnakeHead4", new Image("snake_head_light_blue4.png"));
        resources.addImage("SnakeHead5", new Image("snake_head_green5.png"));
        resources.addImage("SnakeHead6", new Image("snake_head_yellow6.png"));
        resources.addImage("SnakeHead7", new Image("snake_head_read7.png"));

        resources.addImage("Mushroom", new Image("mushroom_red.png"));


    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
