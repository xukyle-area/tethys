package com.ganten.tethys.leetcode75;

import java.util.Arrays;

public class N1657 {
    public boolean closeStrings(String word1, String word2) {
        int[] counts1 = this.counts(word1);
        int[] counts2 = this.counts(word2);
        for (int i = 0; i < 26; ++i) {
            // for any character, one contains, another not contains, return false
            if ((counts1[i] > 0 && counts2[i] == 0) || (counts2[i] > 0 && counts1[i] == 0)) {
                return false;
            }
        }
        Arrays.sort(counts1);
        Arrays.sort(counts2);
        for (int i = 0; i < 26; ++i) {
            if (counts1[i] != counts2[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] counts(String word) {
        char[] chars = word.toCharArray();
        int[] result = new int[26];
        for (char c : chars) {
            int index = c - 'a';
            result[index]++;
        }
        return result;
    }
}
