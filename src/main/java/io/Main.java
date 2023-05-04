package io;

import javax.swing.*;

public class Main {
    public static JFrame frame = new JFrame("interface");
    public static CoreX coreX = new CoreX();

    public Main(){
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(coreX);
    }

    public static void main(String[] args) {
        new Main();
    }
}
