package com.andrejlatys;

import java.util.Optional;

public class FoodState {
    private Optional<Tile> tileWithFood;

    public FoodState() {
        tileWithFood = Optional.empty();
    }

    public void generateNewFoodPiece(MainStateUpdater state) {
        final SnakeState snakeState = state.getSnakeState();
        final GridState gridState = state.getGridState();

        int newX = (int) (Math.random() * gridState.getBoardSIDE());
        int newY = (int) (Math.random() * gridState.getBoardSIDE());

        final boolean free = snakeState.isFreeTile(newX, newY);
        if (free) {
            tileWithFood = Optional.of(new Tile(newX, newY));
        }
    }

    public Optional<Tile> getTileWithFood() {
        return tileWithFood;
    }

    public boolean isFoodGenerated() {
        return tileWithFood.isPresent();
    }

    public void update(MainStateUpdater state) {
        final CollisionDetector collisionDetector = state.getCollisionDetector();

        if (!isFoodGenerated()) {
            generateNewFoodPiece(state);
        }

        if (collisionDetector.getCurrentType().equals(CollisionType.SNAKE_FOOD)) {
            tileWithFood = Optional.empty();
        }
    }
}
