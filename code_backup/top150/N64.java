package com.ganten.tethys.top150;

public class N64 {
    public int minPathSum(int[][] m) {
        int[][] dp = new int[m.length][m[0].length];
        // init dp
        dp[0][0] = m[0][0];
        for (int i = 1; i < m.length; i++)
            dp[i][0] = dp[i - 1][0] + m[i][0];
        for (int j = 1; j < m[0].length; j++)
            dp[0][j] = dp[0][j - 1] + m[0][j];

        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[0].length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m.length - 1][m[0].length - 1];
    }
}
