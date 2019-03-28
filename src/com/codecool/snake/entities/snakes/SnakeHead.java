package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.Mushroom;
import com.codecool.snake.entities.powerups.SimplePowerUp;

import com.codecool.snake.entities.powerups.SpeedDown;
import com.codecool.snake.entities.powerups.SpeedUp;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;
    private int temporaryLoopCount;
    private int headImageNumber = 1;


    public SnakeHead(Snake snake, Vec2d position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }

    public void updateRotation(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        Globals.getInstance().setCurrentHeadPosition(getPosition());
    }


    public void increaseTemporaryLoopCount() {
        temporaryLoopCount ++;
    }

    public void setTemporaryLoopCount(int temporaryLoopCount) {
        this.temporaryLoopCount = temporaryLoopCount;
    }

    public void updateImage() {
        if (headImageNumber < 8) {
            setImage(Globals.getInstance().getImage("SnakeHead" + headImageNumber));
            headImageNumber ++;
        }
        else {
            headImageNumber = 1;
        }
    }

    public void getHigh() {
        if (temporaryLoopCount % 2 == 0) {
            updateImage();
        }
        if (temporaryLoopCount == 60 * 10) {
            snake.setHigh(false);
            setImage(Globals.getInstance().getImage("SnakeHead"));
        }
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
            System.out.println(getMessage());
            snake.changeHealth(((Enemy) entity).getDamage());
        }
        else if(entity instanceof SimplePowerUp){
            System.out.println(getMessage());
            snake.addPart(4);
        }
        else if(entity instanceof SpeedUp){
            snake.changeSpeed(0.3f);
        }
        else if(entity instanceof SpeedDown){
            snake.changeSpeed(-0.3f);
        }
        else if(entity instanceof Mushroom){
            snake.setHigh(true);
            setTemporaryLoopCount(0);
        }
    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
