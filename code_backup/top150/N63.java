package com.ganten.tethys.top150;

public class N63 {
    public int uniquePathsWithObstacles(int[][] m) {
        int[][] dp = new int[m.length][m[0].length];
        // init dp
        dp[0][0] = m[0][0] == 1 ? 0 : 1;
        for (int i = 1; i < m.length; i++) {
            if (m[i][0] == 1) dp[i][0] = 0;
            else dp[i][0] = dp[i - 1][0];
        }
        for (int j = 1; j < m[0].length; j++) {
            if (m[0][j] == 1) dp[0][j] = 0;
            else dp[0][j] = dp[0][j - 1];
        }

        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[0].length; j++) {
                if (m[i][j] == 1) dp[i][j] = 0;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m.length - 1][m[0].length - 1];
    }
}
