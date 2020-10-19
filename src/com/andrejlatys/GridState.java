package com.andrejlatys;

import java.util.Optional;

public class GridState {

    private final int SIDE = 16;

    private final GameBoardTile[][] mainGrid = new GameBoardTile[SIDE][SIDE];

    public GridState() {
        initEmpty();
    }

    public void initEmpty() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                mainGrid[i][j] = new GameBoardTile(new Tile(i, j));
            }
        }
    }

    public int getBoardSIDE() {
        return SIDE;
    }

    public GameBoardTile[][] getMainGrid() {
        return mainGrid;
    }

    public void update(MainStateUpdater state) {
        initEmpty();

        update(state.getFoodState());
        update(state.getSnakeState());
    }

    public void update(SnakeState state) {
        state.getBODY_TILES().stream()
                .forEach(st -> changeTile(st, GameBoardTileType.SNAKE));

        changeTile(state.getHEAD_TILE(), GameBoardTileType.HEAD);
    }

    public void update(FoodState state) {
        final Optional<Tile> tileWithFood = state.getTileWithFood();

        if (tileWithFood.isPresent()) {
            final Tile tile = tileWithFood.get();
            changeTile(tile, GameBoardTileType.FOOD);
        }
    }

    public void changeTile(Tile tile, GameBoardTileType type) {
        mainGrid[tile.getTILE_X()][tile.getTILE_Y()].updateType(type);
    }
}
