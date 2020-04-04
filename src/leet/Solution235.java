package leet;

import java.util.*;

public class Solution235 {
    public static void main(String[] args) {

    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Set<TreeNode> parent_p = new HashSet<>(getPath(root, p));
        List<TreeNode> parent_q = getPath(root, q);

        for (int i = parent_q.size() - 1; i >= 0; i--) {
            boolean index = parent_p.contains(parent_q.get(i));
            if (index) {
                return parent_q.get(i);
            }
        }
        return root;
    }

    private static List<TreeNode> getPath(TreeNode root, TreeNode dest) {
        TreeNode mov = root;
        List<TreeNode> parent_p = new ArrayList<>();
        while (mov.val != dest.val) {
            parent_p.add(mov);
            if (dest.val < mov.val) {
                mov = mov.left;
            } else {
                mov = mov.right;
            }
        }
        parent_p.add(mov);
        return parent_p;
    }

    /**
     * 1.从根节点开始遍历树
     * 2.如果节点 p 和节点 q 都在右子树上，那么以右孩子为根节点继续 1 的操作
     * 3.如果节点 p 和节点 q 都在左子树上，那么以左孩子为根节点继续 1 的操作
     * 4.如果条件 2 和条件 3 都不成立，这就意味着我们已经找到节 p 和节点 q 的 LCA 了
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = root;
        if (p.val < root.val && q.val < root.val) {
            ans = lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            ans = lowestCommonAncestor(root.right, p, q);
        }
        return ans;
    }

}
