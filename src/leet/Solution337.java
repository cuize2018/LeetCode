package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution337 {
    Map<TreeNode, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        Solution337 solution337 = new Solution337();
        System.out.println(solution337.rob3(root));
    }

    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        // 抢，然后去下下家
        int doRob = root.val + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right)) +
                (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
        // 不抢，然后去下家
        int skipRob = (rob(root.left) + rob(root.right));

        int res = Math.max(doRob, skipRob);
        memo.put(root, res);
        return res;
    }

    public int rob2(TreeNode root) {
        if (root == null) return 0;
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }

    /* 返回一个大小为 2 的数组 arr
    arr[0] 表示不抢 root 的话，得到的最大钱数
    arr[1] 表示抢 root 的话，得到的最大钱数 */
    private int[] dp(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        int[] left = dp(root.left);
        int[] right = dp(root.right);
        // 抢，下家就不能抢了
        int rob = root.val + left[0] + right[0];
        // 不抢，下家可抢可不抢，取决于收益大小
        int not_rob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{not_rob, rob};
    }

    public int rob3(TreeNode root) {
        if (root == null) return 0;
        if (memo.containsKey(root)){
            return memo.get(root);
        }

        int left = 0;
        int right = 0;
        if (root.left != null) {
            left = rob3(root.left.left) + rob3(root.left.right);
        }
        if (root.right != null) {
            right = rob3(root.right.left) + rob3(root.right.right);
        }
        int robRoot = root.val + left + right;
        int notRobRoot = rob3(root.left) + rob3(root.right);

        int v = Math.max(robRoot, notRobRoot);
        memo.put(root, v);
        return v;
    }


}
