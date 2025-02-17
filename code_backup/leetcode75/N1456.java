package com.ganten.tethys.leetcode75;

public class N1456 {
    public int maxVowels(String s, int k) {
        char[] chars = s.toCharArray();
        int[] counts = new int[s.length() - k + 1];
        int count0 = 0;
        for (int i = 0; i < k; i++) {
            count0 += this.isVowel(chars[i]);
        }
        counts[0] = count0;
        int maxCount = count0;
        for (int i = k; i < chars.length; i++) {
            int newCount = counts[i - k] + (this.isVowel(chars[i]) - this.isVowel(chars[i - k]));
            counts[i - k + 1] = newCount;
            maxCount = Math.max(maxCount, newCount);
        }
        return maxCount;
    }

    private int isVowel(char letter) {
        if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
            return 1;
        }
        return 0;
    }
}
