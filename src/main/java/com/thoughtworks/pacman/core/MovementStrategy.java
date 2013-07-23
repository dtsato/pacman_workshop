package com.thoughtworks.pacman.core;

public interface MovementStrategy {
    public TileCoordinate getNextTile(TileCoordinate currentTile);
}
