package leet;

public class Solution226 {
    public static void main(String[] args) {

    }

    public static TreeNode invertTree(TreeNode root) {
        swap(root);
        return root;
    }

    /**
     * 交换左右节点
     * @param root
     */
    private static void swap(TreeNode root) {
        if (root == null)return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        swap(root.left);
        swap(root.right);
    }
}
