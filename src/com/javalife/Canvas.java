package com.javalife;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;


public class Canvas extends JPanel {

    private int POINTSIZE = 10; //px
    private Life life = new Life();

    public void setLife (Life life) {
        this.life = life;
    }

    public Canvas() {
        Canvas.MyMouseListener ml = new Canvas.MyMouseListener();
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
