package com.codecool.snake.entities.powerups;

import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;


public class SpeedDown extends PowerUps implements Interactable,Animatable {
    private static Random rnd = new Random();
    private boolean isGoingForward = true;
    private final int speed = 1;
    private int temporaryLoopCount;

    public SpeedDown() {
        setImage(Globals.getInstance().getImage("SpeedDown"));

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

    }

    private void UpdateDirection(){
        double rotation = getRotate();

        if (Globals.getInstance().gameLoop.getLoopCount() % 120 == 0) {
            if (isGoingForward) {
                setImage(Globals.getInstance().getImage("SpeedDownMirrored"));
                isGoingForward = false;
            }
            else {
                setImage(Globals.getInstance().getImage("SpeedDown"));
                isGoingForward = true;
            }
            rotation += 180;
        }

        setRotate(rotation);
        Point2D heading = Utils.directionToVector(rotation+90, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

    }

    @Override
    public String getMessage() {
        return "I'm slow as fuck, giiirl :(";
    }

    @Override
    public void step() {
        UpdateDirection();
    }


}
