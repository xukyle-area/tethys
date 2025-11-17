package com.ganten.tethys;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int min = Integer.MAX_VALUE, n = nums.length;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        // index -> value
        Map<Integer, Integer> buckets = new HashMap<>();
        int bucketSize = valueDiff + 1;
        for (int i = 0; i < n; i++) {
            if (i > indexDiff) {
                int rm = nums[i - indexDiff - 1];
                int bktId = (rm - min) / bucketSize;
                buckets.remove(bktId);
            }
            int cur = nums[i];
            int bktId = (cur - min) / bucketSize;
            if (buckets.containsKey(bktId)) {
                return true;
            }
            buckets.put(bktId, cur);
            if (buckets.containsKey(bktId - 1) && Math.abs(buckets.get(bktId - 1) - cur) <= valueDiff) {
                return true;
            }
            if (buckets.containsKey(bktId + 1) && Math.abs(buckets.get(bktId + 1) - cur) <= valueDiff) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 1};
        System.out.println(solution.containsNearbyAlmostDuplicate(nums, 3, 0));
    }
}
