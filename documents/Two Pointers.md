# 125. [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/description/)

小写字母的 ascii 比 大写字母的大，数值是 32.

```java
package com.ganten.tethys;

public class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (true) {
            while (left < s.length() && !this.isLetter(s.charAt(left))) left++;
            
            while (right >= 0 && !this.isLetter(s.charAt(right))) right--;
            
            if (left > right) break;
            
            if (this.toLowCase(s.charAt(left)) != this.toLowCase(s.charAt(right))) return false;
            left++;
            right--;
        }
        return true;
    }

    private boolean isLetter(char c) {
        return ('a' <= c && 'z' >= c) || ('A' <= c && 'Z' >= c) || ('0' <= c && '9' >= c);
    }

    private char toLowCase(char c) {
        if ('A' <= c && 'Z' >= c) return (char) (c + 32);
        
        return c;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean mm = solution.isPalindrome("A man, a plan, a canal: Panama");
        System.out.println(mm);
    }
}

```

# 344. [Reverse String](https://leetcode.com/problems/reverse-string/description/)
```java
package com.ganten.tethys;

public class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            this.swap(s, right++, left++);
        }
    }

    public void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}
```

# 680. [Valid Palindrome II](https://leetcode.com/problems/valid-palindrome-ii/description/)
```java
package com.ganten.tethys;

public class Solution {
    public boolean validPalindrome(String s) {
        return validPalindrome(s, 1);
    }

    public boolean validPalindrome(String s, int i) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if (i == 0) {
                    return false;
                }
                return this.validPalindrome(s.substring(left + 1, right + 1), i - 1)
                        || this.validPalindrome(s.substring(left, right), i - 1);
            }
        }
        return true;
    }
}
```

# 5. [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/description/)
```java
class Solution {
    private int resultStart = 0;
    private int resultLen = 0;

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            checkPalindrome(chars, i, i);
            checkPalindrome(chars, i, i + 1);
        }

        return s.substring(resultStart, resultStart + resultLen);
    }

    private void checkPalindrome(char[] chars, int left, int right) {
        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        int len = right - left - 1;
        if (len > resultLen) {
            resultLen = len;
            resultStart = left + 1;
        }
    }
}
```

# 11. [Container With Most Water](https://leetcode.com/problems/container-with-most-water/description/)
```java
package com.ganten.tethys;

class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            int cur = (right - left) * Math.min(height[left], height[right]);
            max = Math.max(max, cur);
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return max;
    }
}
```

# 15. [3Sum](https://leetcode.com/problems/3sum/description/)
```java
package com.ganten.tethys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        if (nums.length < 3) {
            return resultList;
        }
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            if (nums[i] + nums[right - 1] + nums[right] < 0) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            while (left < right) {
                if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    ArrayList<Integer> result = new ArrayList<>();
                    result.add(nums[i]);
                    result.add(nums[left]);
                    result.add(nums[right]);
                    resultList.add(result);
                    int curLeft = nums[left];
                    int curRight = nums[right];
                    while (curLeft == nums[left] && left < right) {
                        left++;
                    }

                    while (curRight == nums[right] && left < right) {
                        right--;
                    }
                }
            }
        }
        return resultList;
    }
}
```

# 16. [3Sum Closest](https://leetcode.com/problems/3sum-closest/description/)
```java
package com.ganten.tethys;

import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int tagret) {
        Arrays.sort(nums);
        if (nums.length < 3) {
            return 0;
        }
        int closet = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int cur = nums[i] + nums[left] + nums[right];
                if (Math.abs(closet - tagret) > Math.abs(cur - tagret)) {
                    closet = cur;
                }
                if (nums[i] + nums[left] + nums[right] > tagret) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < tagret) {
                    left++;
                } else {
                    return tagret;
                }
            }
        }
        return closet;
    }
}
```

# 167. [Two Sum II](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/)
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
    }
}
```

# 283. Move Zeroes
```java
class Solution {
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] == 0) {
                continue;
            }
            nums[slow] = nums[fast];
            slow++;
        }
        for (; slow < nums.length; slow++) nums[slow] = 0;
    }
}
```

# 75. [Sort Colors](https://leetcode.com/problems/sort-colors/description/)
```java
class Solution {
    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int curr = 0;

        while (curr <= right) {
            if (nums[curr] == 0) {
                swap(nums, left, curr);
                left++;
                curr++;
            } else if (nums[curr] == 2) {
                swap(nums, curr, right);
                right--;
            } else {
                curr++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

# 26. Remove Duplicates from Sorted Array
```java

```

# 27. Remove Element
```java

```

# 80. Remove Duplicates II
```java

```

# 977. Squares of a Sorted Array
```java

```
