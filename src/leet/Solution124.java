package leet;

public class Solution124 {
    private int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode mov = root;
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);

        mov = mov.left;
        mov.left = new TreeNode(1);
        mov.right = new TreeNode(3);
        mov = mov.left;
        mov.left = new TreeNode(-1);

        mov = root.right;
        mov.left = new TreeNode(-2);

        Solution124 solution124 = new Solution124();
        int res = solution124.maxPathSum2(root);
        System.out.println(res);
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
        max = Math.max(max, Math.max(price_new, price_old));
        return price_old;
    }


    public int maxPathSum2(TreeNode root) {
        func(root);
        return max;
    }

    public int func(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(func(root.left), 0);
        int right = Math.max(func(root.right), 0);

        int priceNew = root.val + left + right;
        int priceOld = root.val + Math.max(left, right);

        max = Math.max(max, Math.max(priceNew, priceOld));
        return priceOld;
    }
}
