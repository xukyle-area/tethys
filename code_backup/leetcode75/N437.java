package com.ganten.tethys.leetcode75;

import com.ganten.tethys.TreeNode;

public class N437 {
    public int pathSum(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        int result = rootSum(root, targetSum);
        result += pathSum(root.left, targetSum);
        result += pathSum(root.right, targetSum);
        return result;
    }

    private int rootSum(TreeNode node, long targetSum) {
        int result = 0;
        if (node == null) {
            return result;
        }
        if (node.val == targetSum) {
            result++;
        }
        result += this.rootSum(node.left, targetSum - node.val);
        result += this.rootSum(node.right, targetSum - node.val);
        return result;
    }
}
