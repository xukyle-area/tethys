# [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        for (int i = 0; i < 128; i++) map[i] = -1;

        int left = 0;
        int right = -1;
        int max = 0;
        while (right < s.length() - 1) {
            right++;
            char currentChar = s.charAt(right);
            int lastIndex = map[currentChar];
            map[currentChar] = right;
            if (lastIndex >= left) left = lastIndex + 1;
            
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
```

# [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/description/)
不是最优解，但是我尽力了。
```java
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> tCount = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            tCount.put(c, tCount.getOrDefault(c, 0) + 1);

        }

        Map<Character, Integer> windowsCount = new HashMap<>();
        char[] aArray = s.toCharArray();

        int left = 0;
        int right = -1;
        String min = s;
        boolean has = false;
        while (right < aArray.length - 1) {
            right++;
            windowsCount.put(aArray[right], windowsCount.getOrDefault(aArray[right], 0) + 1);
            while (this.check(windowsCount, tCount)) {
                has = true;
                if (min.length() > right - left + 1) {
                    min = s.substring(left, right + 1);
                }
                windowsCount.put(aArray[left], windowsCount.getOrDefault(aArray[left], 0) - 1);
                left++;
            }
        }
        return has ? min : "";
    }

    private boolean check(Map<Character, Integer> windowsCount, Map<Character, Integer> tCount) {
        for (Entry<Character, Integer> entry : tCount.entrySet()) {
            if (windowsCount.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
```

# [Permutation in String](https://leetcode.com/problems/permutation-in-string/description/)
```java
class Solution {
    public boolean checkInclusion(String t, String s) {
        int[] tcount = new int[26];
        if (t.length() > s.length()) {
            return false;
        }
        for (int i = 0; i < t.length(); i++) {
            tcount[t.charAt(i) - 'a']++;
        }

        int[] windowsCount = new int[26];

        int left = 0;
        int right = t.length() - 1;
        for (int i = left; i <= right; i++) {
            windowsCount[s.charAt(i) - 'a']++;
        }
        if (this.check(windowsCount, tcount)) {
            return true;
        }

        while (right < s.length() - 1) {
            right++;
            windowsCount[s.charAt(right) - 'a']++;
            windowsCount[s.charAt(left) - 'a']--;
            left++;
            if (this.check(windowsCount, tcount)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(int[] windowsCount, int[] tcount) {
        for (int i = 0; i < 26; i++) {
            if (windowsCount[i] != tcount[i]) {
                return false;
            }
        }
        return true;
    }
}
```

# [Find All Anagrams](https://leetcode.com/problems/find-all-anagrams-in-a-string/description/)
跟上面一题极其相似。
```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new ArrayList<Integer>();
        int[] tcount = new int[26];
        if (t.length() > s.length()) {
            return result;
        }
        for (int i = 0; i < t.length(); i++) {
            tcount[t.charAt(i) - 'a']++;
        }

        int[] windowsCount = new int[26];

        int left = 0;
        int right = t.length() - 1;
        for (int i = left; i <= right; i++) {
            windowsCount[s.charAt(i) - 'a']++;
        }
        if (this.check(windowsCount, tcount)) {
            result.add(left);
        }

        while (right < s.length() - 1) {
            right++;
            windowsCount[s.charAt(right) - 'a']++;
            windowsCount[s.charAt(left) - 'a']--;
            left++;
            if (this.check(windowsCount, tcount)) {
                result.add(left);
            }
        }
        return result;
    }

    private boolean check(int[] windowsCount, int[] tcount) {
        for (int i = 0; i < 26; i++) {
            if (windowsCount[i] != tcount[i]) {
                return false;
            }
        }
        return true;
    }
}
```

# [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/description/)
过程如下：

```java
nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3

i=0: deque=[0(1)]
i=1: deque=[1(3)]           // 3>1, 移除索引0
i=2: deque=[1(3), 2(-1)]    // -1<3, 保留
     ans[0] = nums[1] = 3

i=3: deque=[1(3), 2(-1), 3(-3)]
     ans[1] = nums[1] = 3

i=4: deque=[4(5)]           // 5最大, 移除所有
     ans[2] = nums[4] = 5

i=5: deque=[4(5), 5(3)]     // 3<5, 保留
     ans[3] = nums[4] = 5

i=6: deque=[6(6)]           // 6最大, 移除所有
     ans[4] = nums[6] = 6

i=7: deque=[7(7)]           // 7最大, 移除所有
     ans[5] = nums[7] = 7

结果: [3, 3, 5, 5, 6, 7]
```

```java
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
```

# [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/description/)
```java

```

# Minimum Size Subarray Sum
```java
```

# Max Consecutive Ones III
```java
```

# Get Equal Substrings Within Budget
```java
```

# Longest Substring with At Most K Distinct Characters
```java
```

# Subarrays with K Different Integers
```java
```

# Maximum Erasure Value
```java
```
