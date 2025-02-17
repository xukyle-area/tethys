package com.ganten.tethys.daily.year2025.month3;

import java.util.Arrays;

/**
 * 2033
 */
public class Day26 {
    public int minOperations(int[][] grid, int k) {
        int[] rebuildGrid = this.rebuildGrid(grid, k);
        if (rebuildGrid == null) {
            return -1;
        }
        Arrays.sort(rebuildGrid);
        // choose the mid-index
        int midIndex = rebuildGrid.length / 2;
        int midValue = rebuildGrid[midIndex];
        int count = 0;
        for (int i : rebuildGrid) {
            int cur = midValue - i;
            count += (cur >= 0 ? cur : -cur);
        }
        return count;
    }

    private int[] rebuildGrid(int[][] grid, int k) {
        int min = this.findMin(grid);
        int index = 0;
        int[] result = new int[grid.length * grid[0].length];
        for (int[] ints : grid) {
            for (int anInt : ints) {
                int m = anInt - min;
                if (m % k != 0) {
                    return null;
                }
                result[index] = m / k;
                index++;
            }
        }
        return result;
    }

    private int findMin(int[][] grid) {
        int min = Integer.MAX_VALUE;
        for (int[] ints : grid) {
            for (int anInt : ints) {
                min = Math.min(min, anInt);
            }
        }
        return min;
    }
}
