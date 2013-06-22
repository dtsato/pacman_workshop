package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;

public enum GhostType {
    BLINKY("ghost10.jpg", "ghost11.jpg", new SpacialCoordinate(14 * Tile.SIZE, 14 * Tile.SIZE + Tile.SIZE / 2)),
    PINKY("ghost40.jpg", "ghost41.jpg", new SpacialCoordinate(14 * Tile.SIZE, 17 * Tile.SIZE + Tile.SIZE / 2)),
    INKY("ghost30.jpg", "ghost31.jpg", new SpacialCoordinate(12 * Tile.SIZE, 17 * Tile.SIZE + Tile.SIZE / 2)),
    CLYDE("ghost20.jpg", "ghost21.jpg", new SpacialCoordinate(16 * Tile.SIZE, 17 * Tile.SIZE + Tile.SIZE / 2));

    private final SpacialCoordinate startCoordinate;
    private final String image1;
    private final String image2;

    private GhostType(String image1, String image2, SpacialCoordinate startCoordinate) {
        this.image1 = image1;
        this.image2 = image2;
        this.startCoordinate = startCoordinate;
    }

    public SpacialCoordinate getStartCoordinate() {
        return startCoordinate;
    }

    public String getImageOne() {
        return image1;
    }

    public String getImageTwo() {
        return image2;
    }
}
