package com.andrejlatys;

import java.awt.*;

public class GameBoardTile {
    private final Tile tile;

    public static final int TILE_SIDE = 12;

    private GameBoardTileType type;

    public GameBoardTile(Tile tile) {
        this.tile = tile;
        this.type = GameBoardTileType.EMPTY;
    }

    public void updateType(GameBoardTileType type) {
        this.type = type;
    }

    public GameBoardTileType getType() {
        return type;
    }

    public void paint(Graphics g, int coreX, int coreY) {
        final int CURRENT_X = coreX + tile.getTILE_X() * TILE_SIDE;
        final int CURRENT_Y = coreY + tile.getTILE_Y() * TILE_SIDE;

        switch (type) {
            case EMPTY -> g.setColor(Color.GRAY);
            case SNAKE -> g.setColor(Color.green);
            case HEAD -> g.setColor(Color.RED);
            case FOOD -> g.setColor(Color.YELLOW);
        }

        g.fillRect(CURRENT_X, CURRENT_Y, TILE_SIDE, TILE_SIDE);
    }
}
