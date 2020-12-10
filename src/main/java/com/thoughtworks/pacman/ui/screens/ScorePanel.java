package com.thoughtworks.pacman.ui.screens;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class ScorePanel extends JFrame implements ActionListener {
    JPanel panel;
    JLabel user_label, message;
    JTextField userName_text;
    JButton submit;
    String userName;
    ArrayList<Entry> scoreList = new ArrayList<>();
    int score;

    public ScorePanel(int score) {
        user_label = new JLabel();
        user_label.setText("Your Score is High Enough to Join Top5! Enter Your Name");
        userName_text = new JTextField();
        submit = new JButton("Submit");
        panel = new JPanel(new GridLayout(3, 1));
        panel.add(user_label);
        panel.add(userName_text);
        message = new JLabel();
        panel.add(submit);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("Create new user");
        setSize(350, 150);
        setVisible(true);
        this.score = score;
    }

    /*public static void main(String[] args) {
        new ScorePanels();
    }*/
    @Override
    public void actionPerformed(ActionEvent ae) {
        userName = userName_text.getText();
        load();
        Entry current = new Entry(userName, score);
        scoreList.add(current);
        sortList();
        save();
        setVisible(false);
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
                ScorePanel.Entry current = new ScorePanel.Entry(name, score);
                scoreList.add(current);
            }
            scn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        String path = System.getProperty("user.dir");
        path = path.substring(0, path.lastIndexOf("\\"));
        path = path.substring(0, path.lastIndexOf("\\"));
        path = path + "\\score.txt";
        File file = new File(path);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(file));
            for (int i = 0; i < scoreList.size(); i++) {
                //if (scoreList.get(i).score.compareTo(0) != 0)
                if(i == 5) break;
                if (i == scoreList.size() - 1)
                    pw.write(scoreList.get(i).name + " " + scoreList.get(i).score.toString());
                else
                    pw.write(scoreList.get(i).name + " " + scoreList.get(i).score.toString() + "\n");
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sortList() {
        for (int i = 0; i < scoreList.size() - 1; i++) {
            for (int j = 0; j < scoreList.size() - i - 1; j++) {
                if (scoreList.get(j).score.compareTo(scoreList.get(j + 1).score) == -1) {
                    ScorePanel.Entry temp = scoreList.get(j);
                    scoreList.set(j, scoreList.get(j + 1));
                    scoreList.set(j + 1, temp);
                }
            }
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
