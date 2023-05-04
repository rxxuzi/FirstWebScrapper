package main;

import javax.swing.*;

public class Main {
    public static JFrame frame = new JFrame("Scraping Hexagon");
    public static CoreX coreX = new CoreX();
    public static void main(String[] args) {
        //get java version
        System.out.println("JDK Version -> " + System.getProperty("java.version"));
        //create gui
        frame.setSize(850,800);
        frame.setLocation(500,0);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(coreX);
    }
}
