package leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution887 {
    public static void main(String[] args) {
        Solution887 solution887 = new Solution887();
        System.out.println(solution887.superEggDrop2(2, 6));
    }

    Map<Integer, Integer> map = new HashMap<>();

    public int superEggDrop(int K, int N) {
        return dp2(K, N);
    }
    //超时
    public int dp(int K, int N) {
        if (K == 1) return N;
        if (N == 0) return 0;

        Integer key = N * 100 + K;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            res = Math.min(res, Math.max(dp(K - 1, i - 1), dp(K, N - i)) + 1);
        }
        map.put(key, res);
        return res;
    }

    public int dp2(int K, int N) {
        if (K == 1) return N;
        if (N == 0) return 0;

        Integer key = N * 100 + K;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int res = Integer.MAX_VALUE;
//      用二分搜索代替线性搜索
        int left = 1;
        int right = N;

        while (left <= right) {
            int middle = (left + right) >>> 1;
            int broken = dp2(K - 1, middle - 1);
            int not_broken = dp2(K, N - middle);
//          res = min(max(碎，没碎) + 1)
            if (broken > not_broken) {
                right = middle - 1;
                res = Math.min(res, broken + 1);
            } else {
                left = middle + 1;
                res = Math.min(res, not_broken + 1);
            }
        }

        map.put(key, res);
        return res;
    }


    public int superEggDrop2(int K, int N) {
        return dp4(K, N);
    }
    //超时
    private int dp3(int k, int n) {
        if (k == 1) return n;
        if (n == 0) return 0;

        int key = n * 100 + k;
        if (map.containsKey(key)) return map.get(key);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            res = Math.min(res, Math.max(dp3(k - 1, i - 1), dp3(k, n - i)) + 1);
        }
        map.put(key, res);
        return res;
    }

    private int dp4(int k, int n) {
        if (k == 1) return n;
        if (n == 0) return 0;

        int key = n * 100 + k;
        if (map.containsKey(key)) return map.get(key);

        int low = 1;
        int high = n;
        int res = Integer.MAX_VALUE;

        while (low <= high) {
            int middle = (low + high) >>> 1;
            int broken = dp4(k - 1, middle - 1) + 1;
            int not_broken = dp4(k, n - middle) + 1;
            if (broken > not_broken) {
                high = middle - 1;
                res = Math.min(res, broken);
            } else {
                low = middle + 1;
                res = Math.min(res, not_broken);
            }
        }

        map.put(key, res);
        return res;
    }





    private int dp5(int k, int n) {
        int[][] dp = new int[k+1][n+1];
        for (int i = 0; i < k+1; i++) {
            Arrays.fill(dp[i], 9999);
        }

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        dp[0][1] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i][1] = 1;
        }

        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 0;
            dp[1][i] = i;
        }

        for (int i = 2; i <= k; i++) {
            for (int j = 2; j <= n; j++) {
                for (int l = 1; l <= j; l++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i-1][l-1], dp[i][j-l])+1);
                }
            }
        }
        return dp[k][n];
    }

    private int dp6(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 0; i < k + 1; i++) {
            Arrays.fill(dp[i], 9999);
        }

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        dp[0][1] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i][1] = 1;
        }

        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 0;
            dp[1][i] = i;
        }

        for (int i = 2; i <= k; i++) {
            for (int j = 2; j <= n; j++) {
                int low = 1;
                int high = j;
                while (low < high){
                    int middle =  low + (high - low + 1) / 2;
                    int broken = dp[i-1][middle-1];
                    int not_broken = dp[i][j-middle];
                    if (broken > not_broken){
                        high = middle-1;
                    }
                    else {
                        low = middle;
                    }

                }
                dp[i][j] = Math.max(dp[i-1][low-1], dp[i][j-low])+1;
            }
        }
        return dp[k][n];
    }

}
