package com.ganten.tethys.leetcode75;

import com.ganten.tethys.TreeNode;

import java.util.LinkedList;

public class N1161 {
    private final LinkedList<Integer> resultList = new LinkedList<>();
    private final LinkedList<TreeNode> levelList = new LinkedList<>();

    public int maxLevelSum(TreeNode root) {
        if (root != null)
            levelList.add(root);
        while (!levelList.isEmpty()) {
            this.nextLevel();
        }
        int maxIndex = -1;
        Integer max = null;
        for (int i = 0; i < resultList.size(); i++) {
            Integer value = resultList.get(i);
            System.out.println("i: " + i + 1 + ", value: " + value);
            if (max == null || value > max) {
                maxIndex = i + 1;
                max = value;
            }
        }
        return maxIndex;
    }

    private void nextLevel() {
        LinkedList<TreeNode> tempList = new LinkedList<>();
        int sum = 0;
        while (!levelList.isEmpty()) {
            TreeNode node = levelList.removeFirst();
            sum += node.val;
            tempList.addLast(node);
        }
        resultList.add(sum);
        while (!tempList.isEmpty()) {
            TreeNode cur = tempList.removeFirst();
            TreeNode right = cur.right;
            if (right != null) levelList.addLast(right);
            TreeNode left = cur.left;
            if (left != null) levelList.addLast(left);
        }
    }
}
