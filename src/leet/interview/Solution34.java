package leet.interview;

import leet.TreeNode;

import java.util.*;

public class Solution34 {
    List<List<Integer>> res = new ArrayList<>();
    Stack<Integer> one = new Stack<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);root.left = new TreeNode(4);root.right = new TreeNode(8);
        int sum = 9;

        Solution34 solution34 = new Solution34();
        List<List<Integer>> lists = solution34.pathSum(root, sum);
        System.out.println(lists);

    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null)return new ArrayList<>();

        one.add(root.val);
        if (root.left == null && root.right == null){
            if (sum - root.val == 0){
                res.add(new ArrayList<>(one));
            }
            return res;
        }

        dfs(root.left, sum-root.val);
        dfs(root.right, sum-root.val);

        return res;
    }

    private void dfs(TreeNode root, int sum) {
        if (root == null)return;

        one.add(root.val);
        if (root.left == null && root.right == null){
            if (sum - root.val == 0){
                res.add(new ArrayList<>(one));
            }
            one.pop();
            return ;
        }

        dfs(root.left, sum-root.val);
        dfs(root.right, sum-root.val);
        one.pop();
    }
}
