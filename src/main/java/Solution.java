class Solution {
    /**
     * @param v first cost
     * @param u second cost
     * @param c first cost of each goods
     * @param d second cost of each goods
     * @param w value of each goods
     * @param s use limit of each goods
     *   1: 01 背包
     *  -1: no limit, 完全背包
     *  value: 多重背包
     * @return
     */
    public static int solute(int v, int u, int[] c, int[] d, int[] w, int[] s) {
        int n = w.length;
        int[][] dp = new int[v + 1][u + 1];

        for (int i = 0; i < n; i++) {
            if (s[i] == 1) {
                // 01背包，逆向
                for (int j = v; j >= 0; j--) {
                    for (int k = u; k >= 0; k--) {
                        if (j >= c[i] && k >= d[i]) {
                            dp[j][k] = Math.max(dp[j][k], dp[j - c[i]][k - d[i]] + w[i]);
                        }
                    }
                }
            } else if (s[i] == -1) {
                // 完全背包，正向
                for (int j = 0; j <= v; j++) {
                    for (int k = 0; k <= u; k++) {
                        if (j >= c[i] && k >= d[i]) {
                            dp[j][k] = Math.max(dp[j][k], dp[j - c[i]][k - d[i]] + w[i]);
                        }
                    }
                }
            } else {
                // 多重背包，逆向
                for (int j = v; j >= 0; j--) {
                    for (int k = u; k >= 0; k--) {
                        for (int m = 1; m <= s[i] && j >= m * c[i] && k >= m * d[i]; m++) {
                            dp[j][k] = Math.max(dp[j][k], dp[j - m * c[i]][k - m * d[i]] + m * w[i]);
                        }
                    }
                }
            }
        }
        return dp[v][u];
    }
}
