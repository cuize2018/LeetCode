package leet;

import java.util.*;

public class Solution144 {
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> out = new ArrayList<>();

        if (root == null){
            return out;
        }

        out.add(root.val);
        out.addAll(preorderTraversal(root.left));
        out.addAll(preorderTraversal(root.right));
        return out;
    }

    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        if (root == null)return res;
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);

            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return res;

    }
}
