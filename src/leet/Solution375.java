package leet;

public class Solution375 {
    private int nx;
    private int[][] dp;
    private int[] nums;

    public static void main(String[] args) {

    }

    /**
     * 考虑动态规划， 这里面关键的一步是，第一次选择哪个数字（当然很多时候也可以猜想最后一步选择哪个数字），
     * 因为我们不知道结果，所以只能brute force把所有可能的数字都尝试一遍。
     * 然后选择了一个数以后，就把数组分成了左右两边， 这时候，左边和右边就形成了重叠子问题。
     * 也就是说，对于一个数组arr, 如果要计算from到to，如果选择了arr[i],
     * 那么， 成本就变成Math.max(cost[from,i-1], cost[i+1][to]) + arr[i]
     * 这样，就可以通过递归+memorization的方式来top down解决
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        this.nx = n;
        dp = new int[n][n];
        nums = new int[n];

        for (int i = 0;i<n;i++){
            for (int j = 0;j<n;j++){
                dp[i][j] = -1;
            }
        }

        for (int i = 0;i<nums.length;i++){
            nums[i] = i+1;
        }

        return find(0,n-1);
    }

    private int find(int from, int to){
        if (to <= from)return 0;
        if (dp[from][to] != -1)return dp[from][to];

        int min = Integer.MAX_VALUE;
        for (int i = from; i <= to;i++){
            int left_val = find(from, i-1);
            int right_val = find(i+1, to);
            int cost = Math.max(left_val, right_val) + nums[i];
            min = Math.min(min, cost);
        }
        dp[from][to] = min;
        return min;
    }

}
