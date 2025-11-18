package com.ganten.tethys;

public class Solution {
    public boolean validPalindrome(String s) {
        return validPalindrome(s, 1);
    }

    public boolean validPalindrome(String s, int i) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if (i == 0) {
                    return false;
                }
                return this.validPalindrome(s.substring(left + 1, right + 1), i - 1)
                        || this.validPalindrome(s.substring(left, right), i - 1);
            }
        }
        return true;
    }
}
