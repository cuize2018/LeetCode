package leet;

import java.util.*;

public class Solution257 {
    Deque<String> stack = new LinkedList<>();
    List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        TreeNode mov = root;
        mov = mov.left;
        mov.right = new TreeNode(5);

        Solution257 solution257 = new Solution257();
        List<String> strings = solution257.binaryTreePaths(root);
        System.out.println(strings);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return new ArrayList<>();
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            stack.addLast(String.valueOf(root.val));
            res.add(String.join("->", stack));
            stack.pollLast();
            return;
        }

        stack.addLast(String.valueOf(root.val));
        dfs(root.left);
        dfs(root.right);
        stack.pollLast();
    }
}
