package leet.interview;

import leet.TreeNode;

public class Solution55_II {
    public static void main(String[] args) {

    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int left = calHeight(root.left);
        int right = calHeight(root.right);

        if (Math.abs(left - right) <= 1) {
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;
    }

    private int calHeight(TreeNode root) {
        if (root == null) return 0;

        int left = calHeight(root.left);
        int right = calHeight(root.right);

        return Math.max(left, right) + 1;
    }
}
