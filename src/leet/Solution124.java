package leet;

public class Solution124 {
    private int max = Integer.MIN_VALUE;

    public static void main(String[] args) {

    }

    public int maxPathSum(TreeNode root) {
        help(root);
        return max;
    }

    private int help(TreeNode node) {
        if (node == null) return 0;

        //左子树和右子树的最大值
        int left = Math.max(help(node.left), 0);
        int right = Math.max(help(node.right), 0);

        //新路径的代价值，node节点为最高节点
        int price_new = node.val + left + right;
        //旧路径的代价值，仍需使用node的父节点
        int price_old = node.val + Math.max(left, right);

        //判断是否使用新路径，若新路径值大则选择新路径
        max = Math.max(max, Math.max(price_new,price_old));
        return price_old;
    }


}
