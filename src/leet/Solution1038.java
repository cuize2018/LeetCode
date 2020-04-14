package leet;

public class Solution1038 {
    public static void main(String[] args) {

    }

    int sum = 0;
    //与538题相同
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) return null;

        bstToGst(root.right);

        root.val += sum;
        sum = root.val;

        bstToGst(root.left);
        return root;
    }
}
