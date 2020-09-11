package leet;

public class Solution156 {
    public static void main(String[] args) {

    }

    public static TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) return null;
        if (root.left == null) return root;

        TreeNode newRoot = upsideDownBinaryTree(root.left);
        TreeNode mov = newRoot;

        while (mov.right != null) mov = mov.right;
        mov.left = root.right;
        mov.right = new TreeNode(root.val);

        return newRoot;
    }


    TreeNode finalRoot = null;
    //java递归后序遍历递归解法。helper返回的是新树中的右节点，而最终要返回新的root，所以一开始需要把新的root保存
    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        if (root == null) return null;
        if (root.left == null) return root;

        func(root);
        return finalRoot;
    }

    private TreeNode func(TreeNode root) {
        if (root == null) return null;

        TreeNode newRoot = func(root.left);
        TreeNode newLeft = func(root.right);
        TreeNode newRight = root;

        if (newRoot == null) return root;
        if (finalRoot == null) finalRoot = newRoot;

        newRoot.left = newLeft;
        newRoot.right = newRight;

        if (newLeft != null) {
            newLeft.left = null;
            newLeft.right = null;
        }
        if (newRight != null) {
            newRight.left = null;
            newRight.right = null;
        }
        return newRight;
    }
}
