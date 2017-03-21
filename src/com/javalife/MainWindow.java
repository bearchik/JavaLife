package com.javalife;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;


public class MainWindow {
    private JFrame frame;

    private JPanel buttonPanel;

    private JButton btStart;
    private JButton btNextTurn;
    private JButton btReset;

    private JSlider speedSlider;

    private int generation = 1;

    Life life = new Life();
    Canvas cv = new Canvas(life);

    AutoLifeThread r = new AutoLifeThread(life, cv);

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

        speedSlider = new JSlider(JSlider.HORIZONTAL, 100, 2000, StaticValues.defSliderValue);
        speedSlider.addChangeListener(new MySliderListener());
        buttonPanel.add(speedSlider);
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer( 100 ), new JLabel("Fast") );
        labelTable.put( new Integer( 2000 ), new JLabel("Slow") );
        speedSlider.setLabelTable(labelTable);
        speedSlider.setPaintLabels(true);

        btStart = new JButton("Start");
        btStart.addActionListener(new ButtonActions());
        btNextTurn = new JButton("Next Turn");
        btNextTurn.addActionListener(new ButtonActions());
        btReset = new JButton("Reset");
        btReset.addActionListener(new ButtonActions());
        buttonPanel.add(btStart);
        buttonPanel.add(btNextTurn);
        buttonPanel.add(btReset);

        frame.getContentPane().add(cv);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    private class ButtonActions implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if ("Start".equals(e.getActionCommand()) || "Stop".equals(e.getActionCommand())) {
                if (r.isAlive()) {
                    r.interrupt();
                    btStart.setText("Start");
                    btNextTurn.setEnabled(true);
                    speedSlider.setValue(StaticValues.defSliderValue);
                } else {
                    r = new AutoLifeThread(life, cv);
                    r.start();
                    btStart.setText("Stop");
                    btNextTurn.setEnabled(false);
                }
            }

            if ("Next Turn".equals(e.getActionCommand())) {
                life.calculateLife();
                generation++;
                cv.repaint();
            }

            if ("Reset".equals(e.getActionCommand())) {
                if (r.isAlive()) {
                    r.interrupt();
                }
                life.clear();
                generation = 1;
                cv.repaint();
                btNextTurn.setEnabled(true);
                btStart.setText("Start");
                speedSlider.setValue(StaticValues.defSliderValue);
            }
        }
    }

    private class MySliderListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            if (r.isAlive()) {
                JSlider slider = (JSlider)e.getSource();
                if (!slider.getValueIsAdjusting()) {
                    r.changeDelay((int)slider.getValue());
                }
            }
        }
    }
}
