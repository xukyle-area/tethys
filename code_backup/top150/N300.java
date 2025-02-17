package com.ganten.tethys.top150;

import java.util.Arrays;

public class N300 {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int m = i - 1;
            int innerMax = 0;
            while (m >= 0) {
                if (nums[m] < nums[i]) innerMax = Math.max(innerMax, dp[m]);
                m--;
            }
            dp[i] = innerMax + 1;
        }
        System.out.println(Arrays.toString(dp));
        int max = 0;
        for (int i : dp) max = Math.max(max, i);
        return max;
    }

    public static void main(String[] args) {
        N300 n300 = new N300();
        n300.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3});
    }
}
