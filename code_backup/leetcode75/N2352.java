package com.ganten.tethys.leetcode75;

public class N2352 {
    public int equalPairs(int[][] grid) {
        int size = grid.length;
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int m = 0;
                while (grid[i][m] == grid[m][j]) {
                    if (++m == size) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
}
