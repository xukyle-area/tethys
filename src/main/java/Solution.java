import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // 存储索引，保持单调递减

        for (int i = 0; i < nums.length; i++) {
            // 1. 移除不在窗口内的索引
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 2. 移除所有比当前元素小的索引（维护单调递减）
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 3. 添加当前索引
            deque.offerLast(i);

            // 4. 记录答案（窗口形成后）
            if (i >= k - 1) {
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return ans;
    }
}
