package com.thoughtworks.pacman.core;

public enum Direction {
    UP {
        @Override
        public TileCoordinate delta() {
            return new TileCoordinate(0, -1);
        }
    },
    DOWN {
        @Override
        public TileCoordinate delta() {
            return new TileCoordinate(0, 1);
        }
    },
    LEFT {
        @Override
        public TileCoordinate delta() {
            return new TileCoordinate(-1, 0);
        }
    },
    RIGHT {
        @Override
        public TileCoordinate delta() {
            return new TileCoordinate(1, 0);
        }
    };

    public abstract TileCoordinate delta();
}
