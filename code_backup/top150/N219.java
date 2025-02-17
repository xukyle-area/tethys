package com.ganten.tethys.top150;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class N219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer preIndex = map.get(num);
            if (Objects.nonNull(preIndex) && i - preIndex <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }
}
