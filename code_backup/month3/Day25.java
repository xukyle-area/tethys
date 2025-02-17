package com.ganten.tethys.daily.year2025.month3;

public class Day25 {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        for (int i = 1; i < rectangles.length; i++) {
            int[] prev = rectangles[i - 1];
            if (checkX(prev, rectangles[i]) && checkY(prev, rectangles[i])) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * true: 在 Y 轴上不相交
     */
    private boolean checkY(int[] rectangle1, int[] rectangle2) {
        int low1 = rectangle1[1];
        int high1 = rectangle1[3];
        int low2 = rectangle2[1];
        int high2 = rectangle2[3];
        if (low1 >= high1 || low2 >= high2) {
            return true;
        }
        return false;
    }

    /**
     * true: 在 X 轴上不相交
     */
    private boolean checkX(int[] rectangle1, int[] rectangle2) {
        int left1 = rectangle1[0];
        int right1 = rectangle1[2];
        int left2 = rectangle2[0];
        int right2 = rectangle2[2];
        if (left1 <= right1 || left2 <= right2) {
            return true;
        }
        return false;
    }
}
