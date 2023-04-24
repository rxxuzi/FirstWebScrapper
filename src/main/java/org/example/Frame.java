package org.example;

import javax.swing.*;

public class Frame extends JFrame {
    Frame(){
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
