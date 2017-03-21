package com.javalife;

public class AutoLifeThread extends Thread {

    private volatile int delay = 1000;
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
