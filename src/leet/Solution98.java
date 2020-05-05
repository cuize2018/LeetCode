package leet;

import java.util.*;

public class Solution98 {
    TreeNode pre = null;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode root_c = root;
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root = root.left;

        root.left = new TreeNode(0);
        root.right = new TreeNode(2);
        root = root.right;
        root.right = new TreeNode(3);
        root = root_c;
        root = root.right;
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);

        Solution98 s = new Solution98();

        System.out.println(s.isValidBST(root_c));
    }

    /**
     * 因为二叉搜索树中序遍历是递增的,所以我们可以中序遍历判断前一数是否小于后一个数.
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        if (!isValidBST(root.left)) return false;
        if (pre != null && pre.val >= root.val) return false;
        pre = root;
        return isValidBST(root.right);
    }

    public boolean isValidBST3(TreeNode root) {
        if (root == null) return true;

        boolean left = isValidBST3(root.left);
        if (!left) return false;

        if (pre != null && root.val <= pre.val) return false;
        pre = root;

        return isValidBST3(root.right);
    }

}
