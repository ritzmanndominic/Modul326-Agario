package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame runner = new JFrame("AGAR.IO");
        runner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        runner.setLocationRelativeTo(null);
        runner.setSize(1000, 1000);
        runner.setLayout(null);
        runner.setLocation(0, 0);

        BlobManager theGame = new BlobManager();
        theGame.setSize(1000, 1000);
        theGame.setLocation(0, 0);
        runner.getContentPane().add(theGame);
        runner.setVisible(true);
        runner.addKeyListener(theGame);
        theGame.run();
    }
}
