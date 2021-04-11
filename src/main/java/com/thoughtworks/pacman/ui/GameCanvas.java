package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.ui.screens.IntroScreen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameCanvas extends Canvas implements KeyListener {

    private final Dimension dimension;
    private Screen currentScreen;
    private JPanel panel;
    private boolean isTwoPlayerMode = false;

    public GameCanvas(Dimension dimension, Game game) {
        this.dimension = dimension;
        this.currentScreen = new IntroScreen(game);
    }

    public void initialize(JPanel panel) {
        this.panel = panel;
        setBounds(new Rectangle(dimension));
        panel.add(this);
        setIgnoreRepaint(true);
        createBufferStrategy(2);
        addKeyListener(this);
        requestFocus();
    }

    public void draw() throws Exception {
        BufferStrategy strategy = getBufferStrategy();
        Graphics2D graphics = (Graphics2D) strategy.getDrawGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        graphics.setColor(Color.black);
        graphics.fill(new Rectangle(dimension));

        currentScreen = currentScreen.getNextScreen();
        currentScreen.draw(graphics);

        graphics.dispose();
        strategy.show();
    }

    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) {
        if (currentScreen instanceof IntroScreen && e.getKeyCode() == KeyEvent.VK_2) {
            if(isTwoPlayerMode) {
                dimension.setSize((dimension.getWidth() - Tile.SIZE * 2) / 2, dimension.getHeight());
                isTwoPlayerMode = false;
            }
            else {
                dimension.setSize((dimension.getWidth() - Tile.SIZE * 2) / 2, dimension.getHeight());
                isTwoPlayerMode = true;
            }
            setBounds(new Rectangle(dimension));
            panel.setPreferredSize(dimension);
            Container topLevelAncestor = panel.getTopLevelAncestor();
            if (topLevelAncestor instanceof JFrame) {
                ((JFrame) topLevelAncestor).pack();
            }
            ((IntroScreen) currentScreen).setMode(isTwoPlayerMode);
        }
        else {
            currentScreen.keyPressed(e);
        }    
    }

    public void keyReleased(KeyEvent e) { }
}
