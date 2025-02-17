package com.ganten.tethys.top150;

import java.util.Arrays;

public class N322 {
    /**
     * dp[i] 表示凑齐 i 元的金额，需要的最少的 coin 数量
     * dp[i] = min{dp[i],dp[i-coins[i]+1}
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 20163844);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i < amount + 1; i++) {
                dp[i] = Math.max(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == 20163844 ? -1 : dp[amount];
    }
}
