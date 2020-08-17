package leet;

import java.util.*;

public class Solution145 {
    public static void main(String[] args) {

    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        if (root == null)return new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.addFirst(node.val);

            if (node.left != null){
                stack.push(node.left);
            }
            if (node.right != null){
                stack.push(node.right);
            }

        }
        return res;
    }

    //后序遍历倒着看为：右先序遍历
    public static List<Integer> postorderTraversal2(TreeNode root) {
        if (root == null)return new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        stack.addLast(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.removeLast();
            res.addFirst(node.val);

            if (node.left != null){
                stack.addLast(node.left);
            }
            if (node.right != null){
                stack.addLast(node.right);
            }

        }
        return res;
    }
}
