package com.ganten.tethys.leetcode75;

import com.ganten.tethys.TreeNode;

public class N104 {
    public int maxDepth(TreeNode node) {
        if (node == null) return 0;
        return Math.max(maxDepth(node.right), maxDepth(node.left)) + 1;
    }
}
