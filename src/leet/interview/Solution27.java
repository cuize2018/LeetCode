package leet.interview;

import leet.TreeNode;

public class Solution27 {
    public static void main(String[] args) {

    }

    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null)return null;

        TreeNode mirrorLeft = mirrorTree(root.left);
        TreeNode mirrorRight = mirrorTree(root.right);

        root.left = mirrorRight;
        root.right = mirrorLeft;

        return root;
    }
}
