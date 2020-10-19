package com.andrejlatys;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private final MainStateUpdater mainStateUpdater = MainStateUpdater.getInstance();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        mainStateUpdater.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        mainStateUpdater.keyReleased(e);
    }
}
