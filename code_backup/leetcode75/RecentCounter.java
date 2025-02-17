package com.ganten.tethys.leetcode75;

import java.util.LinkedList;

/**
 * N 933
 */
public class RecentCounter {
    private final LinkedList<Integer> list;

    public RecentCounter() {
        list = new LinkedList<>();
    }

    public int ping(int t) {
        int begin = t - 3000;
        if (begin < 0) {
            begin = 0;
        }
        list.addLast(t);
        while (list.getFirst() < begin) {
            list.pop();
        }
        return list.size();
    }
}

