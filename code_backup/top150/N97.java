package com.ganten.tethys.top150;

public class N97 {
    /**
     * dp[i][j] 表示使用 s1[0] 到 s1[i - 1] 中间的字符 和 s2[0] 到 s1[j - 1] 中间的字符
     * 可批匹配到的到 s3[0] - s3[dp[i][j] -1]
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() < s1.length() + s2.length()) {
            return false;
        }

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        dp[0][0] = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s3.charAt(i)) {
                dp[i + 1][0] = i + 1;
            } else {
                break;
            }
        }

        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == s3.charAt(i)) {
                dp[0][i + 1] = i + 1;
            } else {
                break;
            }
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (dp[i - 1][j] == 0 && dp[i][j - 1] == 0) {
                    dp[i][j] = 0;
                }
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    int index = dp[i - 1][j];
                    if (s3.charAt(index) == s1.charAt(i - 1)) {
                        dp[i][j] = index + 1;
                    } else {
                        dp[i][j] = 0;
                    }
                } else {
                    int index = dp[i][j - 1];
                    if (s3.charAt(index) == s2.charAt(j - 1)) {
                        dp[i][j] = index + 1;
                    } else {
                        dp[i][j] = 0;
                    }
                }
            }
        }
        return dp[s1.length()][s2.length()] == s3.length();
    }

}
