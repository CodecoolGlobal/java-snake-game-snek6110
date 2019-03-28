package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.PathTransition;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class SecondEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;

    public SecondEnemy(){
        super(-2);

        setImage(Globals.getInstance().getImage("SecondEnemy"));

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        SetStartCoordinations();

        int speed = 3;
        heading = Utils.directionToVector(direction, speed);
        Rectangle rectangle = new Rectangle(getX() + heading.getX(), getY() + heading.getY(),40, 75);

        PathTransition transition = new PathTransition();
        transition.setNode(this);
        transition.setDuration(Duration.seconds(3));
        transition.setPath(rectangle);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.play();
    }



    @Override
    public void step() {
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }


    @Override
    public void apply(GameEntity entity){
        if(entity instanceof SnakeHead) {
            System.out.println(getMessage());
            destroy();
            new SecondEnemy();
        }
    }


    @Override
    public String getMessage(){ return (getDamage() + " damage"); }

}
