package com.thoughtworks.pacman.core.tiles;

import com.thoughtworks.pacman.core.Tile;

public class Dot implements Tile {
    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public String toString() {
        return ".";
    }
}
