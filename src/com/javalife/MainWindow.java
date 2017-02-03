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


    private class StartAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("start");
            if ("Start".equals(e.getActionCommand())) {
                Canvas cv = new Canvas();
                cv.setLife(life);
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
}
