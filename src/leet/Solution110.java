package leet;

public class Solution110 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode head = root;
        root.left = new TreeNode(2);
        root = root.left;
        root.left = new TreeNode(3);
        root = root.left;
        root.left = new TreeNode(4);

        System.out.println(isBalanced(head));
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int left = height(root.left);
        int right = height(root.right);

        if (Math.abs(left - right) <= 1) {
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;
    }

    private static int height(TreeNode root) {
        if (root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left, right) + 1;
    }

    public static boolean isBalanced2(TreeNode root) {
        int res = height2(root);
        return res != -1;
    }

    private static int height2(TreeNode root) {
        if (root == null) return 0;
        int left = height2(root.left);
        if (left == -1) return -1;

        int right = height2(root.right);
        if (right == -1) return -1;

        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;

    }
}
