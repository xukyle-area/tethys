class Solution {

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
        int leftHeight = 0;
        int sumDeep = 0;
        for (int cur = 0; cur < highestIndex; cur++) {
            int curHeight = heights[cur];
            if (leftHeight <= curHeight) {
                leftHeight = curHeight;
            } else {
                sumDeep = sumDeep + leftHeight - curHeight;
            }
        }
        int rightHeight = 0;
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
}
