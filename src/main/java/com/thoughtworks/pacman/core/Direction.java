package com.thoughtworks.pacman.core;

import java.util.EnumSet;

public enum Direction {
    UP(0, -1, 130) {
        @Override
        public EnumSet<Direction> validTurns() {
            return EnumSet.of(LEFT, RIGHT);
        }
    },
    DOWN(0, 1, 310) {
        @Override
        public EnumSet<Direction> validTurns() {
            return EnumSet.of(LEFT, RIGHT);
        }
    },
    LEFT(-1, 0, 220) {
        @Override
        public EnumSet<Direction> validTurns() {
            return EnumSet.of(UP, DOWN);
        }
    },
    RIGHT(1, 0, 40) {
        @Override
        public EnumSet<Direction> validTurns() {
            return EnumSet.of(UP, DOWN);
        }
    },
    NONE(0, 0, 0) {
        @Override
        public EnumSet<Direction> validTurns() {
            return EnumSet.of(UP, DOWN, LEFT, RIGHT);
        }
    };

    private final int x;
    private final int y;
    private final int startAngle;

    private Direction(int x, int y, int startAngle) {
        this.x = x;
        this.y = y;
        this.startAngle = startAngle;
    }

    public SpacialCoordinate delta() {
        return new SpacialCoordinate(x, y);
    }

    public TileCoordinate tileDelta() {
        return new TileCoordinate(x, y);
    }

    public int getStartAngle() {
        return startAngle;
    }

    public abstract EnumSet<Direction> validTurns();
}
