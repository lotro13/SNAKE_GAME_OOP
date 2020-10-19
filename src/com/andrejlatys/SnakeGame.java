package com.andrejlatys;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class SnakeGame implements Runnable {
    private final int width;
    private final int height;

    private final int SLEEP_TIME_BETWEEN_UPDATE = 80;

    private boolean running = false;

    private Thread thread;

    private final GameWindow gameWindow;

    private GameBoard board;
    private SideBar bar;

    private Subject subject;
    private MainStateUpdater updater;

    public SnakeGame(int width, int height) {
        this.width = width;
        this.height = height;

        gameWindow = new GameWindow(this.width, this.height);
    }

    private void init() {
        subject = new Subject();
        board = new GameBoard(subject, 36, 36);
        bar = new SideBar(subject, 250, 36);

        updater = MainStateUpdater.getInstance();

        gameWindow.addKeyListener(new KeyInput());
    }

    private void update() {
        updater.update();
        subject.setState(updater);
    }

    private void paint() {
        final Canvas canvas = gameWindow.getCanvas();

        BufferStrategy bS = canvas.getBufferStrategy();
        if (bS == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics g = bS.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        board.paint(g);
        bar.paint(g);

        bS.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();

        while (running) {

            update();
            paint();

            try {
                Thread.sleep(SLEEP_TIME_BETWEEN_UPDATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
