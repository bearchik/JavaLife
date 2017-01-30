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

    Life life = new Life();



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
            for (int i = 0; i < life.worldLength(); i++) {
                for(int j = 0; j < life.worldLength(); j++) {
                    if(life.get(i,j)) {
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
                life.calculateLife();
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
                life.clear();
                generation = 1;
                lifePanel.repaint();
            }

        }
    }

    class MyMouseListener implements MouseInputListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getPoint());
            if (!life.get(e.getPoint().x/POINTSIZE,e.getPoint().y/POINTSIZE )) {
                life.setTrue(e.getPoint().x/POINTSIZE,e.getPoint().y/POINTSIZE );
            } else {
                life.setFalse(e.getPoint().x/POINTSIZE,e.getPoint().y/POINTSIZE );
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
