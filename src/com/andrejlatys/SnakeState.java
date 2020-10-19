package com.andrejlatys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SnakeState {
    private List<SnakeTile> BODY_TILES;

    public SnakeState(SnakeTile HEAD_TILE) {
        BODY_TILES = new ArrayList<>();
        BODY_TILES.add(HEAD_TILE);
    }

    public void addBody(SnakeTile tile) {
        BODY_TILES.add(tile);
    }

    public void makeAMove(MainStateUpdater state) {
        final SnakeTile nextHeadTile = BODY_TILES.get(0).findNextTile(state.getGridState());

        if (!getBODY_TILES().isEmpty()) {
            Collections.rotate(getSNAKE_TILES(), 1);
        }

        BODY_TILES.set(0, nextHeadTile);
    }

    public void changeDirection(Direction direction) {
        final Direction currentDirection = getHEAD_TILE().getDirection();
        int difference = Math.abs(direction.ordinal() - currentDirection.ordinal());

        if (difference != 2) {
            getHEAD_TILE().changeDirection(direction);
        }
    }

    public SnakeTile getHEAD_TILE() {
        return getSNAKE_TILES().get(0);
    }

    public SnakeTile getLAST_TILE() {
        return getSNAKE_TILES().get(BODY_TILES.size() - 1);
    }

    public List<SnakeTile> getSNAKE_TILES() {
        return BODY_TILES;
    }

    public List<SnakeTile> getBODY_TILES() {
        return BODY_TILES.stream()
                .skip(1L)
                .collect(Collectors.toList());
    }

    public int getSnakeLength() {
        return getSNAKE_TILES().size();
    }

    public void setBODY_TILES(List<SnakeTile> BODY_TILES) {
        this.BODY_TILES = BODY_TILES;
    }

    public boolean isFreeTile(int x, int y) {
        final Optional<SnakeTile> first = getSNAKE_TILES().stream()
                .filter(t -> t.getTILE_X() == x && t.getTILE_Y() == y)
                .findFirst();

        return first.isEmpty();
    }

    public void update(MainStateUpdater state) {
        CollisionDetector collisionDetector = state.getCollisionDetector();

        Optional<SnakeTile> newBodyTile = Optional.empty();

        if (collisionDetector.getCurrentType().equals(CollisionType.SNAKE_FOOD)) {
            newBodyTile = Optional.ofNullable(getLAST_TILE());
        }

        makeAMove(state);

        newBodyTile.ifPresent(this::addBody);
    }
}
