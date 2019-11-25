package leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution94 {
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        TreeNode root_copy = root;
        root.left = new TreeNode(15);
        root.right = new TreeNode(2);

        root = root.right;
        root.left = new TreeNode(3);
        root.right = null;

        System.out.println(inorderTraversal(root_copy));
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

}
