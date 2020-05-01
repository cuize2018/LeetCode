package leet.interview;

import leet.ListNode;
import leet.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution37 {
    public static void main(String[] args) {

    }


}

class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)return "[]";
        List<String> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        res.add(String.valueOf(root.val));

        while (!queue.isEmpty()){
            TreeNode node = queue.remove();
            if (node == null)res.add("null");
            else {
                queue.add(node.left);
                queue.add(node.right);
                res.add(String.valueOf(node.val));
            }
        }
        String str = String.join(",", res);
       // System.out.println(str);
        return "[" + str + "]";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("[]"))return null;

        data = data.substring(1, data.length()-1);
        String[] nodes = data.split(",");

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        queue.add(root);

        int idx = 1;
        while (idx < nodes.length && !queue.isEmpty()){
            TreeNode node = queue.remove();

            String left = nodes[idx];
            if (!left.equals("null")) {
                TreeNode leftNode = new TreeNode(Integer.parseInt(left));
                node.left = leftNode;
                queue.add(leftNode);
            }
            idx++;

            String right = nodes[idx];
            if (!right.equals("null")){
                TreeNode rightNode = new TreeNode(Integer.parseInt(right));
                node.right = rightNode;
                queue.add(rightNode);
            }
            idx++;
        }
        return root;
    }
}
