package leet;

import java.util.*;

public class Solution60 {
    int alreadyHas;
    StringBuilder one = new StringBuilder();
    String res = null;
    Set<Integer> set = new HashSet<>();

    public static void main(String[] args) {
        int n = 4;
        int k = 9;
        Solution60 solution60 = new Solution60();
        System.out.println(solution60.getPermutation2(n, k));
    }

    /**
     * n!个数的排列是有依据的，通过k的大小确定第一位的数，然后去掉该数。
     * 然后将k除余之后剩下的数确定第二位的数，这样一次下去直到确定所有排列。
     * <p>
     * n个数字一共有(n)!种排列，那么第1个位置上的的每个不同数字都能有 (n-1)! 种排列，
     * 那么就可以可以对k做对 (n-1)! 的除法得到第n个位置的数字应该是多少，然后再将k减去 t*(n-1)! 再开始循环，其中t就是k对（n-1)!做除法得到的商，
     * <p>
     * exp:
     * 假设 n=5,k=32，建立num矩阵[1,2,3,4,5,6,7,8,9].结果为res；
     * 通过计算得到 (5-1)! = 24，那么k=32除以24得到的是一个大于1小于2的数，那么将t取值为1，取num[t]作为第一位的数，res=2,
     * 在num中删除掉num[t]，num变为[1,3,4,5,6,7,8,9], k= k - t*24 .
     * <p>
     * (n-2)! = 6 ，8/6大于1小于2，所以t取1，第二位的数为num[t],删除num[t],num=[1,4,5,6,7,8,9],然后res=23,k=k-6=2;
     * (n-3)! = 2，2/2等于1，所以t取0，第3位的数为num[t],删除num[t],num=[4,5],然后res=231,k=k-2*0=2;
     * (n-4)! = 1 ，2/1等于2，所以t取1，第4位的数为num[t],删除num[t],num=[4],然后res=2315,k=k-1*1=1;
     * (n-5)! = 1 ，1/1等于1，所以t取0，第5位的数为num[t],删除num[t],num=[],然后res=23154,k=k-1*1=0;
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        if (n == 1) return "1";
        String[] strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List<String> nums = new ArrayList<>(Arrays.asList(strings));

        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int numInGroup = factorial(n - i);
            int groupIdx = (int) (Math.ceil((double) k / numInGroup) - 1);
            k = k - groupIdx * numInGroup;
            res.append(nums.get(groupIdx));
            nums.remove(groupIdx);
        }
        return res.toString();
    }

    private int factorial(int i) {
        if (i == 0) return 0;
        if (i == 1) return 1;
        return i * factorial(i - 1);
    }

    StringBuilder out = new StringBuilder();
    String[] strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    List<String> nums = new ArrayList<>(Arrays.asList(strings));

    public String getPermutation2(int n, int k) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] * i;
        }

        helper(n, k, dp);
        return out.toString();
    }

    private void helper(int n, int k, int[] dp) {
        if (n == 1) {
            out.append(nums.get(0));
            return;
        }

        int groupIdx = (k - 1) / dp[n - 1];
        out.append(nums.get(groupIdx));
        nums.remove(groupIdx);
        helper(n - 1, k - groupIdx * dp[n - 1], dp);
    }
}
