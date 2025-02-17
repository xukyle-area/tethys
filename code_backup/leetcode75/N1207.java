package com.ganten.tethys.leetcode75;

import java.util.HashSet;

public class N1207 {

    /**
     * -1000 <= arr[i] <= 1000
     * -1000 <= arr[i] + 1000 <= 1000
     * 0 <= arr[i] + 1000 <= 2000
     *
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences(int[] arr) {
        int[] counts = new int[2001];
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i] + 1000]++;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            if (count == 0) {
                continue;
            }
            if (set.contains(count)) {
                return false;
            }
            set.add(count);
        }
        return true;
    }
}
