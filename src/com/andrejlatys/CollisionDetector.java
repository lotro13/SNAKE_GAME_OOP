package com.andrejlatys;

import java.util.Optional;

public class CollisionDetector {

    private CollisionType currentType;

    public CollisionDetector() {
        currentType = CollisionType.NONE;
    }

    public void detect(MainStateUpdater mainStateUpdater) {
        final SnakeState snakeState = mainStateUpdater.getSnakeState();
        final FoodState foodState = mainStateUpdater.getFoodState();

        Tile headTile = snakeState.getHEAD_TILE();
        Optional<Tile> foodTile = foodState.getTileWithFood();

        if (foodTile.isPresent()) {
            if (headTile.equals(foodTile.get())) {
                currentType = CollisionType.SNAKE_FOOD;
                return;
            }
        }

        final Optional<SnakeTile> collisionWithBody = snakeState.getBODY_TILES().stream()
                .filter(st -> st.equals(headTile))
                .findFirst();

        if (collisionWithBody.isPresent()) {
            currentType = CollisionType.SNAKE_SNAKE;
            mainStateUpdater.setGameStatus(GameStatus.LOST);
            return;
        }

        currentType = CollisionType.NONE;
    }

    public CollisionType getCurrentType() {
        return currentType;
    }
}
