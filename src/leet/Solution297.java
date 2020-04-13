package leet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution297 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode mov = root;
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        mov = mov.left;
        mov.left = new TreeNode(1);
        mov.right = new TreeNode(3);

        mov = root;

        mov = mov.right;
        mov.left = new TreeNode(2);
        mov.right = new TreeNode(4);


        CodecBinaryTree codecBinaryTree = new CodecBinaryTree();
        String serialize = codecBinaryTree.serialize(root);
        System.out.println(serialize);
        TreeNode deserialize = codecBinaryTree.deserialize(serialize);

        String serialize1 = codecBinaryTree.serialize(deserialize);
        System.out.println(serialize1);
        int a = 0;
    }
}

class CodecBinaryTree {
    // Encodes a tree to a single string.
    // 层序遍历
    public String serialize(TreeNode root) {
        ArrayList<TreeNode> thisLevel = new ArrayList<>();
        boolean allNull = false;

        thisLevel.add(root);
        if (root == null) allNull = true;

        List<String> list = hierarchical(thisLevel, allNull);
        return list.toString();
    }

    private List<String> hierarchical(List<TreeNode> thisLevel, boolean allNull) {
        List<String> list = new ArrayList<>();
        if (allNull) return list;
        if (thisLevel.size() == 0) return list;

        List<TreeNode> nextLevel = new ArrayList<>();
        boolean flag = true;
        for (TreeNode treeNode : thisLevel) {
            if (treeNode == null) {
                list.add("null");
                continue;
            }
            list.add(String.valueOf(treeNode.val));
            nextLevel.add(treeNode.left);
            nextLevel.add(treeNode.right);
            if (treeNode.left != null || treeNode.right != null) flag = false;
        }
        List<String> nextLevelNodes = hierarchical(nextLevel, flag);
        list.addAll(nextLevelNodes);
        return list;
    }

    // Decodes your encoded data to tree.
    //BFS
    public TreeNode deserialize(String data) {
        String str = data.substring(1, data.length() - 1);
        if (str.length() == 0) return null;

        String[] nodes = str.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        queue.add(root);

        int idx = 1;
        while (idx < nodes.length && !queue.isEmpty()) {
            TreeNode curr = queue.remove();
            if (curr == null) continue;

            curr.left = nodes[idx].trim().equals("null") ? null : new TreeNode(Integer.parseInt(nodes[idx].trim()));
            curr.right = nodes[idx + 1].trim().equals("null") ? null : new TreeNode(Integer.parseInt(nodes[idx + 1].trim()));
            idx += 2;

            queue.add(curr.left);
            queue.add(curr.right);
        }
        return root;
    }
}
