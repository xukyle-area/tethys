package com.ganten.tethys.top150;

import com.ganten.tethys.TreeNode;

import java.util.Objects;

public class N100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (Objects.isNull(p) && Objects.isNull(q)) return true;
        if (Objects.isNull(p) || Objects.isNull(q)) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
