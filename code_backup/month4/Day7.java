package com.ganten.tethys.daily.year2025.month4;

import java.util.Arrays;

public class Day7 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        byte[][] memory = new byte[nums.length][target + 1];
        return this.helper(nums, memory, 0, target);
    }

    private boolean helper(int[] nums, byte[][] memory, int index, int target) {

        if (index == nums.length) return target == 0;
        if (target == 0) return true;
        if (memory[index][target] != (byte) 0) return memory[index][target] == (byte) 1;
        // 不选择当前
        boolean a = this.helper(nums, memory, index + 1, target);
        // 选择当前
        boolean b = this.helper(nums, memory, index + 1, target - nums[index]);
        boolean result = a || b;
        memory[index][target] = result ? (byte) 1 : (byte) -1;
        return result;
    }

    public static void main(String[] args) {
        Day7 day7 = new Day7();
        System.out.println(day7.canPartition(new int[]{1, 5, 11, 5}));
    }
}

class Solution {
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        // 0 表示未计算，1 表示可以满足需求，-1 表示无法满足需求
        int[][] memory = new int[nums.length][target + 1];
        return dfs(0, target, nums, memory);
    }

    private boolean dfs(int index, int target, int[] nums, int[][] memory) {
        if (index >= nums.length) {
            return target == 0;
        }
        if (memory[index][target] != 0) {
            return memory[index][target] == 1;
        }
        if (target < nums[index]) {
            return false;
        }
        boolean ans = dfs(index + 1, target, nums, memory) || dfs(index + 1, target - nums[index], nums, memory);
        memory[index][target] = ans ? 1 : -1;
        return ans;
    }
}
