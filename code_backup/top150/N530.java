package com.ganten.tethys.top150;

import com.ganten.tethys.TreeNode;

public class N530 {
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        int deltaLeft = Integer.MAX_VALUE;
        if (root.left != null) deltaLeft = Math.abs(root.val - root.left.val);
        int deltaRight = Integer.MAX_VALUE;
        if (root.left != null) deltaRight = Math.abs(root.val - root.right.val);
        int curMin = Math.min(deltaLeft, deltaRight);
        int nextLeft = this.getMinimumDifference(root.left);
        int nextRight = this.getMinimumDifference(root.right);
        int nextMin = Math.min(nextRight, nextLeft);
        return Math.min(nextMin, curMin);
    }
}
