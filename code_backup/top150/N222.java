package com.ganten.tethys.top150;

import com.ganten.tethys.TreeNode;

public class N222 {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }


}
