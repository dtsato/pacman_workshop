package com.thoughtworks.pacman.ui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public interface Screen {
    void draw(Graphics2D graphics);
    void keyPressed(KeyEvent e);
    Screen getNextScreen() throws Exception;
}
