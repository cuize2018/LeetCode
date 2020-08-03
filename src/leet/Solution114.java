package leet;

public class Solution114 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        TreeNode rootCopy = root;
        root = root.left;

        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root = rootCopy;
        root = root.right;
        root.right = new TreeNode(6);

        Solution114 s = new Solution114();

        s.flatten(rootCopy);
        int ii = 0;

    }

    public void flatten(TreeNode root) {
        if (root == null) return;

        while (root != null) {
            if (root.left != null) {
                TreeNode mov = root.left;
                while (mov.right != null) {
                    mov = mov.right;
                }

                TreeNode r = root.right;
                root.right = root.left;
                root.left = null;
                mov.right = r;

            }
            root = root.right;
        }
    }

    public void flatten2(TreeNode root) {
        if (root == null) return;

        flatten2(root.left);
        flatten2(root.right);

        if (root.left == null) {
            return;
        }

        TreeNode mov = root.left;
        while (mov.right != null) {
            mov = mov.right;
        }

        TreeNode r = root.right;

        root.right = root.left;
        root.left = null;

        mov.right = r;
    }
}
