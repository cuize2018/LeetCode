package leet;

public class Solution104 {
    public static void main(String[] args) {

    }

    public static int maxDepth(TreeNode root) {
        int maxLen = 1;
        maxLen = dfs(root, maxLen);
        return  maxLen;
    }

    private static int dfs(TreeNode root, int maxLen) {
        if (root == null)return maxLen;

        int left = dfs(root.left, maxLen+1);
        int right = dfs(root.right, maxLen+1);

        return Math.max(left, right);
    }
}
