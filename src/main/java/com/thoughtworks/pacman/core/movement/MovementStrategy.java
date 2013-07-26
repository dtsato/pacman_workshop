package com.thoughtworks.pacman.core.movement;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.TileCoordinate;

public interface MovementStrategy {
    TileCoordinate getNextTile(TileCoordinate currentTile);

    void jump(TileCoordinate tileCoordinate);

    Direction getDirection();

    boolean isMoving();
}
