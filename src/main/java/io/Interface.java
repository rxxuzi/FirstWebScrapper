package io;

import javax.swing.*;

public class Interface {
    public static JFrame frame = new JFrame("interface");
    public static CoreX coreX = new CoreX();

    public Interface(){
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(coreX);
    }

    public static void main(String[] args) {
        Interface ui = new Interface();
        ui.coreX.
    }
}
