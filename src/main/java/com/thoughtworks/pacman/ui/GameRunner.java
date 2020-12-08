package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.Game;
import com.thoughtworks.pacman.ui.SoundLoader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameRunner {
    private static final int FRAME_INTERVAL = 30;

    public static void main(String[] args) throws Exception {
        System.out.println("basliyoruz");
        GameRunner runner = new GameRunner();
        runner.initialize();
        runner.run();
    }

    private boolean open;
    private GameCanvas canvas;
    private Game game;
    private SoundLoader sl ;
    private void initialize() throws Exception {
        game = new Game();
        sl = new SoundLoader();
        sl.play();
        Dimension dimension = game.getDimension();
        canvas = new GameCanvas(dimension, game);

        JFrame container = new JFrame("Pacman");

        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(dimension);
        panel.setLayout(null);

        container.pack();
        container.setResizable(false);
        container.setVisible(true);
        open = true;

        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                open = false;
                System.exit(0);
            }
        });

        canvas.initialize(panel);
    }

    private void run() throws Exception {
        while (open) {
            canvas.draw();

            try {
                Thread.sleep(FRAME_INTERVAL);
            } catch (InterruptedException e) {
            }
        }
    }
}
