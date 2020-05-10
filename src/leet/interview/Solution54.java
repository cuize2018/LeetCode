package leet.interview;

import leet.TreeNode;

public class Solution54 {
    int count = 0;
    int val = -1;

    public static void main(String[] args) {

    }

    //右中左
    public int kthLargest(TreeNode root, int k) {
        dfs(root, k);
        return val;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null) return;

        dfs(root.right, k);
        count++;
        if (count == k) {
            val = root.val;
            return;
        }
        dfs(root.left, k);
    }
}
