package com.thoughtworks.pacman.core.tiles;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import static java.awt.geom.Arc2D.OPEN;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;

public enum WallType {
    BOTTOM_LEFT {
        @Override
        public Shape getShape(SpacialCoordinate center) {
            final SpacialCoordinate circleCenter = center.add(new SpacialCoordinate(0, -Tile.SIZE));
            return new Arc2D.Float(getArcBounds(circleCenter), 180, 90, OPEN);
        }
    },
    BOTTOM_RIGHT {
        @Override
        public Shape getShape(SpacialCoordinate center) {
            final SpacialCoordinate circleCenter = center.add(new SpacialCoordinate(-Tile.SIZE, -Tile.SIZE));
            return new Arc2D.Float(getArcBounds(circleCenter), 270, 90, OPEN);
        }
    },
    TOP_LEFT {
        @Override
        public Shape getShape(SpacialCoordinate center) {
            return new Arc2D.Float(getArcBounds(center), 90, 90, OPEN);
        }
    },
    TOP_RIGHT {
        @Override
        public Shape getShape(SpacialCoordinate center) {
            final SpacialCoordinate circleCenter = center.add(new SpacialCoordinate(-Tile.SIZE, 0));
            return new Arc2D.Float(getArcBounds(circleCenter), 0, 90, OPEN);
        }
    },
    VERTICAL {
        @Override
        public Shape getShape(SpacialCoordinate center) {
            final SpacialCoordinate topMiddle = center.add(new SpacialCoordinate(0, -Tile.SIZE / 2));
            final SpacialCoordinate bottomMiddle = topMiddle.add(new SpacialCoordinate(0, Tile.SIZE));
            return new Line2D.Float(topMiddle.toPoint(), bottomMiddle.toPoint());
        }
    },
    HORIZONTAL {
        @Override
        public Shape getShape(SpacialCoordinate center) {
            final SpacialCoordinate centerLeft = center.add(new SpacialCoordinate(-Tile.SIZE / 2, 0));
            final SpacialCoordinate centerRight = centerLeft.add(new SpacialCoordinate(Tile.SIZE, 0));
            return new Line2D.Float(centerLeft.toPoint(), centerRight.toPoint());
        }
    };

    private static Rectangle getArcBounds(SpacialCoordinate center) {
        return new Rectangle(center.toPoint(), new Dimension(Tile.SIZE, Tile.SIZE));
    }

    public static WallType fromMazeTile(String value) {
        try {
            return WallType.values()[Integer.parseInt(value) - 1];
        } catch (NumberFormatException nfe) {
            return WallType.HORIZONTAL;
        }
    }

    public abstract Shape getShape(SpacialCoordinate center);
}
