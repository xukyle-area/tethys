package com.ganten.tethys.daily.year2025.month3;

import java.util.ArrayList;
import java.util.List;

public class Day30 {

    public List<Integer> partitionLabels(String s) {
        char[] chars = s.toCharArray();
        int iter = 0;
        List<Integer> list = new ArrayList<>();
        while (true) {
            int helper = this.helper(chars, iter);
            list.add(helper - iter + 1);
            if (helper == chars.length - 1) break;
            iter = helper + 1;
        }
        return list;
    }

    private int helper(char[] chars, int start) {
        int iter = start;
        int end = start;
        while (iter < chars.length) {
            int nextIndex = this.findNextIndex(chars, iter);
            if (nextIndex != -1) end = Math.max(nextIndex, end);
            if (iter == end) return end;
            iter++;
        }
        return iter - 1;
    }

    private int findNextIndex(char[] chars, int start) {
        for (int i = start + 1; i < chars.length; i++) if (chars[i] == chars[start]) return i;
        return -1;
    }

    private String buildString(char[] chars, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) sb.append(chars[i]);
        return sb.toString();
    }

    public static void main(String[] args) {
        Day30 day30 = new Day30();
        day30.partitionLabels("eccbbbbdec");
    }
}
