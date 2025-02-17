package com.ganten.tethys.daily.year2025.month4;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day8 {

    // 10 - 6
    // 0 1 2 3 4 5 6 7 8 9
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            if (set.contains(num)) {
                break;
            } else {
                set.add(num);
            }
        }
        int m = nums.length - set.size();
        System.out.println(m);
        int n = m / 3;
        return n + (m % 3 == 0 ? 0 : 1);
    }
}
