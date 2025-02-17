package com.ganten.tethys.top150;

import com.ganten.tethys.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class N102 {
    private final List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        this.helper(root);
        return result;
    }

    public void helper(TreeNode root) {
        if (root == null) return;
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        result.add(Collections.singletonList(root.val));
        while (!level.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode treeNode : level) {
                if (treeNode.left != null) nextLevel.add(treeNode.left);
                if (treeNode.right != null) nextLevel.add(treeNode.right);
            }
            if (nextLevel.isEmpty()) return;
            List<Integer> list = new ArrayList<>();
            for (TreeNode node : nextLevel) list.add(node.val);
            result.add(list);
            level.clear();
            level.addAll(nextLevel);
        }
    }
}
