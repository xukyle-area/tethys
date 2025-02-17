package com.ganten.tethys.leetcode75;

import com.ganten.tethys.TreeNode;

public class N1372_A {
    public int longestZigZag(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 计算以当前节点为终点的 Z 字路径的最长长度
        int max = Math.max(this.max(node.right, true), this.max(node.left, false)) + 1;
        // 遍历子节点
        int leftMax = longestZigZag(node.left);
        int rightMax = longestZigZag(node.right);
        return Math.max(max, Math.max(leftMax, rightMax));
    }

    public int max(TreeNode node, boolean isRight) {
        if (node == null) {
            return 0;
        }
        if (isRight) {
            // prev is right, next is left
            return this.max(node.left, false) + 1;
        } else {
            // prev is left, next is right
            return this.max(node.right, true) + 1;
        }
    }
}
