package com.ganten.tethys.top150;

import java.util.HashSet;
import java.util.List;

public class N139 {
    private boolean[] dp;
    private HashSet<String> set = new HashSet<>();

    public boolean wordBreak(String s, List<String> wordDict) {
        // 1. 将字典放入一个hashSet中，并且计算出字典中最长和最短的单词
        dp = new boolean[s.length()];
        int minLength = Integer.MAX_VALUE;
        int maxLength = Integer.MIN_VALUE;
        for (String ss : wordDict) {
            set.add(ss);
            minLength = Math.min(minLength, ss.length());
            maxLength = Math.max(maxLength, ss.length());
        }

        for (int i = 0; i < s.length(); i++) {
            dp[i] = dpFunc(s, minLength, maxLength, i);
        }
        return dp[s.length() - 1];
    }

    /**
     * dp，计算对应的位置是否可以由字典组成
     *
     * @param s              源字符
     * @param dictRangeStart 字典最短的字符 [1,++)
     * @param dictRangeEnd   字典最长的字符 [1,++)
     * @param index          计算从s.(0) 到s.(index) 能否用字典完成 [0,length-1)
     * @return boolean
     */
    public boolean dpFunc(String s, int dictRangeStart, int dictRangeEnd, int index) {


        /**
         * 按照字典的长度开始遍历，截取s.(index)前字典长度个字符，判断在不在字典中
         * 在：s.(0) 到s.(index) 可以由字典完成
         * 不在：不能有字典完成
         */
        for (int i = dictRangeStart; i < dictRangeEnd + 1; i++) {
            // 单词匹配的起点
            int matchStart = index - i + 1;
            if (matchStart < 0 || (matchStart != 0 && !dp[matchStart - 1])) {
                continue;
            }

            String substring = s.substring(matchStart, matchStart + i);
            if (set.contains(substring)) {
                return true;
            }
        }
        return false;
    }

}
