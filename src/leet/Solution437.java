package leet;

public class Solution437 {
    int count = 0;

    public static void main(String[] args) {
        String tree = "[1,-2,-3,1,3,-2,-1]";
        int sum = 1;
        CodecBinaryTree codecBinaryTree = new CodecBinaryTree();
        TreeNode root = codecBinaryTree.deserialize(tree);
        Solution437 solution437 = new Solution437();
        System.out.println(solution437.pathSum(root, sum));

    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        dfs(root, sum);
        return count;
    }

    //包括所有情况，即包括使用根节点的值和不使用根节点的值两种情况
    private void dfs(TreeNode root, int sum) {
        if (root == null) return;
        int sum1 = sum - root.val;
        if (sum1 == 0) count++;


        dfs(root.left, sum);
        dfs2(root.left, sum - root.val);

        dfs(root.right, sum);
        dfs2(root.right, sum - root.val);
    }

    //该函数为使用根节点的值的情况，此时一旦使用了根节点，则后续子节点就必须连续使用，不可中途断开不使用
    private void dfs2(TreeNode root, int sum) {
        if (root == null) return;

        int sum1 = sum - root.val;
        if (sum1 == 0) count++;

        dfs2(root.left, sum - root.val);
        dfs2(root.right, sum - root.val);
    }
}
