package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.powerups.FadingPowerUps;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;

import java.util.List;

public class GameLoop {
    private Snake snake;
    private boolean running = false;
    private int loopCount;
    private int powerUpSpawnRate;

    public GameLoop(Snake snake) { this.snake = snake; }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        if(running) {

//            if (!containsInstance(Globals.getInstance().display.getObjectList(), SimplePowerUp.class)) {
//                Globals.getInstance().game.spawnSimplePowerUps(1);
//            }

            snake.step();
            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
                if (gameObject instanceof FadingPowerUps) {
                    ((FadingPowerUps) gameObject).increaseTemporaryLoopCount();
                    ((FadingPowerUps) gameObject).fadeAway(10*60);
                }
            }
            if (getLoopCount() % getPowerUpSpawnRate() == 0) {
                Globals.getInstance().game.spawnARandomPowerUp();
            }
            if (snake.isHigh()) {
                snake.getHead().getHigh();
                snake.getHead().increaseTemporaryLoopCount();
            }

            checkCollisions();
            increaseLoopCount();
        }

        Globals.getInstance().display.frameFinished();
    }

    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable){
                        if(objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())){
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }

    private void increaseLoopCount() {
        loopCount ++;
    }

    public int getLoopCount(){
        return loopCount;
    }

    public void setPowerUpSpawnRate(int loops){
        powerUpSpawnRate = loops;
    }

    private int getPowerUpSpawnRate(){
        return powerUpSpawnRate;
    }

//    private boolean containsInstance(List<GameEntity> gameObjectList, Class classInQuestion) {
//        for ( GameEntity gameEntity : gameObjectList) {
//            if (classInQuestion.isInstance(gameEntity)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
