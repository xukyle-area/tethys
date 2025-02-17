package com.ganten.tethys.top150;

public class N42 {

    public int trap(int[] heights) {
        int highestIndex = -1;
        int highest = -1;
        for (int i = 0; i < heights.length; i++) {
            if (highest < heights[i]) {
                highest = heights[i];
                highestIndex = i;
            }
        }
        // 左边墙index
        int leftHeight = heights[0];
        int sumDeep = 0;
        for (int cur = 1; cur < highestIndex; cur++) {
            int curHeight = heights[cur];
            if (leftHeight <= curHeight) {
                leftHeight = curHeight;
            } else {
                sumDeep = sumDeep + leftHeight - curHeight;
            }
        }
        int rightHeight = heights[0];
        for (int cur = heights.length - 1; cur > highestIndex; cur--) {
            int curHeight = heights[cur];
            if (rightHeight <= curHeight) {
                rightHeight = curHeight;
            } else {
                sumDeep = sumDeep + rightHeight - curHeight;
            }
        }
        return sumDeep;
    }
    public int trapGpt(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        int left = 0, right = heights.length - 1;
        int leftMax = 0, rightMax = 0;
        int sumDeep = 0;

        while (left < right) {
            if (heights[left] < heights[right]) {
                if (heights[left] >= leftMax) {
                    leftMax = heights[left];
                } else {
                    sumDeep += leftMax - heights[left];
                }
                left++;
            } else {
                if (heights[right] >= rightMax) {
                    rightMax = heights[right];
                } else {
                    sumDeep += rightMax - heights[right];
                }
                right--;
            }
        }
        return sumDeep;
    }

    public static void main(String[] args) {
        N42 n42 = new N42();
        System.out.println(n42.trap(new int[]{4, 2, 0, 3, 2, 5}));
    }
}
