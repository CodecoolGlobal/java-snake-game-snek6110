package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;

import java.util.Random;


public class Mushroom extends FadingPowerUps implements Interactable {
    private static Random rnd = new Random();

    public Mushroom() {
        setImage(Globals.getInstance().getImage("Mushroom"));

        setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH-30));
        setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT-30));

    }

    @Override
    public String getMessage() {
        return " I don't feel so good.. ";
    }
}
