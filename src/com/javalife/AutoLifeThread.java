package com.javalife;

import static java.lang.Thread.sleep;
public class AutoLifeThread implements Runnable {

    public volatile int delay = 1000;
    private Life life = new Life();
    private Canvas cv = new Canvas(life);

    public AutoLifeThread (Life life, Canvas cv) {
        this.life = life;
        this.cv = cv;
    }

    public void changeDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public void run() {
        while (true) {
            if(!Thread.interrupted()) {
                try {
                    life.calculateLife();
                    cv.repaint();
                    System.out.println(delay);
                    sleep(delay);
                } catch (InterruptedException e) {
                        return;
                }
            } else {
                return;
            }
        }
    }
}
