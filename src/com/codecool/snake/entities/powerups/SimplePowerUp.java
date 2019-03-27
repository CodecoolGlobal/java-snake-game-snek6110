package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class SimplePowerUp extends PowerUps implements Interactable {
    private static Random rnd = new Random();

    public SimplePowerUp() {
        setImage(Globals.getInstance().getImage("PowerUpBerry"));

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }

    @Override
    public void fadeAway(int loopsToFade) {
    }

    @Override
    public void increaseTemporaryLoopCount() {
    }
}
