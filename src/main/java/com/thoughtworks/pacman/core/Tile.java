package com.thoughtworks.pacman.core;

public interface Tile {
    int SIZE = 16;

    boolean isMovable();

    <T> T visit(TileVisitor<T> visitor);
}
