package leet;

public class Solution236 {
    TreeNode res;

    public static void main(String[] args) {

    }

    /**
     * 1.从根节点开始遍历树。
     * 2.如果当前节点本身是 p 或 q 中的一个，我们会将变量 mid 标记为 true，并继续搜索左右分支中的另一个节点。
     * 3.如果左分支或右分支中的任何一个返回 true，则表示在下面找到了两个节点中的一个。
     * 4.如果在遍历的任何点上，左、右、中三个标志中的任意两个变为 true，这意味着我们找到了节点 p 和 q 的最近公共祖先。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }

        boolean left = dfs(root.left, p, q);
        boolean right = dfs(root.right, p, q);
        boolean middle = (root.val == p.val) || (root.val == q.val);

        if (middle && left || middle && right || left && right) {
            res = root;
        }
        return left || middle || right;
    }

    /**
     * 两个节点p,q分为两种情况：
     * p和q在相同子树中
     * p和q在不同子树中
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //如果当前节点为空或等于p或q，则返回当前节点
        if (root == null || root.val == p.val || root.val == q.val) return root;

        //递归遍历左右子树，如果左右子树查到节点都不为空，则表明p和q分别在左右子树中，因此，当前节点即为最近公共祖先；
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left != null && right != null) return root;

        //如果左右子树其中一个不为空，则返回非空节点。
        if (left == null) return right;
        return left;
    }


    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)return null;
        if (root.val == p.val || root.val == q.val)return root;

        TreeNode left = lowestCommonAncestor3(root.left, p, q);
        TreeNode right = lowestCommonAncestor3(root.right, p, q);
        if (left != null && right != null)return root;

        if (left == null)return right;
        return left;
    }


}
