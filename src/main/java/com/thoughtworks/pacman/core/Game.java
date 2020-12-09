package com.thoughtworks.pacman.core;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.thoughtworks.pacman.core.actors.Ghost;
import com.thoughtworks.pacman.core.actors.Pacman;
import com.thoughtworks.pacman.core.maze.Maze;
import com.thoughtworks.pacman.core.maze.MazeBuilder;
import com.thoughtworks.pacman.core.tiles.visitors.PacmanTileVisitor;
import com.thoughtworks.pacman.ui.screens.ScorePanel;

public class Game {
    private final Maze maze;
    private final Pacman pacman;
    private final Ghosts ghosts;
    private final PacmanTileVisitor pacmanTileVisitor;
    ArrayList<Entry> scoreList = new ArrayList<>();
    private boolean control = true;
    private boolean scoreControl = false;

    public Game() throws Exception {
        this(MazeBuilder.buildWalledMaze());
    }

    private Game(Maze maze) {
        this(maze, new Pacman(maze));
    }

    private Game(Maze maze, Pacman pacman) {
        this.maze = maze;
        this.pacman = pacman;
        this.ghosts = new Ghosts(this);
        this.pacmanTileVisitor = new PacmanTileVisitor();
    }

    public Game(Maze maze, Pacman pacman, Ghosts ghosts) {
        this.maze = maze;
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.pacmanTileVisitor = new PacmanTileVisitor();
    }

    public Maze getMaze() {
        return maze;
    }

    public Dimension getDimension() {
        return maze.getDimension();
    }

    public Pacman getPacman() {
        return pacman;
    }

    public Ghost[] getGhosts() {
        return new Ghost[]{ghosts.getBlinky(), ghosts.getPinky(), ghosts.getInky(), ghosts.getClyde()};
    }

    public void advance(long timeDeltaInMillis) {
        if (pacman.isDead()) {
            return;
        }

        ghosts.freeGhostsBasedOnScore(maze.getScore());

        pacman.advance(timeDeltaInMillis);
        ghosts.advance(timeDeltaInMillis);

        if (ghosts.killed(pacman)) {
            pacman.die();
        }

        Tile pacmanTile = maze.tileAt(pacman.getCenter().toTileCoordinate());
        pacmanTile.visit(pacmanTileVisitor);
    }

    public boolean won() {
        if (!maze.hasDotsLeft()) {
            if (control) {
                load();
                check(maze.getScore());
                if(scoreControl) {
                    ScorePanel panel = new ScorePanel(getMaze().getScore());
                }
                control = false;
                scoreControl = false;
            }
        }
        return !maze.hasDotsLeft();
    }

    public boolean lost() {
        if (pacman.isDead()) {
            if (control) {
                load();
                check(maze.getScore());
                if(scoreControl) {
                    ScorePanel panel = new ScorePanel(getMaze().getScore());
                }
                control = false;
                scoreControl = false;
            }
        }
        return pacman.isDead();
    }

    public void check(Integer score) {
        //System.out.println(scoreList.size());
        if (scoreList.size() >= 5) {
            if (scoreList.get(4).score.compareTo(score) == -1)
                scoreControl = true;
            else{}
                //System.out.println("current not enough high");
        } else if (scoreList.size() >= 0 && scoreList.size() < 5) {
            scoreControl = true;
        }
    }

    public void load() {
        String path = System.getProperty("user.dir");
        path = path.substring(0, path.lastIndexOf("\\"));
        path = path.substring(0, path.lastIndexOf("\\"));
        path = path + "\\score.txt";
        File file = new File(path);
        try {
            if (file.createNewFile()) {

                System.out.println("Score file has been created.");
            } else {

                //System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scn = null;
        try {
            scn = new Scanner(new FileInputStream(file));
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String name = line.substring(0, line.lastIndexOf(' '));
                Integer score = Integer.parseInt(line.substring(line.lastIndexOf(' ') + 1));
                Entry current = new Entry(name, score);
                scoreList.add(current);
            }
            scn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class Entry {
        String name;
        Integer score;

        public Entry(String name, Integer score) {
            this.name = name;
            this.score = score;
        }
    }

}