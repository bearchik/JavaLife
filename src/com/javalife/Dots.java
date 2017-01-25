package com.javalife;


public class Dots {
    private static boolean[][] dots = new boolean[50][50];

    public boolean[][] clear (boolean[][] dots) {

        for (int i = 0; i < dots.length; i++ ) {
            for (int j = 0; j < dots.length; j++ ) {
                dots[i][j] = false;
            }
        }

        return dots;
    }

}
