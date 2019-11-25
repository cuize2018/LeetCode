package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution103 {
    private List<List<Integer>> out = new ArrayList<>();
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        TreeNode rootCopy = root;
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root = root.right;
        root.left = new TreeNode(15);
        root.right = new TreeNode(7);

        Solution103 s = new Solution103();
        List<List<Integer>> fin = s.zigzagLevelOrder(rootCopy);
        System.out.println(fin.toString());
    }

    public  List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null)return out;
        if (root.left == null && root.right == null){
            List<Integer> l = new ArrayList<>();
            l.add(root.val);
            out.add(l);
            return out;
        }

        List<TreeNode> lastLevel = new ArrayList<>();
        List<Integer> val = new ArrayList<>();
        lastLevel.add(root);
        val.add(root.val);
        out.add(val);

        getLevel(lastLevel,1);
        return out;
    }

    private  void getLevel(List<TreeNode> lastLevel, int level){
        List<TreeNode> thisLevel = new ArrayList<>();
        List<Integer> val = new ArrayList<>();

        for (int i = lastLevel.size() - 1; i >= 0; i--) {
            TreeNode node = lastLevel.get(i);

            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;

            if (level % 2 == 0) {
                if (leftNode != null) {
                    thisLevel.add(leftNode);
                    val.add(leftNode.val);
                }
                if (rightNode != null) {
                    thisLevel.add(rightNode);
                    val.add(rightNode.val);
                }
            }
            else {
                if (rightNode != null) {
                    thisLevel.add(rightNode);
                    val.add(rightNode.val);
                }
                if (leftNode != null) {
                    thisLevel.add(leftNode);
                    val.add(leftNode.val);
                }
            }
        }

        if (thisLevel.isEmpty()){
            return;
        }
        out.add(val);
        getLevel(thisLevel, level+1);
    }
}
