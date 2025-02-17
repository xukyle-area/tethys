package com.ganten.tethys.leetcode75;

import java.util.Stack;

public class N394 {
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
        StringBuilder result = new StringBuilder();
        while (!stack.empty()) {
            result.insert(0, stack.pop());
        }
        return result.toString();
    }

    public String xx(int n, String base) {
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < n; j++) {
            result.append(base);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        N394 n394 = new N394();
        String s = n394.decodeString("10[abc]3[cd]ef");
        System.out.println(s);
    }
}
