package com.thoughtworks.pacman.ui;

import java.awt.Graphics2D;

public interface Presenter {
    int TILE_SIZE = 16;

    void draw(Graphics2D graphics);
}
