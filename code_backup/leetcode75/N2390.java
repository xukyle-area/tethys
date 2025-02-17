package com.ganten.tethys.leetcode75;

import java.util.Stack;

public class N2390 {
    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '*') {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.empty()) {
            result.insert(0, stack.pop());
        }
        return result.toString();
    }
}
