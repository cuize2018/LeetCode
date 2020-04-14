package leet;

public class Solution538 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        Solution538 solution538 = new Solution538();
        solution538.convertBST(root);
        int a = 0;
    }

    int sum = 0;

    /**
     * BST的中序遍历就是从小到大,那么反过来就是从大到小,然后累加就好了.
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;

        convertBST(root.right);

        root.val += sum;
        sum = root.val;

        convertBST(root.left);
        return root;
    }

}
