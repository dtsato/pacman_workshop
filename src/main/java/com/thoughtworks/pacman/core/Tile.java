package com.thoughtworks.pacman.core;

public interface Tile {
    boolean isMovable();

    <T> T visit(TileVisitor<T> visitor);
}
