package com.ganten.tethys.top150;

public class N200 {

    private boolean[][] used;
    private char[][] grid;

    public int numIslands(char[][] grid) {
        this.used = new boolean[grid.length][grid[0].length];
        this.grid = grid;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                count += this.helper(i, j);
            }
        }
        return count;
    }

    public int helper(int i, int j) {
        if (i < 0 || i > grid.length) return 0;
        if (j < 0 || j > grid[0].length) return 0;
        if (grid[i][j] == '0') return 0;
        if (used[i][j]) return 0;
        used[i][j] = true;
        this.helper(i - 1, j);
        this.helper(i + 1, j);
        this.helper(i, j - 1);
        this.helper(i, j + 1);
        return 1;
    }

}
