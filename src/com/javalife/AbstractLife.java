package com.javalife;

public abstract class AbstractLife implements Obhod{
    public int obhod(boolean[][] dots, int xCoordinate, int yCoordinate) {
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
}
