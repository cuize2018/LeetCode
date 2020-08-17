package leet;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution103 {
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        TreeNode rootCopy = root;
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root = root.right;
        root.left = new TreeNode(15);
        root.right = new TreeNode(7);

        List<List<Integer>> fin = zigzagLevelOrder(rootCopy);
        System.out.println(fin.toString());
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> out = new ArrayList<>();
        if (root == null)return out;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int level = 1;
        while (!deque.isEmpty()){
            int size = deque.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (level % 2 == 1) {
                    TreeNode node = deque.removeFirst();
                    temp.add(node.val);
                    if (node.left != null)deque.addLast(node.left);
                    if (node.right != null)deque.addLast(node.right);
                }
                else {
                    TreeNode node = deque.removeLast();
                    temp.add(node.val);
                    if (node.right != null) deque.addFirst(node.right);
                    if (node.left != null) deque.addFirst(node.left);
                }
            }
            out.add(temp);
            level++;
        }
        return out;
    }
}
