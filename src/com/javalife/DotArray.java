package com.javalife;

public class DotArray {
        private static boolean[][] dotsArray = new boolean[50][50];
        public boolean[][] clearArray (boolean[][] dotsArray) {

            for (int i = 0; i < dotsArray.length; i++ ) {
                for (int j = 0; j < dotsArray.length; j++ ) {
                    dotsArray[i][j] = false;
                }
            }
            return dotsArray;
        }

        public boolean[][] copyArray (boolean[][] srcArray) {
            boolean[][] dstArray = new boolean[srcArray.length][srcArray.length];

            for (int i = 0; i < srcArray.length; i++ ) {
                for (int j = 0; j < srcArray.length; j++ ) {
                    dstArray[i][j] = srcArray[i][j];
                }
            }

            return dstArray;
        }
}
