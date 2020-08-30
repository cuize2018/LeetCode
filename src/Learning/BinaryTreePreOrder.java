package Learning;

import leet.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePreOrder {
    public static void main(String[] args) {

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            res.add(node.val);

            if (node.right != null) queue.addFirst(node.right);
            if (node.left != null) queue.addFirst(node.left);
        }
        return res;
    }
}
