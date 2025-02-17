package com.ganten.tethys.leetcode75;

public class N643 {
    public double findMaxAverage(int[] nums, int k) {
        int[] sums = new int[nums.length - k + 1];
        int sum0 = 0;
        for (int i = 0; i < k; i++) {
            sum0 += nums[i];
        }
        sums[0] = sum0;
        int maxSum = sum0;
        for (int i = k; i < nums.length; i++) {
            int newSum = sums[i - k] + (nums[i] - nums[i - k]);
            sums[i - k + 1] = newSum;
            maxSum = Math.max(maxSum, newSum);
        }
        return (double) maxSum / k;
    }
}
