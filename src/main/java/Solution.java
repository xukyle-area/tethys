class Solution {
    /**
     * 对于数组 s，可以有以下情况：
     * 1. 对于01背包，s 中的所有的值都是1
     * 2. 对于完全背包，s 中的所有的值都是-1，表示无上限
     * 3. 对于多重背包，s[i] 表示第i件物品可以取的上限
     *
     * @param v 背包空间
     * @param c 物品的体积
     * @param w 物品的价值
     * @param s 物品的件数
     * @return 最大价值
     */
    public static int solute(int v, int[] c, int[] w, int[] s) {
        int n = w.length;
        int[] dp = new int[v + 1];
        // 外层循环枚举每个物品
        for (int i = 0; i < n; i++) {
            if (s[i] == 1) {
                // 01背包：倒序，O(v)
                for (int j = v; j >= c[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - c[i]] + w[i]);
                }
            } else if (s[i] == -1) {
                // 完全背包：正序，O(v) - 快很多！
                for (int j = c[i]; j <= v; j++) {
                    dp[j] = Math.max(dp[j], dp[j - c[i]] + w[i]);
                }
            } else {
                // 多重背包：倒序+枚举，O(v·s[i])
                for (int j = v; j >= c[i]; j--) {
                    for (int k = 1; k <= s[i] && j >= k * c[i]; k++) {
                        dp[j] = Math.max(dp[j], dp[j - k * c[i]] + k * w[i]);
                    }
                }
            }
        }
        return dp[v];
    }
}
