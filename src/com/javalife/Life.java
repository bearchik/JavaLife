package com.javalife;

public class Life {

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


    private int obhod(boolean[][] dots, int xCoordinate, int yCoordinate) {
        int count = 0;

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
