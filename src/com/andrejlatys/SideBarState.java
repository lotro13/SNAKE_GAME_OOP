package com.andrejlatys;

public class SideBarState {
    private int highScore = 0;

    public void update(MainStateUpdater mainStateUpdater) {
        final SnakeState snakeState = mainStateUpdater.getSnakeState();
        final int currentSnakeLength = snakeState.getSnakeLength();

        if (highScore < currentSnakeLength) {
            highScore = currentSnakeLength;
        }
    }

    public int getHighScore() {
        return highScore;
    }
}
