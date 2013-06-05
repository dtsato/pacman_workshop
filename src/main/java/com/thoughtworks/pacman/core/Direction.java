package com.thoughtworks.pacman.core;

public enum Direction {
    UP {
        @Override
        public SpacialCoordinate delta() {
            return new SpacialCoordinate(0, -1);
        }
    },
    DOWN {
        @Override
        public SpacialCoordinate delta() {
            return new SpacialCoordinate(0, 1);
        }
    },
    LEFT {
        @Override
        public SpacialCoordinate delta() {
            return new SpacialCoordinate(-1, 0);
        }
    },
    RIGHT {
        @Override
        public SpacialCoordinate delta() {
            return new SpacialCoordinate(1, 0);
        }
    };

    public abstract SpacialCoordinate delta();
}
