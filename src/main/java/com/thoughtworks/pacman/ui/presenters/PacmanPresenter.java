package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PacmanPresenter implements Presenter {
    private static final int MOUTH_CLOSED = 360;
    private static final int MOUTH_OPENED = 280;
    private static final Font FONT = new Font("Monospaced", Font.BOLD, Tile.SIZE);

    private final Pacman pacman;
    private long lastFrame;
    private int deadFrame;

    public PacmanPresenter(Pacman pacman) {
        this.pacman = pacman;
    }

    @Override
    public void draw(Graphics2D graphics) {
        drawPacman(graphics);
        drawScore(graphics);
    }

    private void drawPacman(Graphics2D graphics) {
        graphics.setColor(Color.yellow);
        Rectangle bounds = getBounds();
        graphics.fillArc(bounds.x, bounds.y, bounds.width, bounds.height, pacman.getDirection().getStartAngle(), getArcAngle());
    }

    private void drawScore(Graphics2D graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(FONT);
//        TODO: WEIRD! This causes the ghosts to "jump out" of the house
//        graphics.drawString(String.format("%2d", pacman.getScore()), Tile.SIZE * 5, Tile.SIZE * 2);
    }

    public int getArcAngle() {
        if (pacman.isDead()) {
            return deadFrame > MOUTH_CLOSED / 10 ? 0 : MOUTH_CLOSED - deadFrame++ * 10;
        } else {
            return lastFrame++ % 10 < 5 ? MOUTH_CLOSED : MOUTH_OPENED;
        }
    }

    public Rectangle getBounds() {
        int radius = Tile.SIZE / 2;
        SpacialCoordinate upperLeft = pacman.getCenter().add(new SpacialCoordinate(-radius, -radius));
        return new Rectangle(upperLeft.toPoint(), new Dimension(Tile.SIZE, Tile.SIZE));
    }
}
