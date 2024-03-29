package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;


public class Snake implements Animatable {
    private static float speed = 2;
    private SimpleIntegerProperty health = new SimpleIntegerProperty(100);

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;
    private boolean isHigh = false;

    public SnakeHead getHead() {
        return head;
    }

    public Snake(Vec2d position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();

        addPart(4);
    }

    public SimpleIntegerProperty healthProperty() {
        return health;
    }

    public int getHealth() {
        return health.get();
    }

    public void step() {
        SnakeControl turnDir = getUserInput();

        if (!isHigh) {
            head.updateRotation(turnDir, speed, 1);
        }
        else {
            head.updateRotation(turnDir, speed, -2);
        }

        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        health.set(health.get() + diff);
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health.get() <= 0) {
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
            Globals.getInstance().display.showGameOverScreen(body.getList().size());
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }

    public void changeSpeed(float diff) {
        speed += diff;
    }


    public void setHigh(boolean isHigh){
        this.isHigh = isHigh;
    }

    public boolean isHigh() {
        return isHigh;
    }
}
