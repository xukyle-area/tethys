class Solution {
    /**
     * @param v 背包空间
     * @param c 物品的体积
     * @param w 物品的价值
     * @return 最大价值
     */
    public static int solute(int v, int[] c, int[] w) {
        int n = w.length;
        int[] dp = new int[v + 1];
        for (int i = 0; i < n; i++) {
            for (int j = v; j >= c[i]; j--) {
                for (int k = 0; j >= k * c[i]; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * c[i]] + k * w[i]);
                }
            }
        }
        return dp[v];
    }
}
