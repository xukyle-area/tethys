package com.ganten.tethys.leetcode75;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// TODO
public class N2542 {
    /**
     * (nums1[i0] + nums1[i1] +...+ nums1[ik - 1])
     * *
     * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1])
     */
    public long maxScore(int[] speeds, int[] efficiencies, int k) {
        int n = speeds.length;
        int[][] esArray = new int[n][2];
        for (int i = 0; i < n; ++i) {
            esArray[i] = new int[]{efficiencies[i], speeds[i]};
        }
        // 按照 efficiencies 进行 降序 排序
        Arrays.sort(esArray, (a, b) -> b[0] - a[0]);
        // 构建一个堆 用来保存 efficiencies
        PriorityQueue<Integer> priorityQueueOfSpeed = new PriorityQueue<>(k, Comparator.naturalOrder());
        long res = 0, sumOfSpeed = 0;
        for (int[] es : esArray) {
            // efficiency 降序排列，所以每次的 efficiency 都是最小的
            int efficiency = es[0];
            int speed = es[1];
            priorityQueueOfSpeed.add(speed);
            sumOfSpeed = sumOfSpeed + speed;
            if (priorityQueueOfSpeed.size() > k) {
                sumOfSpeed -= priorityQueueOfSpeed.poll();
            }
            if (priorityQueueOfSpeed.size() == k) {
                res = Math.max(res, (sumOfSpeed * efficiency));
            }
        }
        return res;
    }
}
