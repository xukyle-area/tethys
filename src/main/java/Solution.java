import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 创建容量为 k 的最小堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);

        for (int num : nums) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(num);
            } else if (priorityQueue.peek() < num) {
                priorityQueue.poll();
                priorityQueue.add(num);
            }
        }
        // 返回第 k 大的元素
        return priorityQueue.peek();
    }
}
