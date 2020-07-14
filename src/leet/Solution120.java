package leet;

import javax.jnlp.ClipboardService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solution120 {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(2);
        list.add(a);
        List<Integer> b = new ArrayList<>();
        b.add(3);
        b.add(4);
        list.add(b);
        List<Integer> c = new ArrayList<>();
        c.add(6);
        c.add(5);
        c.add(7);
        list.add(c);
        List<Integer> d = new ArrayList<>();
        d.add(4);
        d.add(1);
        d.add(8);
        d.add(3);
        list.add(d);

        System.out.println(minimumTotal(list));


    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int[][] dp = new int[rows][rows];

        dp[0][0] = triangle.get(0).get(0);
        if (rows == 1) return dp[0][0];

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            if (dp[rows - 1][i] < min) {
                min = dp[rows - 1][i];
            }
        }
        return min;
    }

    public static int minimumTotal3(List<List<Integer>> triangle) {
        if (triangle.size() == 0) return 0;
        if (triangle.size() == 1) return triangle.get(0).get(0);

        int[] dp = new int[1];
        dp[0] = triangle.get(0).get(0);

        int res = Integer.MAX_VALUE;
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> line = triangle.get(i);
            int[] dpCurr = new int[line.size()];
            for (int j = 0; j < line.size(); j++) {
                int v = line.get(j);
                if (j == 0) {
                    v = v + dp[0];
                } else if (j == line.size() - 1) {
                    v = v + dp[dp.length - 1];
                } else {
                    v = Math.min(dp[j], dp[j - 1]) + v;
                }
                dpCurr[j] = v;
                if (i == triangle.size() - 1) {
                    res = Math.min(res, v);
                }
            }
            dp = dpCurr;
        }
        return res;
    }
}
