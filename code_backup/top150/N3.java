package com.ganten.tethys.top150;

import java.util.HashMap;
import java.util.Map;

public class N3 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        char[] array = s.toCharArray();

        int maxLength = 0;

        int left = 0;
        int right = -1;
        while (true) {
            right++;
            if (right == array.length) {
                break;
            }
            counts.put(array[right], counts.getOrDefault(array[right], 0) + 1);
            while (!this.match(counts)) {
                counts.put(array[left], counts.getOrDefault(array[left], 0) - 1);
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    private boolean match(Map<Character, Integer> counts) {
        for (int value : counts.values()) {
            if (value > 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        N3 n3 = new N3();
        System.out.println(n3.lengthOfLongestSubstring("pwwkew"));
    }
}
