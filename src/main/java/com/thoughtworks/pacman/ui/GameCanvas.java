package com.thoughtworks.pacman.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas {
    public void initialize(JPanel panel) {
        setBounds(0, 0, 200, 200);
        panel.add(this);
        setIgnoreRepaint(true);
        createBufferStrategy(2);
    }

    public void draw() {
        BufferStrategy strategy = getBufferStrategy();
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, 200, 200);

        g.dispose();
        strategy.show();
    }
}
