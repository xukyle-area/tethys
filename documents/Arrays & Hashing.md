# 1. [Two Sum](https://leetcode.com/problems/two-sum/)
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
# 217. [Contains Duplicate](https://leetcode.com/problems/contains-duplicate)

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
# 219. [Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/description/)

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

# 220. [Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/description/)
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

# 36. [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/description/)

```java
package com.ganten.tethys;

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[][] cols = new int[9][9];
        int[][] rows = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    cols[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || cols[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }

                }
            }
        }
        return true;
    }
}

```

# 73. [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/description/)

```java
package com.ganten.tethys;

public class Solution {
    private static final int MM = -7349234;

    public void setZeroes(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    this.setZeroes(matrix, i, j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == MM) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setZeroes(int[][] matrix, int i, int j) {
        for (int i2 = 0; i2 < matrix.length; i2++) {
            if (matrix[i2][j] != 0) {
                matrix[i2][j] = MM;
            }
        }
        for (int j2 = 0; j2 < matrix[0].length; j2++) {
            if (matrix[i][j2] != 0) {
                matrix[i][j2] = MM;
            }
        }
    }
}
```

# 128. [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/description/)

```java
package com.ganten.tethys;

import java.util.HashMap;

public class Solution {
    public int longestConsecutive(int[] nums) {
        int result = 0;
        // start with <key>, the longest length <length> of consecutive sequence
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, 1);
        }
        for (int num : nums) {
            int nextNum = num;
            int count = map.getOrDefault(nextNum, 0);
            result = Math.max(count, result);
            if (count == 0) {
                continue;
            }
            while (true) {
                nextNum++;
                int nextCount = map.getOrDefault(nextNum, 0);
                if (nextCount == 0) {
                    break;
                }
                count = count + nextCount;
                map.remove(nextNum);
                map.put(num, count);
                result = Math.max(count, result);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[] {1, 0, 1, 2};
        int mm = solution.longestConsecutive(array);
        System.out.println(mm);
    }
}
```

# 169. [Majority Element](https://leetcode.com/problems/majority-element/description/)

```java
package com.ganten.tethys;

public class Solution {
    public int majorityElement(int[] nums) {
        int result = 0;
        int count = 0;
        for (int n : nums) {
            if (count == 0) {
                result = n;
                count++;
            } else if (n == result) {
                count++;
            } else {
                count--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[] {3, 2, 3};
        int mm = solution.majorityElement(array);
        System.out.println(mm);
    }
}

```

# 229. [Majority Element II](https://leetcode.com/problems/majority-element-ii/description/)

```java
package com.ganten.tethys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int n : nums) {
            int co = hashMap.getOrDefault(n, 0);
            co++;
            hashMap.put(n, co);
        }
        List<Integer> arrayList = new ArrayList<>();
        for (Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > nums.length / 3) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[] {1,2};
        List<Integer> mm = solution.majorityElement(array);
        System.out.println(mm);
    }
}
```

# 238. [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/description/)

```java
package com.ganten.tethys;

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] resultArray = new int[nums.length];
        int count = this.countOfZero(nums);
        int multiply = 1;
        for (int n : nums) {
            if (n != 0)
                multiply *= n;
        }
        if (count == 1) {
            int index = this.indexOfZero(nums);
            resultArray[index] = multiply;
        } else if (count == 0) {
            for (int i = 0; i < nums.length; i++) {
                resultArray[i] = multiply / nums[i];
            }
        }
        return resultArray;
    }

    public int countOfZero(int[] nums) {
        int result = 0;
        for (int num : nums) {
            if (num == 0) {
                result++;
            }
        }
        return result;
    }

    public int indexOfZero(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[] {1, 2, 3, 4};
        int[] mm = solution.productExceptSelf(array);
        System.out.println(mm);
    }
}
```

# 560. [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/description/)

```java
import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0, count = 0;
        for (int n : nums) {
            sum += n;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
```

# 523. Continuous Subarray Sum

如果两个位置的前缀和模 k 的余数相同，说明这两个位置之间的子数组和是 k 的倍数
需要检查 sum % k 是否在之前出现过
而且子数组长度至少为 2，所以不能立即将当前的余数加入 set，应该延迟一轮
使用 prevMod 延迟一轮加入 set，确保子数组长度 ≥ 2
检查当前余数是否已经在 set 中出现
如果两个位置的余数相同，它们之间的子数组和就是 k 的倍数

```java
import java.util.HashSet;

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        // 存储前缀和的余数
        HashSet<Integer> set = new HashSet<>();
        set.add(0);

        int sum = 0;
        int prevMod = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int mod = sum % k;

            // 如果当前余数在 set 中出现过，说明找到了满足条件的子数组
            if (set.contains(mod)) {
                return true;
            }

            // 延迟一轮加入 set，确保子数组长度至少为 2
            set.add(prevMod);
            prevMod = mod;
        }
        return false;
    }
}
```

# 525. [Contiguous Array](https://leetcode.com/problems/contiguous-array/description/)

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // handle case where subarray starts at index 0

        int maxLen = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            // Treat 0 as -1
            sum += (nums[i] == 0 ? -1 : 1);

            if (map.containsKey(sum)) {
                // Subarray between previous index and current index has equal 0s and 1s
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }

        return maxLen;

    }
}
```

# 53. [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/description/)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;

        // dp[i] 表示 nums[i] 结尾的子数组的最大值
        // dp[i] = max{dp[i-1] + nums[i], nums[i]}
        int[] dp = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            dp[i] = Math.max((i > 0 ? dp[i - 1] : 0) + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
```

# 42. Trapping Rain Water

```java
class Solution {

    public int trap(int[] heights) {
        int highestIndex = -1;
        int highest = -1;
        for (int i = 0; i < heights.length; i++) {
            if (highest < heights[i]) {
                highest = heights[i];
                highestIndex = i;
            }
        }
        // 左边墙index
        int leftHeight = 0;
        int sumDeep = 0;
        for (int cur = 0; cur < highestIndex; cur++) {
            int curHeight = heights[cur];
            if (leftHeight <= curHeight) {
                leftHeight = curHeight;
            } else {
                sumDeep = sumDeep + leftHeight - curHeight;
            }
        }
        int rightHeight = 0;
        for (int cur = heights.length - 1; cur > highestIndex; cur--) {
            int curHeight = heights[cur];
            if (rightHeight <= curHeight) {
                rightHeight = curHeight;
            } else {
                sumDeep = sumDeep + rightHeight - curHeight;
            }
        }
        return sumDeep;
    }
}
```

# 15. 3Sum

```java

```

# 16. 3Sum Closest

```java

```

# 18. 4Sum

```java

```

# 66. Plus One

```java

```

# 287. Find the Duplicate Number

```java

```

# 268. Missing Number

```java

```

# 54. Spiral Matrix

```java

```

# 48. Rotate Image

```java

```
