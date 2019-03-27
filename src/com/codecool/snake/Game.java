package com.codecool.snake;

import com.codecool.snake.entities.enemies.SecondEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.ThirdEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


public class Game extends Pane {
    private Snake snake = null;
    private Label healthDisplay = new Label();
    private Button restart = new Button("Restart");
    private GameTimer gameTimer = new GameTimer();


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(3);
        spawnPowerUps(4);
        setHealthDisplay();
        setRestartButton();

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    public void restart() {
        Globals.getInstance().stopGame();
        Globals.getInstance().display.clear();
        init();
        start();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
        snake.healthProperty().addListener((
            ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->
            refreshDisplayedHealth(newValue)
        );
    }

    private void setHealthDisplay(){
        healthDisplay.textProperty().setValue("Health: " + snake.getHealth());
        healthDisplay.setFont(Font.font(25));
        this.getChildren().add(healthDisplay);
    }

    private void refreshDisplayedHealth(Number number) {
        healthDisplay.textProperty().setValue("Health: " + number.toString());
    }
    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i){
            if (i == 0) {
                new ThirdEnemy();
                new SimpleEnemy();
                new SecondEnemy();
            } else {
                new SimpleEnemy();
                new SecondEnemy();
            }
        }
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
    }

    private void setRestartButton(){
        restart.setOnAction((ActionEvent e) -> restart());
        double windowWidth = Globals.getInstance().WINDOW_WIDTH;
        restart.setMinWidth(50);
        restart.relocate((windowWidth - 1.5 * restart.getMinWidth()), 5);
        this.getChildren().add(restart);
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
