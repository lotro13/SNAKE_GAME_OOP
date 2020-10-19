package com.andrejlatys;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private final int width;
    private final int height;
    private Canvas canvas;


    public GameWindow(int width, int height) {
        this.width = width;
        this.height = height;

        createGameWindow();
    }

    private void createGameWindow() {
        setTitle("Snake game");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        canvas = new Canvas();
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));

        add(canvas);
        pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
