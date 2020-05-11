package leet.interview;

import leet.TreeNode;

public class Solution68 {
    public static void main(String[] args) {

    }

    /**
     * 仅存在两种可能性：
     * 1. 在不同子树  -->返回根节点root
     * 2. 在同一个子树 --> 返回第一个遍历到的节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        return left == null ? right : left;
    }
}
