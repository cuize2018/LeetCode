package leet;

public class Solution543 {
    int max = 0;

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(1);
        TreeNode mov = rootNode;
        mov.left = new TreeNode(2);
        mov.right = new TreeNode(3);
        mov = mov.left;
        mov.left = new TreeNode(4);
        mov.right = new TreeNode(5);

        Solution543 solution543 = new Solution543();

        System.out.println(solution543.diameterOfBinaryTree(rootNode));
    }

    /**
     * 寻找每一个节点的左右子树深度和的最大值
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        findDeepest(root);
        return max;
    }

    private int findDeepest(TreeNode startNode) {
        int len1 = 0;
        if (startNode.left != null) {
            len1 = findDeepest(startNode.left) + 1;

        }
        int len2 = 0;
        if (startNode.right != null) {
            len2 = findDeepest(startNode.right) + 1;
        }
        max = Math.max(max, len1 + len2);
        return Math.max(len1, len2);
    }

}
