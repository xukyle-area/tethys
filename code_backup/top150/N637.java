package com.ganten.tethys.top150;

import com.ganten.tethys.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class N637 {

    private int[] count = new int[10000];
    private double[] sum = new double[10000];

    public List<Double> averageOfLevels_2(TreeNode root) {
        this.helper(root, 1);
        List<Double> doubles = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) break;
            doubles.add(sum[i] / count[i]);
        }
        return doubles;
    }

    private void helper(TreeNode node, int level) {
        if (node == null) return;
        count[level]++;
        sum[level] += node.val;
        this.helper(node.left, level + 1);
        this.helper(node.right, level + 1);
    }

    public List<Double> averageOfLevels_1(TreeNode root) {
        List<TreeNode> level = new ArrayList<>();
        List<Double> result = new ArrayList<>();
        level.add(root);
        result.add((double) root.val);
        while (true) {
            double sum = 0d;
            double count = 0;
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode treeNode : level) {
                if (treeNode.left != null) nextLevel.add(treeNode.left);
                if (treeNode.right != null) nextLevel.add(treeNode.right);
                sum += treeNode.val;
                count++;
            }
            if (count == 0) break;
            result.add(sum / count);
            level.clear();
            level.addAll(nextLevel);
        }
        return result;
    }
}
