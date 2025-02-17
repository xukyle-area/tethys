package com.ganten.tethys.daily.year2025.month4;

import com.ganten.tethys.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        // val to depth
        Map<TreeNode, Integer> depthMap = new HashMap<>();
        int maxDepth = this.fillDepthMap(depthMap, root, 0);
        List<TreeNode> depthNodeList = new ArrayList<>();
        for (Map.Entry<TreeNode, Integer> entry : depthMap.entrySet()) {
            if (entry.getValue() == maxDepth) {
                depthNodeList.add(entry.getKey());
            }
        }
        if (depthNodeList.size() == 1) {
            return depthNodeList.get(0);
        } else {
            TreeNode temp = this.lowestCommonAncestor(root, depthNodeList.get(0), depthNodeList.get(1));
            for (int i = 1; i < depthNodeList.size(); i++) {
                temp = this.lowestCommonAncestor(root, temp, depthNodeList.get(i));
            }
            return temp;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    public int fillDepthMap(Map<TreeNode, Integer> map, TreeNode root, int depth) {
        if (root == null) return depth - 1;
        map.put(root, depth);
        System.out.println(root.val + " " + depth);
        int maxLeft = this.fillDepthMap(map, root.left, depth + 1);
        int maxRight = this.fillDepthMap(map, root.right, depth + 1);
        return Math.max(maxRight, maxLeft);
    }
}
