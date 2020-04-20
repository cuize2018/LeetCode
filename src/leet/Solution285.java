package leet;

public class Solution285 {
    TreeNode ans = null;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);root.left = new TreeNode(3);root.right = new TreeNode(6);
        TreeNode mov = root;
        mov = mov.left;
        mov.left = new TreeNode(2);
        mov.right = new TreeNode(4);
        mov = mov.left;
        mov.left = new TreeNode(1);

        Solution285 solution285 = new Solution285();
        TreeNode treeNode = solution285.inorderSuccessor(root, new TreeNode(1));
        int a = 0;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null)return null;
        inorder(root, p);
        return ans;
    }

    private void inorder(TreeNode root, TreeNode p) {
        if (root == null || ans != null){
            return ;
        }

        inorder(root.left, p);
        if (ans == null && root.val > p.val)ans = root;
        inorder(root.right, p);
    }
}
