import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int longestSubarray(int[] nums, int limit) {

        Deque<Integer> minQueue = new ArrayDeque<>();
        Deque<Integer> maxQueue = new ArrayDeque<>();

        int result = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {

            // maintain minQueue (increasing)
            while (!minQueue.isEmpty() && nums[minQueue.peekLast()] > nums[right]) {
                minQueue.pollLast();
            }
            minQueue.addLast(right);

            // maintain maxQueue (decreasing)
            while (!maxQueue.isEmpty() && nums[maxQueue.peekLast()] < nums[right]) {
                maxQueue.pollLast();
            }
            maxQueue.addLast(right);

            // shrink window
            while (!maxQueue.isEmpty() && !minQueue.isEmpty()
                    && nums[maxQueue.peekFirst()] - nums[minQueue.peekFirst()] > limit) {

                if (maxQueue.peekFirst() == left) {
                    maxQueue.pollFirst();
                }
                if (minQueue.peekFirst() == left) {
                    minQueue.pollFirst();
                }
                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }
}
