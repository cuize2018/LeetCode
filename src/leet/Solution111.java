package leet;

import java.util.LinkedList;
import java.util.Queue;

public class Solution111 {
    public static void main(String[] args) {

    }

    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if (left != 0 && right != 0)return Math.min(left, right)+1;
        if (left == 0)return right + 1;
        return left + 1;
    }

    public static int minDepth2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left== null && node.right == null)return res;

                if (node.left != null)queue.add(node.left);
                if (node.right != null)queue.add(node.right);
            }
        }
        return res;
    }
}
