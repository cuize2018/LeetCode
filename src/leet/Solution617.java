package leet;

public class Solution617 {
    public static void main(String[] args) {

    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)return t1;
        if (t1 == null){
            t1 = t2;
            return t1;
        }
        else if (t2 == null)return t1;

        t1.val += t2.val;

        TreeNode left = mergeTrees(t1.left, t2.left);
        TreeNode right = mergeTrees(t1.right, t2.right);

        t1.left = left;
        t1.right = right;

        return t1;
    }
}
