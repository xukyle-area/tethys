package com.ganten.tethys.leetcode75;

import java.util.Stack;

public class N735 {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; ) {
            int newAsteroid = asteroids[i];
            // 方向相同，直接 push
            if (stack.empty() || newAsteroid * stack.peek() > 0) {
                stack.push(newAsteroid);
                i++;
            } else {
                int oldAsteroid = stack.peek();
                // 栈底为正，顶为负数
                if (oldAsteroid > 0) {
                    if (Math.abs(newAsteroid) < Math.abs(oldAsteroid)) {
                        i++;
                    } else if (Math.abs(newAsteroid) > Math.abs(oldAsteroid)) {
                        stack.pop();
                    } else {
                        stack.pop();
                        i++;
                    }
                } else {
                    stack.push(newAsteroid);
                    i++;
                }
            }
        }
        int size = stack.size();
        int[] result = new int[size];
        int i = 0;
        while (!stack.empty()) {
            result[size - i - 1] = stack.pop();
            i++;
        }
        return result;
    }
}
