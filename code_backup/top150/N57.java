package com.ganten.tethys.top150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class N57 {
    public int[][] insert(int[][] intervals, int[] now) {
        int[][] ints = new int[intervals.length + 1][2];
        for (int i = 0; i < intervals.length; i++) {
            ints[i] = intervals[i];
        }
        ints[ints.length - 1] = now;
        return this.merge(ints);
    }

    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparing(o -> o[0]));
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] now = intervals[i];
            if (now[0] <= prev[1]) {
                prev = new int[]{prev[0], Math.max(now[1], prev[1])};
            } else {
                list.add(prev);
                prev = now;
            }
        }
        list.add(prev);
        int[][] result = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            int[] ints = list.get(i);
            result[i] = ints;
        }
        return result;
    }
}
