package com.ganten.tethys.leetcode75;

public class N1732 {
    public int largestAltitude(int[] gain) {
        int[] sums = new int[gain.length + 1];
        sums[0] = 0;
        int max = sums[0];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + gain[i - 1];
            max = Math.max(max, sums[i]);
        }
        return max;
    }
}
