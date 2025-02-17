package com.ganten.tethys.leetcode75;

import com.ganten.tethys.TreeNode;

public class N700 {
    public TreeNode searchBST(TreeNode node, int val) {
        if (node == null || node.val == val) {
            return node;
        }
        if (node.val < val) {
            return this.searchBST(node.left, val);
        } else {
            return this.searchBST(node.right, val);
        }
    }
}
