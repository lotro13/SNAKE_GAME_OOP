package com.andrejlatys;

public class Tile {
    private final int TILE_X;
    private final int TILE_Y;

    public Tile(int tile_x, int tile_y) {
        TILE_X = tile_x;
        TILE_Y = tile_y;
    }

    public int getTILE_X() {
        return TILE_X;
    }

    public int getTILE_Y() {
        return TILE_Y;
    }

    public boolean equals(Tile tile) {
        return tile.getTILE_X() == getTILE_X() && tile.getTILE_Y() == getTILE_Y();
    }

}
