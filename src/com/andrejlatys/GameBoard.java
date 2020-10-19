package com.andrejlatys;

import java.awt.*;

public class GameBoard extends Observer implements UIElement {

    private final int CORE_X;
    private final int CORE_Y;

    private final int TEXT_X;
    private final int TEXT_Y;

    private final int SIDE = 16;

    private String boardText;

    GameBoardTile[][] mainGrid = new GameBoardTile[SIDE][SIDE];

    public GameBoard(Subject subject, int CORE_X, int CORE_Y) {
        this.CORE_X = CORE_X;
        this.CORE_Y = CORE_Y;

        TEXT_X = CORE_X + (SIDE / 3) * GameBoardTile.TILE_SIDE;
        TEXT_Y = CORE_Y + (SIDE / 2) * GameBoardTile.TILE_SIDE;

        this.subject = subject;
        this.subject.attach(this);
    }

    public void paint(Graphics g) {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                mainGrid[i][j].paint(g, CORE_X, CORE_Y);
            }
        }

        if (!boardText.equals("")) {
            FontMetrics fm = g.getFontMetrics();
            final int W = fm.stringWidth(boardText);
            final int H = fm.getAscent();

            if (boardText.equals("You won!")) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.BLACK);
            }

            g.drawString(boardText, TEXT_X - (W / 9), TEXT_Y + (H / 4));
        }
    }

    @Override
    public void update() {
        final MainStateUpdater mainStateUpdater = subject.getState();
        final GridState gridState = mainStateUpdater.getGridState();
        final GameStatus gameStatus = mainStateUpdater.getGameStatus();

        mainGrid = gridState.getMainGrid();

        switch (gameStatus) {
            case WAITING -> boardText = "Press 'Enter' to Start";
            case PLAYING -> boardText = "";
            case WON -> boardText = "You won!";
            case LOST -> boardText = "You loose!";
            case PAUSED -> boardText = "Game is Paused";
        }
    }
}
