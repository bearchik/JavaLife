package com.javalife;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;


public class MainWindow {
    private JFrame frame;
    private JPanel lifePanel;
    private JPanel buttonPanel;

    private int POINTSIZE = 10; //px

    private static boolean[][] dots = new boolean[50][50];


    public MainWindow() {

        createGUI();
    }

    public static void main(String[] args) {

        new MainWindow();

    }

    public void createGUI () {
        frame = new JFrame("Java Life");
        lifePanel = new JPanel();
        buttonPanel = new JPanel();
        lifePanel.setSize(500,500);
        buttonPanel.setSize(100,100);
        frame.setSize(500, 600);
        JButton btStart = new JButton("Start");
        btStart.addActionListener(new StartAction());
        JButton btNextTurn = new JButton("Next Turn");
        btNextTurn.addActionListener(new NextTurnAction());
        JButton btReset = new JButton("Reset");
        btReset.addActionListener(new ResetAction());
        buttonPanel.add(btStart);
        buttonPanel.add(btNextTurn);
        buttonPanel.add(btReset);
        frame.getContentPane().add(lifePanel);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public int obhod(int i, int j) {
        int count = 0;

        for (int k = -1; k < 2; k++ ) {
            for ( int m = -1; m < 2; m++) {
                if (k != 0 || m != 0 ) {

                    boolean flag = false;

                    if ( i + k == -1 && j + m == -1 ) {
                        if (dots[dots.length - 1][dots.length - 1]) {
                            count++;
                        }

                        flag = true;

                    }

                    if (i + k > dots.length  -1 && j + m > dots.length - 1) {
                        if (dots[0][0]) {
                            count++;
                        }

                        flag = true;

                    }

                    if (i + k == -1 && j + m >= 0) {
                        if (dots[dots.length - 1][0]) {
                            count++;
                        }

                        flag = true;

                    }

                    if (i + k > dots.length-1 && j + m <= dots.length - 1) {
                        if (dots[0][j]) {
                            count++;
                        }

                        flag = true;

                    }

                    if (i + k >= 0 && j + m == -1) {
                        if (dots[0][dots.length - 1]) {
                            count++;
                        }

                        flag = true;
                    }

                    if (i + k >= 0 && j + m > dots.length - 1) {
                        if (dots[i][dots.length - 1]) {
                            count++;
                        }

                        flag = true;

                    }

                    if ( !flag ) {
                        if (dots[i + k][j + m]) {
                            count++;
                        }
                    }

                }
            }
        }

        return count;
    }

    public void calculateLife() {
        for (int i = 0; i < dots.length; i++ ) {
            for (int j = 0; j < dots.length; j++ ) {
                int count = obhod(i, j);
                if (dots[i][j]) {
                    if (count < 2 || count > 3) {
                        dots[i][j] = false;
                    }

                } else {
                    if (count == 3) {
                        dots[i][j] = true;
                    }
                }
            }
        }
    }

    class Canvas extends JPanel{

        public Canvas() {
            MyMouseListener ml = new MyMouseListener();
            addMouseListener(ml);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setPaint(Color.LIGHT_GRAY);
            for (int i = 0; i < 50; i++) {
                g2d.drawLine(i * POINTSIZE, 0, i * POINTSIZE, 500  );
                g2d.drawLine(0, i * POINTSIZE,500, i * POINTSIZE);
            }


            g2d.setPaint(Color.RED);
            for (int i = 0; i < dots.length; i++) {
                for(int j = 0; j < dots.length; j++) {
                    if(dots[i][j]) {
                        g2d.fillOval(i * POINTSIZE, j * POINTSIZE, POINTSIZE, POINTSIZE);
                    }
                }
            }
            repaint();

        }
    }


    private class StartAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("start");
            if ("Start".equals(e.getActionCommand())) {
                Canvas cv = new Canvas();
                cv.setSize(500,500);
                lifePanel.add(cv);
                lifePanel.repaint();
            }

        }
    }

    private class NextTurnAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Next Turn".equals(e.getActionCommand())) {
                calculateLife();
                lifePanel.repaint();
            }

        }
    }

    private class ResetAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Reset".equals(e.getActionCommand())) {
                System.out.println("reset");
                for (int i = 0; i < dots.length; i++ ) {
                    for (int j = 0; j < dots.length; j++ ) {
                        dots[i][j] = false;
                    }
                }
                lifePanel.repaint();
            }

        }
    }

    class MyMouseListener implements MouseInputListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getPoint());
            dots[e.getPoint().x/POINTSIZE][e.getPoint().y/POINTSIZE] = true;
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }



}
