package com.thoughtworks.pacman.ui.presenters;

import com.thoughtworks.pacman.core.Position;
import com.thoughtworks.pacman.core.tiles.Wall;
import com.thoughtworks.pacman.ui.Presenter;

import java.awt.*;

public class WallPresenter implements Presenter {
    private final Position position;

    public WallPresenter(Wall wall, Position position) {
        this.position = position;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.blue);
        graphics.draw(new Rectangle(getPosition(), getDimension()));
    }

    public Point getPosition() {
        return position.times(TILE_SIZE).toPoint();
    }

    public Dimension getDimension() {
        return new Dimension(TILE_SIZE, TILE_SIZE);
    }
}
