package leet.interview;

import leet.TreeNode;

import java.util.Arrays;

public class Solution07 {
    public static void main(String[] args) {

    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        if (preorder.length == 1) return new TreeNode(preorder[0]);

        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                int[] inorderLeft = Arrays.copyOfRange(inorder, 0, i);
                int[] inorderRight = Arrays.copyOfRange(inorder, i + 1, inorder.length);

                //中序遍历的根节点下标为i， 则i-1为左子树最右节点，故左子树有i个节点
                //所以先序遍历的[1,i]为左子树的节点
                int[] preorderLeft = Arrays.copyOfRange(preorder, 1, 1 + i);
                int[] preorderRight = Arrays.copyOfRange(preorder, 1 + i, preorder.length);

                root.left = buildTree(preorderLeft, inorderLeft);
                root.right = buildTree(preorderRight, inorderRight);
                break;
            }
        }
        return root;
    }
}
