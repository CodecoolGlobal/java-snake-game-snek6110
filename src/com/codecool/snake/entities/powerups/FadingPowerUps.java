package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

public abstract class FadingPowerUps extends GameEntity implements Interactable {

    public int temporaryLoopCount;

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Yummy";
    }

    public void increaseTemporaryLoopCount() {
        temporaryLoopCount ++;
    }

    public void fadeAway(int loopsToFade) {
        if (temporaryLoopCount % loopsToFade == 0) {
            destroy();
        }
    }


}
