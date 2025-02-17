package com.ganten.tethys.top150;

import java.util.ArrayList;
import java.util.List;

public class N228 {
    public static void main(String[] args) {
        N228 n228 = new N228();
        n228.summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9});
    }

    public List<String> summaryRanges(int[] nums) {
        int index = 0;
        int start = 0;
        List<String> list = new ArrayList<>();
        if (nums.length == 0) return list;
        while (index + 1 < nums.length) {
            if (nums[index + 1] - nums[index] != 1) {
                list.add(this.buildString(nums, start, index));
                index++;
                start = index;
            } else {
                index++;
            }
        }
        list.add(this.buildString(nums, start, index));
        return list;
    }

    private String buildString(int[] nums, int start, int index) {
        if (start == index) {
            return String.valueOf(nums[index]);
        } else {
            return nums[start] + "->" + nums[index];
        }
    }
}
