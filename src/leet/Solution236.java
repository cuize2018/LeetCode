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

}
