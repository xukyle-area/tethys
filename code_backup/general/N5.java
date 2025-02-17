package com.ganten.tethys.general;

public class N5 {
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int size = chars.length;
        String result = "";
        // dp[i][j] if true mean: from i to j, substring is palindrome, false is not
        // palindrome
        boolean[][] dp = new boolean[size][size];
        // init same digit
        for (int i = 0; i < size; i++) {
            dp[i][i] = true;
            result = String.valueOf(chars[i]);
        }
        // init 2 digit
        for (int end = 1; end < size; end++) {
            int start = end - 1;
            if (chars[start] == chars[end]) {
                dp[start][end] = true;
                result = s.substring(start, end + 1);
            }
        }

        for (int length = 3; length <= size; length++) {
            for (int start = 0; start <= size - length; start++) {
                int end = start + length - 1;
                if (dp[start + 1][end - 1] && chars[start] == chars[end]) {
                    dp[start][end] = true;
                    String innerString = s.substring(start, end + 1);
                    if (innerString.length() > result.length()) {
                        result = innerString;
                    }
                }
            }
        }
        return result;
    }
}
