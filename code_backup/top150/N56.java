package com.ganten.tethys.top150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class N56 {
    public static void main(String[] args) {
        N56 n56 = new N56();
        int[][] merge = n56.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        System.out.println(Arrays.deepToString(merge));
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
