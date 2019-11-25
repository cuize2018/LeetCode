package leet;

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
}
