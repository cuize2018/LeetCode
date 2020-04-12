package leet;

public class Solution101 {
    public static void main(String[] args) {

    }

    /**
     * 如果同时满足下面的条件，两个树互为镜像：
     *   1.它们的两个根结点具有相同的值。
     *   2.每个树的右子树都与另一个树的左子树镜像对称。
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        return help(root, root);
    }

    private static boolean help(TreeNode left, TreeNode right) {
        if (left == null && right == null)return true;
        if (left== null || right == null)return false;

        return (left.val == right.val && help(left.left, right.right) && help(left.right, right.left));
    }

}
