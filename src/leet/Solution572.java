package leet;

public class Solution572 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        TreeNode mov = root;
        mov = mov.left;
        mov.left = new TreeNode(1);

        mov = root.right;
        mov.left = new TreeNode(2);

        TreeNode t = new TreeNode(3);
        t.left = new TreeNode(1);
        t.right = new TreeNode(2);

        System.out.println(isSubtree(root, t));
    }

    /**
     * 一个树是另一个树的子树 则
     *      要么这两个树相等
     *      要么这个树是左树的子树
     *      要么这个树是右树的子树
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;

        return isSametree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private static boolean isSametree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;

        return s.val == t.val && isSametree(s.left, t.left) && isSametree(s.right, t.right);
    }
}
