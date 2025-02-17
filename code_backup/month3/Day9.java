package com.ganten.tethys.daily.year2025.month3;

import java.util.ArrayList;
import java.util.List;

public class Day9 {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int length = colors.length;
        int prev = colors[length - 1];
        Integer breakPoint = null;
        for (int i = 0; true; ) {
            if (colors[i] == prev) {
                breakPoint = i;
            }
            prev = colors[i];
            i++;
            if (i == length) {
                break;
            }
        }
        if (breakPoint == null) {
            return length;
        }

        List<Integer> continueCountList = getContinueCountList(colors, breakPoint, length);
        int result = 0;
        for (int i : continueCountList) {
            int m = i - k + 1;
            if (m > 0) {
                result += m;
            }
        }
        return result;
    }

    private List<Integer> getContinueCountList(int[] colors, int breakPoint, int length) {
        List<Integer> continueCountList = new ArrayList<>();
        int cur = breakPoint;
        int curLength = 1;
        do {
            if (colors[this.prevIndex(cur, length)] == colors[cur]) {
                if (curLength > 1) {
                    continueCountList.add(curLength);
                }
                curLength = 1;
            } else {
                curLength++;
            }
            cur = this.nextIndex(cur, length);
        } while (cur != breakPoint);
        continueCountList.add(curLength);
        return continueCountList;
    }

    private int prevIndex(int cur, int length) {
        int result = cur - 1;
        if (result == -1) {
            result += length;
        }
        return result;
    }

    private int nextIndex(int cur, int length) {
        int result = cur + 1;
        if (result == length) {
            result -= length;
        }
        return result;
    }

    public static void main(String[] args) {
        Day9 day9 = new Day9();
        // 2
        System.out.println("result: " + day9.numberOfAlternatingGroups(new int[]{0, 1, 0, 0, 1, 0, 1}, 6));
    }
}
