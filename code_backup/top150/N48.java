package com.ganten.tethys.top150;

public class N48 {
    public void rotate(int[][] matrix) {
        // this.print(matrix);
        int size = matrix.length;
        // 沿 X 轴对称
        for (int i = 0; i < size / 2; i++) {
            for (int j = 0; j < size; j++) {
                swap(matrix, i, j, size - 1 - i, j);
            }
        }
        // this.print(matrix);
        // 沿 X 轴对称
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        // this.print(matrix);
    }

    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

    private void print(int[][] matrix) {
        System.out.println();
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        N48 n48 = new N48();
        n48.rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }
}
