package leet.interview;

import leet.TreeNode;

public class Solution26 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);root.left = new TreeNode(12);root.right = new TreeNode(6);
        TreeNode mov = root;
        mov = mov.left;
        mov.left = new TreeNode(8);
        mov.right = new TreeNode(3);

        mov = root.right;
        mov.left = new TreeNode(11);

        TreeNode B = new TreeNode(10);B.left = new TreeNode(12);B.right = new TreeNode(6);
        TreeNode movB = B;
        movB = movB.left;
        movB.left = new TreeNode(8);
        movB.right = new TreeNode(3);

        Solution26 solution26 = new Solution26();
        boolean subStructure = solution26.isSubStructure(root, B);
        System.out.println(subStructure);
    }

    public  boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null)return false;
        return dfs(A,B)||isSubStructure(A.left,B)||isSubStructure(A.right,B);
    }

    /**
     * 当节点 B 为空：说明树 B 已匹配完成（越过叶子节点），因此返回 true ；
     * 当节点 A 为空：说明已经越过树 A 叶子节点，即匹配失败，返回 false ；
     * @param A
     * @param B
     * @return
     */
    private boolean dfs(TreeNode A, TreeNode B) {
        if (B == null)return true;
        if (A == null)return false;
        return A.val == B.val && dfs(A.left,B.left) && dfs(A.right,B.right);
    }


    public boolean isSubStructure2(TreeNode A, TreeNode B) {
        if (A == null || B == null)return false;
        return dfs2(A,B) || isSubStructure2(A.left, B) || isSubStructure2(A.right, B);
    }

    private boolean dfs2(TreeNode A, TreeNode B) {
        if (B == null)return true;
        if (A == null)return false;
        return A.val == B.val && dfs2(A.left, B.left) && dfs2(A.right, B.right);
    }

    public boolean isSubStructure3(TreeNode A, TreeNode B) {
        if (A == null || B == null)return false;

        boolean b = helper(A,B);
        if (b)return true;
        boolean l = isSubStructure3(A.left, B);
        if (l)return true;
        boolean r = isSubStructure3(A.right, B);
        if (r)return true;
        return false;
    }

    private boolean helper(TreeNode left, TreeNode right) {
        if (right == null)return true;
        if (left == null)return false;

        if (left.val == right.val){
            return helper(left.left,right.left) && helper(left.right,right.right);
        }
        return false;
    }


}
