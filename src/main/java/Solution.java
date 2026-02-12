class Solution {
    int result = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        this.helper(root, false);
        return result;
    }

    private void helper(TreeNode node, boolean isLeft) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            if (isLeft) {
                result += node.val;
            }
            return;
        }
        this.helper(node.left, true);
        this.helper(node.right, false);
    }
}
