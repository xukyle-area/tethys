package com.ganten.tethys.top150;

import com.ganten.tethys.TreeNode;

public class N129 {
    public int sumNumbers(TreeNode root) {
        return this.helper(root, 0);
    }

    public int helper(TreeNode node, int num) {
        if (node == null) return 0;
        int newNum = num * 10 + node.val;
        if (node.left == null && node.right == null) return newNum;
        return this.helper(node.right, newNum) + this.helper(node.left, newNum);
    }
}
