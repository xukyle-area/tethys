package com.ganten.tethys.daily.year2025.month3;

public class Day8 {
    public int minimumRecolors(String blocks, int k) {
        char[] chars = blocks.toCharArray();
        int cur = 0;
        for (int i = 0; i < k; i++) {
            char c = chars[i];
            if (c == 'W') {
                cur++;
            }
        }
        int min = cur;
        int left = 0;
        int right = k - 1;
        while (right < chars.length - 1) {
            if (chars[right + 1] == 'W') {
                cur++;
            }
            if (chars[left] == 'W') {
                cur--;
            }
            right++;
            left++;
            min = Math.min(min, cur);
        }
        return min;
    }

    public static void main(String[] args) {
        Day8 day8 = new Day8();
        int recolors = day8.minimumRecolors("WBWBBBW", 2);
        System.out.println(recolors);
    }
}
