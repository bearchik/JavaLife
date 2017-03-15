package com.javalife;

public class Life extends AbstractLife{

    private static boolean[][] dots = new boolean[50][50];
    private static boolean[][] ngDots = new boolean[50][50];
    DotArray dot = new DotArray();

    public Life () {}

    public boolean get( int x, int y) {
        return dots[x][y];
    }

    public void setTrue( int x, int y) {
        dots[x][y] = true;
    }

    public void setFalse( int x, int y) {
        dots[x][y] = false;
    }

    public int worldLength() {
        return dots.length;
    }

    public void clear() {
        dots = dot.clearArray(dots);
        ngDots = dot.clearArray(ngDots);
    }


    public void calculateLife() {
        for (int i = 0; i < dots.length; i++ ) {
            for (int j = 0; j < dots.length; j++ ) {
                Life life = new Life();
                int count = life.obhod(dots, i, j);
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

}
