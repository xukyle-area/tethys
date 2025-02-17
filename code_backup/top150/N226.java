package com.ganten.tethys.top150;

import com.ganten.tethys.TreeNode;

public class N226 {
    public TreeNode invertTree(TreeNode node) {
        if (node == null) return null;
        TreeNode left = node.left;
        TreeNode right = node.right;
        node.left = invertTree(right);
        node.right = invertTree(left);
        return node;
    }
}
