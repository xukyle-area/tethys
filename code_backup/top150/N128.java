package com.ganten.tethys.top150;

import java.util.HashMap;

public class N128 {
    public static void main(String[] args) {
        N128 n128 = new N128();
        System.out.println(n128.longestConsecutive(new int[]{0, 1, 2, 4, 8, 5, 6, 7, 9, 3, 55, 88, 77, 99, 999999999}));
    }

    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, 1);
        int max = 0;
        for (int num : nums) {
            int m = num;
            Integer count = map.get(m);
            if (count == null) continue;
            while (map.containsKey(m + 1)) {
                count = count + map.get(m + 1);
                map.remove(m + 1);
                m++;
            }
            map.put(num, count);
            max = Math.max(count, max);
        }
        return max;
    }
}
