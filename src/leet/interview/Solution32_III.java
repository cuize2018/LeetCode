package leet.interview;

import leet.TreeNode;

import java.util.*;

public class Solution32_III {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)return res;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        boolean left2Right = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> one = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node;
                if (left2Right) {
                    node = queue.removeFirst();
                    if (node.left != null)queue.addLast(node.left);
                    if (node.right != null)queue.addLast(node.right);
                }
                else {
                    node = queue.removeLast();
                    if (node.right != null)queue.addFirst(node.right);
                    if (node.left != null)queue.addFirst(node.left);
                }
                one.add(node.val);
            }
            left2Right = !left2Right;
            res.add(one);
        }
        return res;
    }
}
