package com.ganten.tethys.leetcode75;

import java.util.PriorityQueue;

public class N215 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k); // 创建容量为 k 的最小堆

        for (int num : nums) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(num); // 直接加入
            } else if (priorityQueue.peek() < num) {
                priorityQueue.poll();  // 移除堆顶最小元素
                priorityQueue.add(num); // 添加新的较大元素
            }
        }

        return priorityQueue.peek(); // 返回第 k 大的元素
    }
}
