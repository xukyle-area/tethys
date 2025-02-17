package com.ganten.tethys.leetcode75;

import com.ganten.tethys.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class N199 {

    private final LinkedList<Integer> resultList = new LinkedList<>();
    private final LinkedList<TreeNode> levelList = new LinkedList<>();

    public List<Integer> rightSideView(TreeNode root) {
        if (root != null)
            levelList.add(root);
        while (!levelList.isEmpty()) {
            this.nextLevel();
        }
        return resultList;
    }

    private void nextLevel() {
        LinkedList<TreeNode> tempList = new LinkedList<>();
        while (!levelList.isEmpty()) {
            tempList.addLast(levelList.removeFirst());
        }
        resultList.add(tempList.getFirst().val);
        while (!tempList.isEmpty()) {
            TreeNode cur = tempList.removeFirst();
            TreeNode right = cur.right;
            if (right != null) levelList.addLast(right);
            TreeNode left = cur.left;
            if (left != null) levelList.addLast(left);
        }
    }
}
