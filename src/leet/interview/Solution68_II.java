package leet.interview;

import leet.TreeNode;

public class Solution68_II {
    public static void main(String[] args) {

    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p == null && q == null) return root;
        if (p == null) return q;
        if (q == null) return p;

        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        return left == null ? right : left;
    }
}
