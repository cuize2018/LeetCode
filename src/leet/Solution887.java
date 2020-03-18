package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution887 {
    public static void main(String[] args) {
        Solution887 solution887 = new Solution887();
        System.out.println(solution887.superEggDrop(4, 5000));
    }

    Map<Integer, Integer> map = new HashMap<>();

    public int superEggDrop(int K, int N) {
        return dp2(K, N);
    }

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

}
