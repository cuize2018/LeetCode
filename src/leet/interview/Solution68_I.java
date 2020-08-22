package leet.interview;

import leet.TreeNode;

public class Solution68_I {
    public static void main(String[] args) {

    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (p == null && q == null)return root;
        if (p == null)return q;
        if (q == null)return p;
        if (p.val > q.val)return lowestCommonAncestor2(root,q,p);

        if (p.val < root.val && q.val > root.val){
            return root;
        }

        if (q.val < root.val){
            return lowestCommonAncestor2(root.left, p,q);
        }
        else if (p.val > root.val){
            return lowestCommonAncestor2(root.right,p,q);
        }
        return root;
    }
}
