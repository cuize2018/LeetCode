package leet.interview;

import leet.TreeNode;

public class Solution28 {
    public static void main(String[] args) {

    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return help(root.left, root.right);
    }

    private static boolean help(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;

        boolean a = help(left.left, right.right);
        boolean b = help(left.right, right.left);
        return a && b;
    }
}
