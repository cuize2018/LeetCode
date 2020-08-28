package leet;

import org.w3c.dom.ls.LSException;

import java.util.*;

public class Solution94 {
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        TreeNode root_copy = root;
        root.left = new TreeNode(15);
        root.right = new TreeNode(2);

        root = root.right;
        root.left = new TreeNode(3);
        root.right = null;

        System.out.println(inorderTraversal3(root_copy));
    }

    /**
     * 递归方法
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> out = new ArrayList<>();

        if (root == null){
            return out;
        }

        out.addAll(inorderTraversal(root.left));
        out.add(root.val);
        out.addAll(inorderTraversal(root.right));
        return out;
    }

    /**
     * 迭代方法, 使用栈
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root!=null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            out.add(root.val);
            root = root.right;
        }
        return out;
    }

    public static List<Integer> inorderTraversal3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }

    public static List<Integer> postOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> res = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.addFirst(node.val);

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return res;
    }
}
