package com.thoughtworks.pacman.core;


public abstract class Actor {
    private static final int SPEED = 100;

    private SpacialCoordinate center;
    private Direction currentDirection;

    public Actor(SpacialCoordinate center, Direction direction) {
        this.center = center;
        this.currentDirection = direction;
    }

    public SpacialCoordinate getCenter() {
        return center;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public boolean collidesWith(Actor other) {
        return center.toTileCoordinate().equals(other.center.toTileCoordinate());
    }

    public void advance(long timeDeltaInMillis) {
        int distance = (int) (SPEED * timeDeltaInMillis / 1000);
        SpacialCoordinate nextCenter = center.add(currentDirection.delta().times(distance));
        SpacialCoordinate currentTileCenter = center.toTileCoordinate().toSpacialCoordinate();
        SpacialCoordinate nextTileCenter = nextCenter.toTileCoordinate().toSpacialCoordinate();

        if (currentTileCenter.between(center, nextCenter)) {
            advanceFromCenter(distance, currentTileCenter);
        } else if (nextTileCenter.between(center, nextCenter)) {
            advanceFromCenter(distance, nextTileCenter);
        } else {
            center = nextCenter;
        }
    }

    private void advanceFromCenter(int distance, SpacialCoordinate currentTileCenter) {
        currentDirection = getNextDirection(currentTileCenter.toTileCoordinate());
        int distanceLeft = distance - currentTileCenter.subtract(center).modulo();
        center = currentTileCenter.add(currentDirection.delta().times(distanceLeft));
    }

    protected abstract Direction getNextDirection(TileCoordinate tileCoordinate);
}
