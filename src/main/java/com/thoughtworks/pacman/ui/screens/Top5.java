package com.thoughtworks.pacman.ui.screens;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
public class Top5 extends JFrame {
    JPanel panel;
    JLabel message;
    String Text = "";
    public Top5() {
        panel = new JPanel(new GridLayout(0, 1));
        load();
        String[] lines = Text.split("\n");
        for(int i = 0; i < lines.length; i++){
            message = new JLabel("   " + lines[i]);
            panel.add(message);
        }
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        add(panel, BorderLayout.CENTER);
        setTitle("Top 5 Scores");
        setSize(250,250);
        setVisible(true);
    }

    public void load() {
        String path = System.getProperty("user.dir");
        path = path.substring(0, path.lastIndexOf("\\"));
        path = path.substring(0, path.lastIndexOf("\\"));
        path = path + "\\score.txt";
        File file = new File(path);
        try {
            if (file.createNewFile()) {

            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scn = null;
        try {
            scn = new Scanner(new FileInputStream(file));
            int i = 0;
            while (scn.hasNextLine()) {
                if(i == 5) break;
                String line = scn.nextLine();
                Text += line + "\n";
                i++;
            }
            scn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
