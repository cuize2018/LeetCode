package leet;

import java.util.Arrays;

public class Solution106 {
    public static void main(String[] args){
        int[] in = {9,3,15,20,7};
        int[] post = {9,15,7,20,3};
        TreeNode r = buildTree(in, post);
        int ff = 0;
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length == 0)return null;
        if (postorder.length == 1)return new TreeNode(postorder[0]);


        TreeNode rootNode = new TreeNode(postorder[postorder.length-1]);
        for (int i = 0;i<inorder.length;i++){
            if (inorder[i] == postorder[postorder.length-1]){
                int[] inOrderLeft = Arrays.copyOfRange(inorder,0,i);
                int[] inOrderRight = Arrays.copyOfRange(inorder,i+1,inorder.length);

                int[] postOrderLeft = Arrays.copyOfRange(postorder, 0,i);
                int[] postOrderRight = Arrays.copyOfRange(postorder, i,postorder.length-1);

                rootNode.left = buildTree(inOrderLeft, postOrderLeft);
                rootNode.right = buildTree(inOrderRight, postOrderRight);
                break;
            }
        }
        return rootNode;
    }
}
