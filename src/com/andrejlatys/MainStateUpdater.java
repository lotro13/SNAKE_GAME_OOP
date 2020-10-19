package com.andrejlatys;

import java.awt.event.KeyEvent;

public class MainStateUpdater {

    private static final MainStateUpdater instance = new MainStateUpdater();

    private final SideBarState sideBarState = new SideBarState();

    private GridState gridState;
    private SnakeState snakeState;
    private FoodState foodState;

    private final CollisionDetector collisionDetector;

    private GameStatus gameStatus;

    private MainStateUpdater() {
        gridState = new GridState();
        snakeState = new SnakeState(new SnakeTile(8, 8));
        foodState = new FoodState();

        collisionDetector = new CollisionDetector();

        gameStatus = GameStatus.WAITING;
    }

    public static MainStateUpdater getInstance(){
        return instance;
    }

    public void update() {
        if (gameStatus.equals(GameStatus.PLAYING)) {
            foodState.update(this);
            snakeState.update(this);
            gridState.update(this);
            collisionDetector.detect(this);
            return;
        }

        if (gameStatus.equals(GameStatus.WAITING)
                || gameStatus.equals(GameStatus.LOST)
                || gameStatus.equals(GameStatus.WON)
                || gameStatus.equals(GameStatus.PAUSED)) {
            sideBarState.update(this);
        }
    }

    public void resetGame() {
        gridState = new GridState();
        snakeState = new SnakeState(new SnakeTile(8, 8));
        foodState = new FoodState();
    }

    public void changeSnakeDirection(Direction direction) {
        snakeState.changeDirection(direction);
    }

    public SideBarState getSideBarState() {
        return sideBarState;
    }


    public CollisionDetector getCollisionDetector() {
        return collisionDetector;
    }

    public GridState getGridState() {
        return gridState;
    }

    public SnakeState getSnakeState() {
        return snakeState;
    }

    public FoodState getFoodState() {
        return foodState;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (gameStatus.equals(GameStatus.PLAYING)) {
            switch (key) {
                case KeyEvent.VK_W -> changeSnakeDirection(Direction.UP);
                case KeyEvent.VK_D -> changeSnakeDirection(Direction.RIGHT);
                case KeyEvent.VK_S -> changeSnakeDirection(Direction.DOWN);
                case KeyEvent.VK_A -> changeSnakeDirection(Direction.LEFT);
                case KeyEvent.VK_ESCAPE -> setGameStatus(GameStatus.PAUSED);
            }
        } else {
            if (key == KeyEvent.VK_ENTER
                    && !gameStatus.equals(GameStatus.PAUSED)) {
                resetGame();
                setGameStatus(GameStatus.PLAYING);
            }

            if (key == KeyEvent.VK_ESCAPE
                    && gameStatus.equals(GameStatus.PAUSED)) {
                setGameStatus(GameStatus.PLAYING);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
