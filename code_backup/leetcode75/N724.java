package com.ganten.tethys.leetcode75;

public class N724 {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int curSum = 0;
        if (sum - nums[0] == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            curSum += nums[i];
            if (curSum == (sum - curSum - nums[i + 1])) {
                return i + 1;
            }
        }
        return -1;
    }
}
