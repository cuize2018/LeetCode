package leet;

public class Solution112 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-2);
        TreeNode head = root;
//        root.left = new TreeNode(4);
        root.right = new TreeNode(-3);

//        root = root.left;root.left = new TreeNode(11);
//        root = root.left;root.left = new TreeNode(7);root.right = new TreeNode(2);

        System.out.println(hasPathSum(head, -5));
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)return false;
        if (root.left == null && root.right == null && sum == root.val){
            return true;
        }

        boolean left = hasPathSum(root.left, sum - root.val);
        boolean right =  hasPathSum(root.right, sum - root.val);

        return left || right;
    }
}
