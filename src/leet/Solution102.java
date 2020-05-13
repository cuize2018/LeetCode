package leet;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution102 {
    private List<List<Integer>> out = new ArrayList<>();

    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        TreeNode rootCopy = root;
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root = root.right;
        root.left = new TreeNode(15);
        root.right = new TreeNode(7);

        Solution102 s = new Solution102();
        List<List<Integer>> fin = s.levelOrder(rootCopy);
        System.out.println(fin.toString());
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> oneLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();

                oneLevel.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null)queue.add(node.right);
            }
            res.add(oneLevel);
        }
        return res;
    }


}
