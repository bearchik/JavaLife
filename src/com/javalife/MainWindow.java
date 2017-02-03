package com.javalife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MainWindow {
    private JFrame frame;

    private JPanel buttonPanel;

    private int generation = 1;

    Life life = new Life();
    Canvas cv = new Canvas(life);

    Runnable r = new AutoLifeThread(life, cv);
    Thread tr = new Thread(r);

    public MainWindow() {

        createGUI();
    }

    public static void main(String[] args) {

        new MainWindow();

    }

    public void createGUI () {
        frame = new JFrame("Java Life");
        buttonPanel = new JPanel();
        buttonPanel.setSize(100,100);
        frame.setSize(600, 600);

        cv.setSize(500,500);

        JButton btStart = new JButton("Start");
        btStart.addActionListener(new StartAction());
        JButton btNextTurn = new JButton("Next Turn");
        btNextTurn.addActionListener(new NextTurnAction());
        JButton btReset = new JButton("Reset");
        btReset.addActionListener(new ResetAction());
        buttonPanel.add(btStart);
        buttonPanel.add(btNextTurn);
        buttonPanel.add(btReset);

        frame.getContentPane().add(cv);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            tr.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private class StartAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Start".equals(e.getActionCommand())) {
                if (tr.isAlive()) {
                    tr.interrupt();
                } else {
                    r = new AutoLifeThread(life, cv);
                    tr = new Thread(r);
                    tr.start();
                }
            }

        }
    }

    private class NextTurnAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Next Turn".equals(e.getActionCommand())) {
                life.calculateLife();
                generation++;
                cv.repaint();
            }

        }
    }

    private class ResetAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Reset".equals(e.getActionCommand())) {
                if (tr.isAlive()) {
                    tr.interrupt();
                }
                life.clear();
                generation = 1;
                cv.repaint();
            }

        }
    }
}
