package leet;

import java.util.*;

public class Solution199 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode mov = root;
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        mov = root.left;
        mov.right = new TreeNode(5);
        mov = root.right;
//        mov.right = new TreeNode(4);

//        TreeNode root = new TreeNode(1);TreeNode mov = root;
//        root.left = new TreeNode(2);

        Solution199 s = new Solution199();
        System.out.println(s.rightSideView(root));
    }

    //层序遍历取最右
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();
        LinkedList<TreeNode> thisLevel = new LinkedList<>();
        thisLevel.add(root);

        return levelOrder(thisLevel);
    }

    private List<Integer> levelOrder(LinkedList<TreeNode> thisLevel) {
        List<Integer> res = new ArrayList<>();
        if (thisLevel == null || thisLevel.isEmpty()) return res;

        TreeNode mostRight = thisLevel.getLast();
        res.add(mostRight.val);

        LinkedList<TreeNode> nextLevel = new LinkedList<>();
        for (TreeNode treeNode : thisLevel) {
            if (treeNode.left != null) {
                nextLevel.add(treeNode.left);
            }
            if (treeNode.right != null) {
                nextLevel.add(treeNode.right);
            }
        }
        List<Integer> rest = levelOrder(nextLevel);
        res.addAll(rest);
        return res;
    }

    public List<Integer> rightSideView2(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<Integer> res = new ArrayList<>();

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                size--;
                TreeNode node = queue.remove();
                if (size == 0){
                    res.add(node.val);
                }
                if (node.left != null)queue.add(node.left);
                if (node.right != null)queue.add(node.right);

            }
        }
        return res;
    }

    public List<Integer> rightSideView3(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<Integer> res = new ArrayList<>();

        while (!queue.isEmpty()){
            int size = queue.size();
            res.add(queue.peek().val);
            while (size > 0){
                size--;
                TreeNode node = queue.remove();
                if (node.right != null)queue.add(node.right);
                if (node.left != null)queue.add(node.left);
            }
        }
        return res;
    }

}
