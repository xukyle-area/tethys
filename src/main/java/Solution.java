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
        // 外层按照物品枚举
        for (int i = 0; i < n; i++) {
            // 内层按照容量进行枚举
            // 必须倒序遍历容量 j，否则数据会被覆盖掉，导致后面的数据用到不是上一轮的数据，而是当前轮的数据
            for (int j = v; j >= c[i]; j--) {
                // max 方法中使用到的 dp 数组就是上一轮的结果
                dp[j] = Math.max(dp[j], dp[j - c[i]] + w[i]);
            }
        }
        return dp[v];
    }

    public static void main(String[] args) {
        int v = 10;
        int[] c = {2, 3, 5, 7};
        int[] w = {1, 3, 5, 9};
        int result = Solution.solute(v, c, w);
        System.out.println(result);
    }
}
