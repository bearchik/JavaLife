package com.javalife;

import static java.lang.Thread.sleep;
public class AutoLifeThread implements Runnable {



    private Life life = new Life();
    private Canvas cv = new Canvas(life);

    public AutoLifeThread (Life life, Canvas cv) {
        this.life = life;
        this.cv = cv;
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        while (true) {
            if(!Thread.interrupted()) {
                try {
                    life.calculateLife();
                    cv.repaint();
                    sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            } else {
                return;
            }

        }

    }
}
