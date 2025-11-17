1. [Two Sum](https://leetcode.com/problems/two-sum/)
```java
package com.ganten.tethys;

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {0, 0};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]);
    }
}
```
217. [Contains Duplicate](https://leetcode.com/problems/contains-duplicate)

```java
package com.ganten.tethys;

import java.util.HashSet;

public class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int num : nums) {
            if (!hashSet.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 1};
        System.out.println(solution.containsDuplicate(nums));
    }
}
```
219. [Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/description/)

```java
package com.ganten.tethys;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            int num = nums[index];
            if (map.containsKey(num)) {
                if (index - map.get(num) <= k) {
                    return true;
                }
            }
            map.put(num, index);
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 1, 2, 3};
        System.out.println(solution.containsNearbyDuplicate(nums, 2));
    }
}
```

220. Contains Duplicate III
```java
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
```

这段代码的核心功能是**判断数组中是否存在两个元素，它们的索引差不超过 `indexDiff`，且数值差不超过 `valueDiff`**（即题目「存在重复元素 III」的解法）。下面从「功能、核心思路、代码细节、示例验证」四个维度详细解释：


### 一、核心功能（对应题目要求）
给定一个整数数组 `nums`、两个整数 `indexDiff` 和 `valueDiff`，返回 `true` 或 `false`：
- 存在一对元素 `nums[i]` 和 `nums[j]`（`i ≠ j`）
- 满足两个条件：
  1. 索引差约束：`|i - j| ≤ indexDiff`（两个元素的位置不能太远）
  2. 数值差约束：`|nums[i] - nums[j]| ≤ valueDiff`（两个元素的大小不能差太多）


### 二、核心解题思路：桶排序（空间换时间）
直接暴力枚举所有元素对（`O(n²)` 时间）会超时，因此代码采用「桶排序」优化，将时间复杂度降到 `O(n)`，核心思路如下：
1. **桶的定义**：把数值范围划分成多个「桶」，每个桶的大小为 `valueDiff + 1`（关键设计）。
   - 例如：`valueDiff = 3` 时，桶大小为 4，每个桶包含的数值范围是 `[0,3]、[4,7]、[8,11]` 等。
   - 原理：**同一个桶内的任意两个数，数值差一定 ≤ valueDiff**（因为桶大小是 `valueDiff+1`，最大差值就是桶大小-1 = valueDiff）。
2. **索引约束处理**：用一个哈希表 `buckets` 存储「当前窗口内的元素对应的桶」，窗口大小为 `indexDiff`（即只保留最近 `indexDiff` 个元素的桶）。
   - 当遍历到第 `i` 个元素时，先移除窗口外的元素（`i > indexDiff` 时，移除 `i - indexDiff - 1` 位置的元素对应的桶），保证窗口内元素的索引差都 ≤ indexDiff。
3. **数值约束判断**：
   - 若当前元素的桶已存在于哈希表中：说明窗口内有同桶元素，数值差满足条件，直接返回 `true`。
   - 若当前元素的「前一个桶」或「后一个桶」存在于哈希表中：需要计算当前元素与相邻桶元素的数值差，若 ≤ valueDiff，返回 `true`（相邻桶可能存在满足条件的元素）。
   - 若以上都不满足：将当前元素的桶加入哈希表，继续遍历。


### 三、代码细节拆解
#### 1. 计算数组最小值 `min`
```java
int min = Integer.MAX_VALUE, n = nums.length;
for (int num : nums) {
    min = Math.min(min, num);
}
```
- 目的：将所有数值「平移」到非负区间（避免负数计算桶ID时出现异常）。
- 例如：数组中有负数 `-2`，`min = -5`，则 `-2 - (-5) = 3`（平移后为正数，桶ID计算更直观）。

#### 2. 桶大小定义
```java
int bucketSize = valueDiff + 1;
```
- 关键设计：确保同一个桶内的元素数值差 ≤ valueDiff（比如 `valueDiff=0` 时，桶大小=1，同一个桶内元素完全相等）。

#### 3. 遍历数组，维护滑动窗口和桶
```java
for (int i = 0; i < n; i++) {
    // 步骤1：移除窗口外的元素（保证索引差 ≤ indexDiff）
    if (i > indexDiff) {
        int rm = nums[i - indexDiff - 1]; // 窗口最左侧元素
        int bktId = (rm - min) / bucketSize; // 该元素的桶ID
        buckets.remove(bktId); // 从哈希表中删除这个桶
    }

    // 步骤2：计算当前元素的桶ID
    int cur = nums[i];
    int bktId = (cur - min) / bucketSize;

    // 步骤3：判断是否满足条件
    if (buckets.containsKey(bktId)) {
        // 同桶元素存在，数值差一定满足
        return true;
    }
    // 检查前一个桶（bktId-1）
    if (buckets.containsKey(bktId - 1) && Math.abs(buckets.get(bktId - 1) - cur) <= valueDiff) {
        return true;
    }
    // 检查后一个桶（bktId+1）
    if (buckets.containsKey(bktId + 1) && Math.abs(buckets.get(bktId + 1) - cur) <= valueDiff) {
        return true;
    }

    // 步骤4：将当前元素的桶加入哈希表（桶中存储当前元素值，用于相邻桶判断）
    buckets.put(bktId, cur);
}
```

#### 4. 哈希表 `buckets` 的作用
- Key：桶ID（整数），表示当前窗口内存在该桶。
- Value：该桶对应的元素值（同一个桶内最多只有一个元素，因为一旦有两个就会直接返回 `true`）。


### 四、示例验证（main方法中的测试用例）
测试用例：`nums = [1,2,3,1]`，`indexDiff=3`，`valueDiff=0`
- 需求：找两个「索引差≤3」且「数值相等」（valueDiff=0）的元素。

#### 遍历过程：
1. `i=0`（cur=1）：
   - 窗口未超出（i≤3），无需移除元素。
   - 桶ID = (1 - 1) / 1 = 0（min=1，bucketSize=0+1=1）。
   - 哈希表无桶0，也无桶-1/1，加入桶0→1。哈希表：{0:1}。

2. `i=1`（cur=2）：
   - 窗口未超出，无需移除。
   - 桶ID = (2-1)/1=1。
   - 哈希表无桶1，检查桶0（1）：|1-2|=1>0，不满足；无桶2。加入桶1→2。哈希表：{0:1, 1:2}。

3. `i=2`（cur=3）：
   - 窗口未超出，无需移除。
   - 桶ID=(3-1)/1=2。
   - 哈希表无桶2，检查桶1（2）：|2-3|=1>0；无桶3。加入桶2→3。哈希表：{0:1, 1:2, 2:3}。

4. `i=3`（cur=1）：
   - 窗口未超出（i=3 ≤ indexDiff=3），无需移除。
   - 桶ID=(1-1)/1=0。
   - 哈希表已包含桶0（对应值1），直接返回 `true`。

最终输出 `true`，符合预期（元素1和索引3的元素1，索引差3≤3，数值差0≤0）。


### 总结
- 功能：解决「存在重复元素 III」问题，判断数组中是否有索引差≤indexDiff且数值差≤valueDiff的元素对。
- 核心：用「桶排序」将数值分组，通过哈希表维护滑动窗口内的桶，快速判断是否存在满足条件的元素对（时间复杂度O(n)）。
- 关键设计：桶大小=valueDiff+1（保证同桶元素满足数值差条件）、数值平移（处理负数）、滑动窗口（处理索引差条件）。
36. Valid Sudoku

```java

```

73. Set Matrix Zeroes

```java

```

128. Longest Consecutive Sequence

```java

```

169. Majority Element

```java

```

229. Majority Element II

```java

```

41. First Missing Positive

```java

```

238. Product of Array Except Self

```java

```

560. Subarray Sum Equals K

```java

```

523. Continuous Subarray Sum

```java

```

525. Contiguous Array

```java

```

53. Maximum Subarray

```java

```

42. Trapping Rain Water

```java

```

15. 3Sum

```java

```

16. 3Sum Closest

```java

```

18. 4Sum

```java

```

66. Plus One

```java

```

287. Find the Duplicate Number

```java

```

268. Missing Number

```java

```

54. Spiral Matrix

```java

```

48. Rotate Image

```java

```
