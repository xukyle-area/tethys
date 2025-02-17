package com.ganten.tethys.leetcode75;

import java.util.ArrayList;
import java.util.List;

public class N54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int startX = 0;
        int startY = 0;
        boolean[][] used = new boolean[matrix.length][matrix[0].length];
        /*
         * 0: 从左到右，y++
         * 1: 从下到上，x++
         * 2: 从右到左，y--
         * 3: 从上到下，x--
         */
        List<Integer> list = new ArrayList<>();
        used[startX][startY] = true;
        list.add(matrix[startX][startY]);
        int order = 0;
        while (list.size() < matrix[0].length * matrix.length) {
            if (order == 0) {
                while (startY + 1 < matrix[0].length && !used[startX][startY + 1]) {
                    startY++;
                    used[startX][startY] = true;
                    list.add(matrix[startX][startY]);
                }
                if (startY == matrix[0].length - 1 || used[startX][startY + 1]) {
                    order++;
                }
            } else if (order == 1) {
                while (startX + 1 < matrix.length && !used[startX + 1][startY]) {
                    startX++;
                    used[startX][startY] = true;
                    list.add(matrix[startX][startY]);
                }
                if (startX == matrix.length - 1 || used[startX + 1][startY]) {
                    order++;
                }
            } else if (order == 2) {
                while (startY - 1 >= 0 && !used[startX][startY - 1]) {
                    startY--;
                    used[startX][startY] = true;
                    list.add(matrix[startX][startY]);
                }
                if (startY == 0 || used[startX][startY - 1]) {
                    order++;
                }
            } else if (order == 3) {
                while (startX - 1 >= 0 && !used[startX - 1][startY]) {
                    startX--;
                    used[startX][startY] = true;
                    list.add(matrix[startX][startY]);
                }
                if (startX == 0 || used[startX - 1][startY]) {
                    order = 0;
                }
            }
        }
        return list;
    }
}
