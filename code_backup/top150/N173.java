package com.ganten.tethys.top150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.ganten.tethys.TreeNode;

public class N173 {


}


class BSTIterator {

    private int index;
    private final List<TreeNode> list;

    public BSTIterator(TreeNode root) {
        list = this.build(root);
        index = 0;
    }

    public int next() {
        TreeNode treeNode = list.get(index);
        index++;
        return treeNode.val;
    }

    public boolean hasNext() {
        return index < list.size();
    }

    private List<TreeNode> build(TreeNode root) {
        TreeNode iter = root;
        List<TreeNode> result = new ArrayList<>();
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        while (!treeNodes.isEmpty() || iter != null) {
            while (iter != null) {
                treeNodes.push(iter);
                iter = iter.left;
            }
            iter = treeNodes.pop();
            result.add(iter);
            iter = iter.right;
        }
        return result;
    }
}
