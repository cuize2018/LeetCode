package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution450 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);TreeNode mov = root;
        mov.left = new TreeNode(3);mov.right = new TreeNode(6);

        mov = mov.left;mov.left = new TreeNode(2);mov.right = new TreeNode(4);
        mov = root.right;mov.right = new TreeNode(7);

        TreeNode out = deleteNode(root,5);
        int l = 0;
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        TreeNode[] keyNodes = findTreeNode(root, key);

        TreeNode keyNode = keyNodes[0];
        TreeNode parentNode = keyNodes[1];
        if (keyNode == null)return root;

        //叶节点
        if (keyNode.left == null && keyNode.right == null) {
            if (parentNode != null) {
                if (parentNode.left == keyNode) parentNode.left = null;
                else parentNode.right = null;
            }
            else {
                return null;
            }
        }

        //单子节点
        else if (keyNode.left == null || keyNode.right == null){
            if (parentNode!= null) {
                if (keyNode.left == null) {
                    if (parentNode.left == keyNode) parentNode.left = keyNode.right;
                    else parentNode.right = keyNode.right;
                } else {
                    if (parentNode.left == keyNode) parentNode.left = keyNode.left;
                    else parentNode.right = keyNode.left;
                }
            }
            else {
                if (keyNode.left!=null)return keyNode.left;
                else return keyNode.right;
            }
        }

        //双子节点
        else {
            //找到右子树最左子节点
            TreeNode root_right = keyNode.right;
            TreeNode[] right_lefts = findMostLeftTreeNode(root_right);

            TreeNode most_left = right_lefts[0];
            TreeNode most_left_parent = right_lefts[1];

            keyNode.val = most_left.val;
            if (most_left_parent != null) {
                //最左为叶节点
                if (most_left.left == null && most_left.right == null) {
                    most_left_parent.left = null;
                }
                //最左为单子节点
                else {
                    most_left_parent.left = most_left.right;
                }
            }
            else {
                keyNode.right = most_left.right;
            }
        }
        return root;
    }

    public static TreeNode[] findTreeNode(TreeNode root, int key){
        TreeNode mov = root;
        TreeNode parent = null;
        while (mov != null && mov.val != key){
            if (key < mov.val){
                parent = mov;
                mov = mov.left;
            }
            else {
                parent = mov;
                mov = mov.right;
            }
        }

        TreeNode[] out = new TreeNode[2];
        out[0] = mov;out[1] = parent;
        return out;
    }


    public static TreeNode[] findMostLeftTreeNode(TreeNode root){
        TreeNode mov = root;
        TreeNode parent = null;
        while (mov.left != null){
            parent = mov;
            mov = mov.left;
        }

        TreeNode[] out = new TreeNode[2];
        out[0] = mov;out[1] = parent;
        return out;
    }
}
