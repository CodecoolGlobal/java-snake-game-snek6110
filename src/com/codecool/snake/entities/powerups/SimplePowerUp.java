package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

import java.util.Random;


public class SimplePowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public SimplePowerUp() {
        setImage(Globals.getInstance().getImage("PowerUpBerry"));

        setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH-30));
        setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT-30));
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
            new SimplePowerUp();
        }
    }


}
