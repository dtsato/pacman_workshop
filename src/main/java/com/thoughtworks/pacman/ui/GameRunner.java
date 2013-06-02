package com.thoughtworks.pacman.ui;

import com.thoughtworks.pacman.core.Game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameRunner {
    public static void main(String[] args) throws Exception {
        GameRunner runner = new GameRunner();
        runner.initialize();
        runner.run();
    }

    private boolean open;
    private GameCanvas canvas;

    private void initialize() throws Exception {
        Game game = new Game();
        canvas = new GameCanvas(game);

        JFrame container = new JFrame();

        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(canvas.getDimension());
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

    private void run() {
        while (open) {
            canvas.draw();
        }
    }
}
