package leet;

import java.util.ArrayList;
import java.util.List;

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

        getLevel(lastLevel);
        return out;
    }

    private void getLevel(List<TreeNode> lastLevel){
        List<TreeNode> thisLevel = new ArrayList<>();
        List<Integer> val = new ArrayList<>();

        for (TreeNode node:lastLevel){
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;

            if (leftNode != null){
                thisLevel.add(leftNode);
                val.add(leftNode.val);
            }
            if (rightNode != null){
                thisLevel.add(rightNode);
                val.add(rightNode.val);
            }
        }
        if (thisLevel.isEmpty()){
            return;
        }
        out.add(val);
        getLevel(thisLevel);

    }

}
