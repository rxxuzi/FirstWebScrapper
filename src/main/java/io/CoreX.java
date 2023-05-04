package io;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CoreX extends JPanel {
    //検索ワードを設定
    public static String word;
    public static Boolean start = false;

    final int corner = 6;
    static boolean isRunning = false;
    private static double dt = 0.01;

    private boolean moveD = false;
    private boolean moveR = false;
    private int rad = 50;
    private final Random random= new Random();
    private final int dx = random.nextInt(5) + 3;
    private final int dy = random.nextInt(5) + 3;
    int x = 500;
    int y = 500;

    CoreX(){
        this.setLayout(null);
        this.setBackground(Color.black);
        JButton btn1 = new JButton("Start");
        btn1.setBounds(10,10,100,30);
        btn1.addActionListener(e -> {
            isRunning = true;
        });
        JButton btn2 = new JButton("Stop");
        btn2.setBounds(120,10,100,30);
        btn2.addActionListener(e -> {
            isRunning = false;
        });

        JLabel label = new JLabel(word);
        label.setBounds(10,90,200,30);

        JTextField textField = new JTextField();
        textField.setBounds(10,50,200,30);

        JButton btn3 = new JButton();
        btn3.setBounds(210,50,30,30);
        btn3.addActionListener(e -> {
            word = textField.getText();
            label.setForeground(Color.red);
            label.setText(word);
            if (word != null){
                start = true;
            }
        });

        this.add(label);
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(textField);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    private void draw(Graphics g){

        drawBall(g);
        g.setColor(Color.white);
        g.setColor(Color.GREEN);

        int rad = 100;
        for (int i = 0 ; i < 6 ; i ++){
            double x = rad * Math.cos(i * Math.PI / 3 );
            double y = rad * Math.sin(i * Math.PI / 3 );
            drawHexagon(g, x, y);
        }
        drawHexagon(g,0,0);


        g.setColor(Color.red);
        sleep();
    }


    private void drawBall(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x,y,rad,rad);
        moveBall();
    }

    private void moveBall() {
        if(isRunning){
            if(moveD) y += dy; else y -= dy;
            if(moveR) x += dx; else x -= dx;
        }

        if(x >= getWidth() - rad){
            moveR = false;
        }
        if(x <= 0){
            moveR = true;
        }
        if(y >= getHeight() - rad){
            moveD = false;
        }
        if(y <= 0){
            moveD = true;
        }
    }

    private void drawHexagon(Graphics g , double dx , double dy){
        g.setColor(Color.WHITE);
        int[] x = new int[corner];
        int[] y = new int[corner];

        for(int i = 0; i < corner; i++){
            double sita = 2 * Math.PI;
            x[i] = (int) (100 * Math.cos(i * sita / corner + dt) + dx + getWidth()/2);
            y[i] = (int) (100 * Math.sin(i * sita / corner + dt) + dy + getHeight()/2);
        }
        g.drawPolygon(x, y, corner);
    }
    private void sleep(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(isRunning) {
            dt += 0.04;
        }
        repaint();
    }
}
