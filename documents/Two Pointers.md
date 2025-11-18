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

```

# 11. Container With Most Water
```java

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

# 259. 3Sum Smaller
```java

```

# 167. Two Sum II
```java

```

# 283. Move Zeroes
```java

```

# 75. Sort Colors
```java

```

# 31. Next Permutation
```java

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
