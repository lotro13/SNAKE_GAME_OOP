package com.andrejlatys;

public class SnakeTile extends Tile {

    private Direction currentDirection;

    public SnakeTile(int tile_x, int tile_y) {
        super(tile_x, tile_y);

        currentDirection = Direction.UP;
    }

    public SnakeTile(int tile_x, int tile_y, Direction direction) {
        super(tile_x, tile_y);

        currentDirection = direction;
    }

    public SnakeTile findNextTile(GridState state) {
        int newX = getTILE_X();
        int newY = getTILE_Y();


        switch (currentDirection) {
            case UP -> newY -= 1;
            case LEFT -> newX -= 1;
            case DOWN -> newY += 1;
            case RIGHT -> newX += 1;
        }

        final int boardSIDE = state.getBoardSIDE();

        newX = (newX < 0 ? boardSIDE - 1 : (newX >= boardSIDE ? 0 : newX));
        newY = (newY < 0 ? boardSIDE - 1 : (newY >= boardSIDE ? 0 : newY));

        return new SnakeTile(newX, newY, currentDirection);
    }

    public void changeDirection(Direction newDirection) {
        currentDirection = newDirection;
    }

    public Direction getDirection() {
        return currentDirection;
    }
}
