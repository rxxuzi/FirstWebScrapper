package io;

import javax.swing.*;
import java.awt.*;

public class Interface {
    static JFrame frame = new JFrame("interface");
    static JPanel panel = new JPanel();
    static JLabel label = new JLabel();
    static JButton button = new JButton();
    static JTextField textField = new JTextField();
    static JTextArea textArea = new JTextArea();
    static JMenuBar menuBar = new JMenuBar();


    public static void main(String[] args) {
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(new CoreX());
    }

}
