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

    final int corner = 6;
    static boolean isRunning = true;
    private static double dt = 0.01;

    private boolean moveD = false;
    private boolean moveR = false;
    private final int rad = 50;
    private final Random random= new Random();
    private final int dx = random.nextInt(5) + 3;
    private final int dy = random.nextInt(5) + 3;
    int x = 500;
    int y = 500;
    //テキストフィールド
    JTextField textField = new JTextField();
    //only number 1 ~ 100
    JTextField numField = new JTextField();

    JLabel label0 = new JLabel();
    JLabel label1 = new JLabel("Get Images About :");
    JLabel label2 = new JLabel("Max Images : ");
    //テキストと数値が記入されているか
    boolean b1 = false;
    boolean b2 = false;

    CoreX(){

        this.setLayout(null);
        this.setBackground(Color.black);

        JButton btn1 = new JButton("Run!");
        btn1.setBounds(10,10,100,30);
        btn1.addActionListener(e -> {
            isRunning = true;
            if (b1 &&  b2){
                Scraper.imgCount = 0;
                label0.setText("");
                Scraper.run();
                if(Scraper.isSuccess){
                    label0.setForeground(Color.green);
                    label0.setText("Success!!");
                }else{
                    label0.setForeground(Color.orange);
                    label0.setText("Failed!!");
                }
                b1 = false;
                b2 = false;
                label1.setText("");
                label2.setText("");
            }else if(!b1 && b2){
                label0.setForeground(Color.red);
                label0.setText("Please input a word.");
            } else if(b1){
                label0.setForeground(Color.red);
                label0.setText("Please input a number.");
            }else {
                label0.setForeground(Color.red);
                label0.setText("Please input a word and a number.");
            }
        });
        label1.setForeground(Color.CYAN);
        label2.setForeground(Color.CYAN);


        label0.setBounds(120,20,300,30);

        label1.setBounds(10,70,200,30);
        label2.setBounds(10,120,200,30);

        textField.setBounds(10,50,200,30);


        numField.setBounds(10,100,90,30);
        numField.setText("0");
        //0~9以外の文字を除外する
        ((AbstractDocument) numField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                fb.insertString(offset, string.replaceAll("[^0-9]", ""), attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                                AttributeSet attrs) throws BadLocationException {
                fb.replace(offset, length, text.replaceAll("[^0-9]", ""), attrs);
            }
        });
        
        JButton btn4 = new JButton();
        btn4.setBounds(100,100,30,30);
        btn4.addActionListener(e ->{
            int num = Integer.parseInt(numField.getText());
            Scraper.maxImages = num;
            System.out.println("Number of Images : " + num);
            label2.setText("Max Images : " + num);
            b1 = true;
            label0.setText("");
        });

        //テキスト用ボタン
        JButton btn3 = new JButton();
        btn3.setBounds(210,50,30,30);
        btn3.addActionListener(e -> {
            word = textField.getText();
            label1.setForeground(Color.red);
            label1.setText(word);
            Scraper.tag = textField.getText();

            label1.setForeground(Color.CYAN);
            label1.setText("Get Images About :" + word);

            b2 = true;
            label0.setText("");
        });

        //描画
        this.add(label0);
        this.add(label1);
        this.add(label2);
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
