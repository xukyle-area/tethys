# Stack / Monotonic Stack

## 20. [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
```java
class Solution {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        LinkedList<Character> linkedList = new LinkedList<>();
        for (char c : chars) {
            if (c == '{' || c == '[' || c == '(') {
                linkedList.push(c);
            } else {
                Character peek = linkedList.peek();
                if (peek == null || Math.abs(c - peek) > 2) return false;
                else linkedList.pop();
            }
        }
        return linkedList.isEmpty();
    }
}
```

## 155. [Min Stack](https://leetcode.com/problems/min-stack/)
```java
class MinStack {

    // 存储正常的栈
    private LinkedList<Integer> stack;
    // 存储截止到当前位置最小的数字
    private LinkedList<Integer> minStack;
    
    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        stack.push(x);
        minStack.push(Math.min(x, minStack.peek()));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
```

## 150. [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/)
```java
import java.util.LinkedList;

class Solution {

    public int evalRPN(String[] tokens) {

        LinkedList<String> stack = new LinkedList<>();

        for (String token : tokens) {
            if (!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) {
                stack.push(token);
                continue;
            }
            int num1 = Integer.parseInt(stack.pop());
            int num2 = Integer.parseInt(stack.pop());
            int res = 0;
            switch (token) {
                case "+":
                    res = num1 + num2;
                    break;
                case "-":
                    res = num2 - num1;
                    break;
                case "*":
                    res = num1 * num2;
                    break;
                case "/":
                    res = num2 / num1;
                    break;
            }
            stack.push(String.valueOf(res));
        }
        return Integer.parseInt(stack.pop());
    }
}
```

## 394. [Decode String](https://leetcode.com/problems/decode-string/)
```java
class Solution {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || c == '[') {
                stack.push(c);
            } else if (c == ']') {
                StringBuilder base = new StringBuilder();
                while (stack.peek() != '[') {
                    base.insert(0, stack.pop());
                }
                stack.pop();
                StringBuilder numc = new StringBuilder();
                while (!stack.empty() && (stack.peek() >= '0' && stack.peek() <= '9')) {
                    numc.insert(0, stack.pop());
                }
                String xx = this.xx(Integer.parseInt(numc.toString()), base.toString());
                for (char c1 : xx.toCharArray()) {
                    stack.push(c1);
                }
            }
        }
        String result = "";
        while (!stack.empty()) {
            result = stack.pop() + result;
        }
        return result;
    }

    public String xx(int n, String base) {
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < n; j++) {
            result.append(base);
        }
        return result.toString();
    }

}
```

## 739. [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)
```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // 栈里的 index 对应的温度值是递减的
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }

        return result;
    }
}
```

## 503. [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/)
```java

```

## 84. [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)
```java

```

## 85. [Maximal Rectangle](https://leetcode.com/problems/maximal-rectangle/)
```java

```

## 42. [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)
```java

```

## 316. [Remove Duplicate Letters](https://leetcode.com/problems/remove-duplicate-letters/)
```java

```

## 32. [Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/)
```java

```

## 856. [Score of Parentheses](https://leetcode.com/problems/score-of-parentheses/)
```java

```

## 921. [Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/)
```java

```
