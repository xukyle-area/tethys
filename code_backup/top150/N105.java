package com.ganten.tethys.top150;

import com.ganten.tethys.TreeNode;

public class N105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        if (preorder.length == 1) return new TreeNode(preorder[0]);
        int head = preorder[0];
        int headIndexFromInorder = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == head) {
                headIndexFromInorder = i;
                break;
            }
        }
        int prevLength = headIndexFromInorder;
        int postLength = inorder.length - headIndexFromInorder - 1;

        TreeNode treeNode = new TreeNode(head);
        int[] preorderLeft = this.buildArrays(preorder, 1, prevLength);
        int[] inorderLeft = this.buildArrays(inorder, 0, prevLength - 1);
        treeNode.left = this.buildTree(preorderLeft, inorderLeft);

        int[] preorderRight = this.buildArrays(preorder, preorder.length - postLength, preorder.length - 1);
        int[] inorderRight = this.buildArrays(inorder, inorder.length - postLength, inorder.length - 1);
        treeNode.right = this.buildTree(preorderRight, inorderRight);
        return treeNode;
    }

    private int[] buildArrays(int[] array, int start, int end) {
        int[] result = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            result[i - start] = array[i];
        }
        return result;
    }

    public static void main(String[] args) {
        N105 n105 = new N105();
        System.out.println(n105.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
    }
}
