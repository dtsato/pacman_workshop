package com.thoughtworks.pacman.core.tiles;

import com.thoughtworks.pacman.core.Tile;

public class EmptyTile implements Tile {

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public String toString() {
        return " ";
    }
}
