package com.ganten.tethys.leetcode75;

import com.ganten.tethys.TreeNode;

public class N872 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        String xx1 = this.calculateString(root1);
        String xx2 = this.calculateString(root2);
        return xx1.equals(xx2);
    }

    public String calculateString(TreeNode node) {
        if (node == null) {
            return "";
        }
        if (node.right == null && node.left == null) {
            return "" + node.val;
        }
        String xx1 = calculateString(node.left);
        String xx2 = calculateString(node.right);
        if (!xx1.isEmpty() && !xx2.isEmpty()) {
            return xx1 + "," + xx2;
        } else {
            return xx1 + xx2;
        }
    }
}
