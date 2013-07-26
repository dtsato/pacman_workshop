package com.thoughtworks.pacman.core.movement;

import com.thoughtworks.pacman.core.TileCoordinate;

public interface MovementStrategy {
    public TileCoordinate getNextTile(TileCoordinate currentTile);
}
