package com.thoughtworks.pacman.core;

public enum Direction {
    UP {
        @Override
        public Position delta() {
            return new Position(0, -1);
        }
    },
    DOWN {
        @Override
        public Position delta() {
            return new Position(0, 1);
        }
    },
    LEFT {
        @Override
        public Position delta() {
            return new Position(-1, 0);
        }
    },
    RIGHT {
        @Override
        public Position delta() {
            return new Position(1, 0);
        }
    };

    public abstract Position delta();
}
