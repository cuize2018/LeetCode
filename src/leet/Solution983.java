package leet;

public class Solution983 {
    public static void main(String[] args) {
        int[] days = {};
        int[] costs = {2, 7, 15};
        System.out.println(mincostTickets(days, costs));
    }

    public static int mincostTickets(int[] days, int[] costs) {
        if (days.length == 0) return 0;

        int[] dp = new int[366];//dp[i]代表到第i天为止的最低消费
        int j = 0;
        for (int i = 1; i <= 365; i++) {
            if (j == days.length) {//所有旅游日结束
                break;
            }
            if (i == days[j]) {//如果这一天是旅游日
                int a = dp[i - 1] + costs[0];//这一天是单日票，即前一天的最低消费+单日票的价格
                int b = i - 7 >= 0 ? dp[i - 7] + costs[1] : costs[1];//这一天是7日票， 如果是前7日内，则消费为7日票价格；如果是7日后，则是7日前的消费+7日票价格
                int c = i - 30 >= 0 ? dp[i - 30] + costs[2] : costs[2];//这一天是30日票， 如果是前30日内，则消费为30日票价格；如果是30日后，则是30日前的消费+30日票价格
                dp[i] = Math.min(a, Math.min(b, c));//取三种情况的最小值
                j++;
            } else {//如果不是旅游日，则消费和前一天一样
                dp[i] = dp[i - 1];
            }
        }
        return dp[days[j - 1]];//返回最后一个旅游日最低消费
    }
}
