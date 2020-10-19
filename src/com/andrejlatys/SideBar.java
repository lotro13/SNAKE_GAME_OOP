package com.andrejlatys;

import java.awt.*;

public class SideBar extends Observer implements UIElement {
    private final int CORE_X;
    private final int CORE_Y;

    private int highScore = 0;

    public SideBar(Subject subject, int CORE_X, int CORE_Y) {
        this.CORE_X = CORE_X;
        this.CORE_Y = CORE_Y;

        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void paint(Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        final int H = fm.getAscent();

        g.setColor(Color.BLACK);
        g.drawString("HIGHSCORE: " + highScore, CORE_X, CORE_Y + H);
        g.drawString("Press Enter to start the game", CORE_X, CORE_Y + 3 * H);
        g.drawString("Press Esc to pause/unpause", CORE_X, CORE_Y + 5 * H);
        g.drawString("Work done by: ", CORE_X, CORE_Y + 7 * H);
        g.drawString("Andrej Latys", CORE_X, CORE_Y + 8 * H);
        g.drawString("PRIf-18/4", CORE_X, CORE_Y + 9 * H);
    }

    @Override
    public void update() {
        final MainStateUpdater mainStateUpdater = subject.getState();
        final SideBarState sideBarState = mainStateUpdater.getSideBarState();
        final int newHighScore = sideBarState.getHighScore();

        if (newHighScore > highScore) {
            highScore = newHighScore;
        }
    }
}
