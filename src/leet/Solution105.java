package leet;

import sun.dc.pr.PRError;

import java.util.Arrays;

public class Solution105 {
    public static void main(String[] args){
//        TreeNode root = new TreeNode(3);
//        TreeNode rootCopy = root;
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root = root.right;
//        root.left = new TreeNode(15);
//        root.right = new TreeNode(7);

        int[] pre = {3,20};
        int[] in = {20,3};

        TreeNode r = buildTree(pre, in);
        int ff = 0;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0)return null;
        if (preorder.length == 1)return new TreeNode(preorder[0]);


        TreeNode rootNode = new TreeNode(preorder[0]);
        for (int i = 0;i<inorder.length;i++){
            if (inorder[i] == preorder[0]){
                int[] inOrderLeft = Arrays.copyOfRange(inorder,0,i);
                int[] inOrderRight = Arrays.copyOfRange(inorder,i+1,inorder.length);

                int[] preOrderLeft = Arrays.copyOfRange(preorder, 1,i+1);
                int[] preOrderRight = Arrays.copyOfRange(preorder, i+1,preorder.length);

                rootNode.left = buildTree(preOrderLeft, inOrderLeft);
                rootNode.right = buildTree(preOrderRight, inOrderRight);
                break;
            }
        }
        return rootNode;
    }

    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder.length == 0)return null;

        TreeNode root = new TreeNode(preorder[0]);
        int rootIdx = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val){
                rootIdx = i;break;
            }
        }
        int leftNums = rootIdx;

        int[] intLeftSubTree = Arrays.copyOfRange(inorder, 0, rootIdx);
        int[] inRightSubTree = Arrays.copyOfRange(inorder, rootIdx+1, inorder.length);

        int[] preLeftSubTree = Arrays.copyOfRange(preorder, 1, 1+leftNums);
        int[] preRightSubTree = Arrays.copyOfRange(preorder, 1+leftNums, preorder.length);

        root.left = buildTree2(preLeftSubTree, intLeftSubTree);
        root.right = buildTree2(preRightSubTree, inRightSubTree);
        return root;
    }
}
