package leet;

import com.sun.corba.se.spi.activation.ActivatorOperations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution95 {


    public static void main(String[] args){
        int n = 4;
        Solution95 s = new Solution95();
        List<TreeNode> fin = s.generateTrees(4);
        int ff = 0;
    }

    /**
     * 解法三 动态规划
     * 通过上边的所有分析，代码可以写了，总体思想就是求长度为 2 的所有情况，
     * 求长度为 3 的所有情况直到 n。而求长度为 len 的所有情况，我们只需要求出一个代表 [ 1 2 ... len ] 的所有情况，
     * 其他的话加上一个偏差，加上当前根节点即可。
     * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-7/
     * @param n
     * @return
     */
    public static List<TreeNode> generateTrees(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<>();

        if (n == 0){
            return dp[0];
        }
        dp[0].add(null);

        for (int len = 1;len <= n;len++){ //长度从1~n
            dp[len] = new ArrayList<>();
            for (int root = 1;root <= len;root++){//根节点从1~len
                int left = root-1;
                int right = len-root;

                for (TreeNode LeftNode: dp[left]){
                    for (TreeNode RightNode: dp[right]){
                        TreeNode rootNode = new TreeNode(root);
                        rootNode.left = LeftNode;
                        rootNode.right = copy(RightNode, root);
                        dp[len].add(rootNode);
                    }
                }
            }
        }
        return dp[n];
    }

    private static  TreeNode copy(TreeNode root, int offset){
        if (root == null){
            return null;
        }
        TreeNode outt = new TreeNode(root.val+offset);
        outt.left = copy(root.left, offset);
        outt.right = copy(root.right,offset);
        return outt;
    }

}
