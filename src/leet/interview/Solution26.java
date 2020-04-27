package leet.interview;

import leet.TreeNode;

public class Solution26 {
    public static void main(String[] args) {
//        TreeNode root = new TreeNode(4);root.left = new TreeNode(2);root.right = new TreeNode(3);
//        TreeNode mov = root;
//        mov = mov.left;
//        mov.left = new TreeNode(4);
//        mov.right = new TreeNode(5);
//
//        mov = root.right;
//        mov.left = new TreeNode(6);
//        mov.right = new TreeNode(7);
//
//        mov = root.left.left;
//        mov.left = new TreeNode(8);
//        mov.right = new TreeNode(9);
//
//        TreeNode B = new TreeNode(4);
//        B.left = new TreeNode(8);
//        B.right = new TreeNode(9);


//        TreeNode root = new TreeNode(1);root.left = new TreeNode(0);root.right = new TreeNode(1);
//        TreeNode mov = root;
//        mov = mov.left;
//        mov.left = new TreeNode(-4);
//        mov.right = new TreeNode(3);
//
//
//        TreeNode B = new TreeNode(1);
//        B.left = new TreeNode(-4);



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


}
