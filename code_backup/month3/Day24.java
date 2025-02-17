package com.ganten.tethys.daily.year2025.month3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day24 {

    public static void main(String[] args) {
        Day24 day24 = new Day24();
        System.out.println(day24.countDays(1000000000, new int[][]{{1, 1000000000}}));
    }

    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        int[] prev = null;
        List<int[]> meetingList = new ArrayList<>();
        for (int[] meeting : meetings) {
            if (prev == null) {
                prev = meeting;
            } else {
                if (prev[1] + 1 >= meeting[0]) {
                    prev = new int[]{prev[0], Math.max(meeting[1], prev[1])};
                } else {
                    meetingList.add(prev);
                    prev = meeting;
                }
            }
        }
        meetingList.add(prev);

        int count = 0;
        for (int[] meeting : meetingList) {
            int m = meeting[1] - meeting[0] + 1;
            count = m + count;
        }
        return days - count;
    }
}
