package com.thoughtworks.pacman.ui.presenters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.ui.Presenter;

public class PacmanPresenter implements Presenter {
    static final int DIAMETER = 20;
    static final int MOUTH_CLOSED = 360;
    static final int MOUTH_OPENED = 280;

    private final Pacman pacman;
    private long lastFrame;
    private int deadFrame;

    public PacmanPresenter(Pacman pacman) {
        this.pacman = pacman;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.yellow);
        Rectangle bounds = getBounds();
        graphics.fillArc(bounds.x, bounds.y, bounds.width, bounds.height, getStartAngle(), getArcAngle());
    }

    int getStartAngle() {
        Direction direction = pacman.isMoving() ? pacman.getDirection() : pacman.getPreviousDirection();
        return direction.getStartAngle();
    }

    int getArcAngle() {
        if (pacman.isDead()) {
            return deadFrame > MOUTH_CLOSED / 10 ? 0 : MOUTH_CLOSED - deadFrame++ * 10;
        }
        if (!pacman.isMoving()) {
            return MOUTH_OPENED;
        }
        return lastFrame++ % 10 < 5 ? MOUTH_CLOSED : MOUTH_OPENED;
    }

    Rectangle getBounds() {
        int radius = DIAMETER / 2;
        SpacialCoordinate upperLeft = pacman.getCenter().add(new SpacialCoordinate(-radius, -radius));
        return new Rectangle(upperLeft.toPoint(), new Dimension(DIAMETER, DIAMETER));
    }
}
