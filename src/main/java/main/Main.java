package main;

import javax.swing.*;

public class Main {
    public static JFrame frame = new JFrame("interface");
    public static CoreX coreX = new CoreX();

    public Main(){
        frame.setSize(850,800);
        frame.setLocation(500,0);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(coreX);
    }

    public static void main(String[] args) {
        new Main();
    }
}
