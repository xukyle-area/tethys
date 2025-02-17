package com.ganten.tethys.leetcode75;

import com.ganten.tethys.TreeNode;

public class N1372 {
    private int maxLength = 0; // 记录最长 ZigZag 路径

    public int longestZigZag(TreeNode root) {
        if (root == null) return 0;
        dfs(root, true, 0);  // 从根节点开始，尝试向右
        dfs(root, false, 0); // 从根节点开始，尝试向左
        return maxLength;
    }

    private void dfs(TreeNode node, boolean isRight, int length) {
        if (node == null) return;
        maxLength = Math.max(maxLength, length);
        if (isRight) {
            dfs(node.right, false, length + 1); // 右 -> 左
            dfs(node.left, true, 1);  // 重新开始计算（从左子树开始）
        } else {
            dfs(node.left, true, length + 1);  // 左 -> 右
            dfs(node.right, false, 1);  // 重新开始计算（从右子树开始）
        }
    }
}
