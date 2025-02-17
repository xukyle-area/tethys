package com.ganten.tethys.top150;

public class N73 {
    public void setZeroes(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    this.setZero(matrix, i, j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] <= 0) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private void setZero(int[][] matrix, int i, int j) {
        for (int k = 0; k < matrix.length; k++) {
            int x = matrix[k][j];
            if (x != 0) {
                matrix[k][j] = -1;
            }
        }
        for (int k = 0; k < matrix[0].length; k++) {
            int x = matrix[i][k];
            if (x != 0) {
                matrix[i][k] = -1;
            }
        }
    }
}
