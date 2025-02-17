package com.ganten.tethys.leetcode75;

import java.util.HashSet;

// N 2336
public class SmallestInfiniteSet {
    private final HashSet<Integer> set;

    public SmallestInfiniteSet() {
        set = new HashSet<>();
    }

    public int popSmallest() {
        for (int i = 1; true; i++) {
            if (!set.contains(i)) {
                set.add(i);
                return i;
            }
        }
    }

    public void addBack(int num) {
        set.remove(num);
    }
}
