package com.ganten.tethys.daily.year2025.month4;

public class Day3 {
    public static void main(String[] args) {
        Day3 day3 = new Day3();
        System.out.println(day3.maximumTripletValue(new int[]{1, 1, 1}));
    }

    public long maximumTripletValue(int[] nums) {
        long[] maxArray = new long[nums.length];
        long maxPost = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            maxArray[i] = Math.max(maxPost, nums[i]);
            maxPost = maxArray[i];
        }
        long result = Long.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[maxIndex] < nums[i]) maxIndex = i;
            long delta = nums[maxIndex] - nums[i];
            result = Math.max(delta * maxArray[i + 1], result);
        }
        return result;
    }
}
