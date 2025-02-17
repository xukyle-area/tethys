package com.ganten.tethys.leetcode75;

import com.ganten.tethys.TreeNode;

public class N1448 {
    public int goodNodes(TreeNode root) {
        return countNode(root, Integer.MIN_VALUE);
    }

    public int countNode(TreeNode node, int max) {
        if (node == null) {
            return 0;
        }
        int newMax = Math.max(max, node.val);
        int cur = node.val >= max ? 1 : 0;
        System.out.println(node.val + " - " + cur);
        int left = countNode(node.left, newMax);
        int right = countNode(node.right, newMax);
        return cur + left + right;
    }
}
