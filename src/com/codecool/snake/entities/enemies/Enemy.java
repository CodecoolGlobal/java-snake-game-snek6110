package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;

import java.util.Random;

public abstract class Enemy extends GameEntity{
    private final int damage;
    protected static Random rnd = new Random();


    public Enemy(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    protected void SetStartCoordinations() {
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        while ( Globals.getInstance().game.getSnake().getHead().getPosition().equals(getPosition())){
            setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
            setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        }
    }
}
