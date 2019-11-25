package leet;

public class Solution114 {
    private TreeNode curr;
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);root.left = new TreeNode(2);root.right = new TreeNode(5);
        TreeNode rootCopy = root;
        root = root.left;

        root.left = new TreeNode(3);root.right = new TreeNode(4);
        root = rootCopy;root = root.right;
        root.right = new TreeNode(6);

//        TreeNode root = new TreeNode(1);  TreeNode rootCopy = root;
//        root.left = new TreeNode(2);
//        root = root.left;
//        root.left = new TreeNode(3);

//        TreeNode root = new TreeNode(2);root.left = new TreeNode(1);root.right = new TreeNode(4);
//        TreeNode rootCopy = root;
//        root = root.right;
//
//        root.left = new TreeNode(3);


        Solution114 s = new Solution114();

        s.flatten(rootCopy);
        int ii = 0;

    }

    public  void flatten(TreeNode root) {
        curr = root;
        boolean flag = false;
        if (curr == null)return;
        if (curr.left == null && curr.right == null){
            return;
        }
        TreeNode rightNode = curr.right;
        if (curr.left != null) {
            flag = true;
            curr.right = curr.left;
            curr.left = null;
        }

        curr = curr.right;
        flatten(curr);

        if (flag) {
            if (rightNode != null) {
                curr.right = rightNode;
                curr = curr.right;
                flatten(curr);
            }
        }
    }
}
