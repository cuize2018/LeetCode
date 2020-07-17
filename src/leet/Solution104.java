package leet;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Solution104 {
    public static void main(String[] args) {

    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null)queue.add(node.left);
                if (node.right != null)queue.add(node.right);
            }
        }
        return res;
    }
}
