package com.ganten.tethys.top150;

import com.ganten.tethys.TreeNode;

public class N101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return innerSymmetric(root.left, root.right);
    }

    public boolean innerSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return innerSymmetric(left.left, right.right) && innerSymmetric(left.right, right.left);
    }
}
