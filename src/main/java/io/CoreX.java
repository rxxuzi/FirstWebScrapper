package io;

import javax.swing.*;
import java.awt.*;

public class CoreX extends JPanel {
    final int corner = 6;
    private static double dt = 0.01;
    CoreX(){
        this.setLayout(null);
        this.setBackground(Color.black);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    private void draw(Graphics g){

        g.setColor(Color.white);
        g.setColor(Color.GREEN);

        int l = 50;
        int rad = l * 2;
        int x = (int) (Math.cos(dt) * 2 * l) + getWidth()/2 - l;
        int y = (int) (Math.sin(dt) * 2 * l) + getHeight()/2 - l;
        g.fillOval(x, y,rad,rad);

        drawHexagon(g);
        g.setColor(Color.red);
        sleep();
    }
    private void drawHexagon(Graphics g){
        g.setColor(Color.WHITE);
        int[] x = new int[corner];
        int[] y = new int[corner];

        for(int i = 0; i < corner; i++){
            double sita = 2 * Math.PI;
            x[i] = (int) (100 * Math.cos(i * sita / corner + dt)) + getWidth()/2;
            y[i] = (int) (100 * Math.sin(i * sita / corner + dt)) + getHeight()/2;
        }
        g.drawPolygon(x, y, corner);
    }
    private void sleep(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dt += 0.04;
        repaint();
    }
}
