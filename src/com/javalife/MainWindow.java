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
    private int generation = 1;

    DotArray dot = new DotArray();
    private static boolean[][] dots = new boolean[50][50];
    private static boolean[][] ngDots = new boolean[50][50];



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
        frame.setSize(600, 600);
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

    public int obhod(int xCoordinate, int yCoordinate) {
        int count = 0;

        System.out.println(xCoordinate + " " + yCoordinate);

        for (int k = -1; k < 2; k++ ) {
            for ( int m = -1; m < 2; m++) {
                if (k != 0 || m != 0 ) {

                    boolean edgeFlag = false;

                    if (xCoordinate == 49 && yCoordinate == 0) {
                        edgeFlag = false;
                    }

                    if (!edgeFlag && xCoordinate + k == -1 && yCoordinate + m == -1) {
                        if (dots[dots.length - 1][dots.length - 1]) {
                            count++;
                        }
                        edgeFlag = true;
                    }

                    if (!edgeFlag && xCoordinate + k > dots.length - 1 && yCoordinate + m > dots.length - 1) {
                        if (dots[0][0]) {
                            count++;
                        }
                        edgeFlag = true;
                    }


                    if (!edgeFlag && xCoordinate + k  > dots.length - 1 && yCoordinate + m == -1) {

                        if (dots[0][dots.length - 1]) {
                            count++;
                        }

                        edgeFlag = true;
                    }

                    if (!edgeFlag && xCoordinate + k  == -1 && yCoordinate + m >= dots.length) {

                        if (dots[dots.length - 1][0]) {
                            count++;
                        }

                        edgeFlag = true;
                    }

                    if (!edgeFlag && xCoordinate + k == -1 && yCoordinate + m < dots.length) {
                        if (dots[dots.length - 1][yCoordinate + m]) {
                            count++;
                        }

                        edgeFlag = true;
                    }

                    if (!edgeFlag && xCoordinate + k < dots.length && yCoordinate + m == -1) {
                        if (dots[xCoordinate + k][dots.length - 1]) {
                            count++;
                        }

                        edgeFlag = true;
                    }

                    if(!edgeFlag && xCoordinate + k >= 0 && yCoordinate + m >= dots.length ) {
                        if (dots[xCoordinate + k][0]) {
                            count++;
                        }

                        edgeFlag = true;
                    }

                    if (!edgeFlag && xCoordinate + k >= dots.length && yCoordinate + m >= 0 ) {
                        if (dots[0][yCoordinate + m]) {
                            count++;
                        }

                        edgeFlag = true;
                    }


                    if ( !edgeFlag ) {
                        if (dots[xCoordinate + k][yCoordinate + m]) {
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
                        ngDots[i][j] = false;
                    } else {
                        ngDots[i][j] = true;
                    }
                } else {
                    if (count == 3) {
                        ngDots[i][j] = true;
                    } else {
                        ngDots[i][j] = false;
                    }
                }
            }
        }
        dots = dot.copyArray(ngDots);
        ngDots = dot.clearArray(ngDots);
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
                generation++;
                lifePanel.repaint();
            }

        }
    }

    private class ResetAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Reset".equals(e.getActionCommand())) {
                System.out.println("reset");
                dots = dot.clearArray(dots);
                ngDots = dot.clearArray(ngDots);
                generation = 1;
                lifePanel.repaint();
            }

        }
    }

    class MyMouseListener implements MouseInputListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getPoint());
            if (!dots[e.getPoint().x/POINTSIZE][e.getPoint().y/POINTSIZE]) {
                dots[e.getPoint().x/POINTSIZE][e.getPoint().y/POINTSIZE] = true;
            } else {
                dots[e.getPoint().x/POINTSIZE][e.getPoint().y/POINTSIZE] = false;
            }

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
