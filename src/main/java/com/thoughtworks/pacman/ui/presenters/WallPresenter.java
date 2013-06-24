package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.tiles.Wall;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.util.EnumSet;

import static java.awt.geom.Arc2D.OPEN;
import static java.awt.geom.Line2D.Float;

public class WallPresenter implements Presenter {
    private final Wall wall;

    private static enum WallType {
        VERTICAL {
            @Override
            EnumSet<Direction> getNeighbours() {
                return EnumSet.of(Direction.UP, Direction.DOWN);
            }

            @Override
            Shape getShape(Wall wall) {
                final SpacialCoordinate topMiddle = wall.getCenter().add(new SpacialCoordinate(0, -Tile.SIZE / 2));
                final SpacialCoordinate bottomMiddle = topMiddle.add(new SpacialCoordinate(0, Tile.SIZE));
                return new Float(topMiddle.toPoint(), bottomMiddle.toPoint());
            }
        },
        HORIZONTAL {
            @Override
            EnumSet<Direction> getNeighbours() {
                return EnumSet.of(Direction.LEFT, Direction.RIGHT);
            }

            @Override
            Shape getShape(Wall wall) {
                final SpacialCoordinate centerLeft = wall.getCenter().add(new SpacialCoordinate(-Tile.SIZE / 2, 0));
                final SpacialCoordinate centerRight = centerLeft.add(new SpacialCoordinate(Tile.SIZE, 0));
                return new Float(centerLeft.toPoint(), centerRight.toPoint());
            }
        },
        TOP_LEFT {
            @Override
            EnumSet<Direction> getNeighbours() {
                return EnumSet.of(Direction.DOWN, Direction.RIGHT);
            }

            @Override
            Shape getShape(Wall wall) {
                final SpacialCoordinate circleCenter = wall.getCenter();
                return new Arc2D.Float(getArcBounds(circleCenter), 90, 90, OPEN);
            }
        },
        BOTTOM_LEFT {
            @Override
            EnumSet<Direction> getNeighbours() {
                return EnumSet.of(Direction.UP, Direction.RIGHT);
            }

            @Override
            Shape getShape(Wall wall) {
                final SpacialCoordinate circleCenter = wall.getCenter().add(new SpacialCoordinate(0, -Tile.SIZE));
                return new Arc2D.Float(getArcBounds(circleCenter), 180, 90, OPEN);
            }
        },
        TOP_RIGHT {
            @Override
            EnumSet<Direction> getNeighbours() {
                return EnumSet.of(Direction.DOWN, Direction.LEFT);
            }

            @Override
            Shape getShape(Wall wall) {
                final SpacialCoordinate circleCenter = wall.getCenter().add(new SpacialCoordinate(-Tile.SIZE, 0));
                return new Arc2D.Float(getArcBounds(circleCenter), 0, 90, OPEN);
            }
        },
        BOTTOM_RIGHT {
            @Override
            EnumSet<Direction> getNeighbours() {
                return EnumSet.of(Direction.UP, Direction.LEFT);
            }

            @Override
            Shape getShape(Wall wall) {
                final SpacialCoordinate circleCenter = wall.getCenter().add(new SpacialCoordinate(-Tile.SIZE, -Tile.SIZE));
                return new Arc2D.Float(getArcBounds(circleCenter), 270, 90, OPEN);
            }
        };

        private static Rectangle getArcBounds(SpacialCoordinate center) {
            return new Rectangle(center.toPoint(), new Dimension(Tile.SIZE, Tile.SIZE));
        }

        abstract Shape getShape(Wall wall);
        abstract EnumSet<Direction> getNeighbours();

        static WallType getType(Wall wall) {
            EnumSet<Direction> neighbourWalls = EnumSet.noneOf(Direction.class);
            for (Direction direction : Direction.values()) {
                if (wall.neighbour(direction) instanceof Wall) {
                    neighbourWalls.add(direction);
                }
            }

            for(WallType type : values()) {
                if (neighbourWalls.containsAll(type.getNeighbours())) {
                    return type;
                }
            }

            return WallType.HORIZONTAL;
        }
    }

    public WallPresenter(Wall wall) {
        this.wall = wall;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.blue);
        graphics.setStroke(new BasicStroke(2.5f));
        graphics.draw(WallType.getType(wall).getShape(wall));
    }

    public Point getTileCoordinate() {
        int delta = Tile.SIZE / 2;
        return wall.getCenter().add(new SpacialCoordinate(-delta, -delta)).toPoint();
    }

    public Dimension getDimension() {
        return new Dimension(Tile.SIZE, Tile.SIZE);
    }
}
