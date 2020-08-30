package Learning;

import leet.TreeNode;

import java.util.*;

public class BinaryTreePostOrder {
    public static void main(String[] args) {

    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        if (root == null)return new ArrayList<>();

        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.addFirst(node.val);

            if (node.left != null)stack.add(node.left);
            if (node.right != null)stack.add(node.right);
        }
        return res;
    }
}
