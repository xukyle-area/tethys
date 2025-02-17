package com.ganten.tethys.top150;

public class N242 {
    public boolean isAnagram(String s, String t) {
        int[] sCount = this.toCount(s);
        int[] tCount = this.toCount(t);
        for (int i = 0; i < 26; i++) {
            if (sCount[i] != tCount[i]) {
                return false;
            }
        }

        return true;
    }

    private int[] toCount(String s) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            count[c - 'a']++;
        }
        return count;
    }
}
