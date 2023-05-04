package main;

import org.example.Scraper;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.Random;

public class CoreX extends JPanel {
    //検索ワードを設定
    public static String word;
    public static Boolean start = false;

    final int corner = 6;
    static boolean isRunning = true;
    private static double dt = 0.01;

    private boolean moveD = false;
    private boolean moveR = false;
    private int rad = 50;
    private final Random random= new Random();
    private final int dx = random.nextInt(5) + 3;
    private final int dy = random.nextInt(5) + 3;
    int x = 500;
    int y = 500;
    Scraper s = new Scraper(word);
    //テキストフィールド
    JTextField textField = new JTextField();
    JLabel label = new JLabel(word);

    CoreX(){

        this.setLayout(null);
        this.setBackground(Color.black);

        JButton btn1 = new JButton("Run!");
        btn1.setBounds(10,10,100,30);
        btn1.addActionListener(e -> {
            isRunning = true;
            Scraper.run();
            if(Scraper.isSuccess){
                label.setForeground(Color.green);
                label.setText("Success!!");
            }
        });


        label.setBounds(10,90,200,30);


        textField.setBounds(10,50,200,30);

        //only number 1 ~ 100
        JTextField numField = new JTextField();
        numField.setBounds(10,120,90,30);
        numField.setText("1");
        ((AbstractDocument) numField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string,
                                     AttributeSet attr) throws BadLocationException {
                fb.insertString(offset, string.replaceAll("[^0-9]", ""), attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                                AttributeSet attrs) throws BadLocationException {
                fb.replace(offset, length, text.replaceAll("[^0-9]", ""), attrs);
            }
        });
        
        JButton btn4 = new JButton("Set");
        btn4.setBounds(100,120,70,30);
        btn4.addActionListener(e ->{
            //get numfield value
            int num = Integer.parseInt(numField.getText());
            Scraper.maxImages = num;
            System.out.println(num);
        });

        JButton btn3 = new JButton();
        btn3.setBounds(210,50,30,30);
        btn3.addActionListener(e -> {
            word = textField.getText();
            label.setForeground(Color.red);
            label.setText(word);
            Scraper.tag = textField.getText();

            label.setForeground(Color.BLUE);
            label.setText("Get Images About:" + word);

            if (word != null){
                start = true;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

            }

        });

        this.add(label);
        this.add(btn1);
        this.add(btn3);
        this.add(btn4);
        this.add(numField);
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
    public void test(){
        System.out.println("test");
        System.out.println(word);
    }
}
