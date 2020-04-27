package leet.interview;

import leet.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution32 {
    public static void main(String[] args) {

    }

    public static int[] levelOrder(TreeNode root) {
        if (root == null)return new int[0];
        List<Integer> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            TreeNode node = queue.remove();
            res.add(node.val);

            if (node.left != null)queue.add(node.left);
            if (node.right != null)queue.add(node.right);
        }

        int[] out = new int[res.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = res.get(i);
        }
        return out;

    }
}
