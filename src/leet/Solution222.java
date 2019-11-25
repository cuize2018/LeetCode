package leet;

public class Solution222 {
    private int level_all = 0;
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);TreeNode mov = root;
        mov.left = new TreeNode(2);mov.right = new TreeNode(3);
        mov = mov.left;
        mov.left = new TreeNode(4);mov.right = new TreeNode(5);

        System.out.println(countNodes(root));
    }

    /**
     * 首先需要明确完全二叉树的定义：它是一棵空树或者它的叶子节点只出在最后两层，若最后一层不满则叶子节点只在最左侧。
     *
     * 再来回顾一下满二叉的节点个数怎么计算，如果满二叉树的层数为h，则总节点数为：2^h - 1.
     * 那么我们来对root节点的左右子树进行高度统计，分别记为left和right,有以下两种结果：
     *
     * left == right。这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，左子树必定已经填满了。
     * 所以左子树的节点总数我们可以直接得到，是2^left - 1，加上当前这个root节点，则正好是2^left。再对右子树进行递归统计。
     *
     * left != right。说明此时最后一层不满，但倒数第二层已经满了，可以直接得到右子树的节点个数。
     * 同理，右子树节点+root节点，总数为2^right。再对左子树进行递归查找。
     * @param root
     * @return
     */
    public  static int countNodes(TreeNode root) {
        if (root == null)return 0;

        int left = countLevel(root.left);
        int right = countLevel(root.right);

        if (left == right){
            return countNodes(root.right) + (int)Math.pow(2,left);
        }else {
            return (int)Math.pow(2,right) + countNodes(root.left);
        }
    }

    public static int countLevel(TreeNode root){
        int level = 0;
        while (root != null){
            level++;
            root = root.left;
        }
        return level;
    }

}
