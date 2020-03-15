package leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution149 {
    public static void main(String[] args) {
//        int[][] points = {{1,1},{1,1},{2,2},{2,2}};
//        int[][] points = {{1,1},{2,2},{3,3}};
        int[][] points = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        System.out.println(maxPoints(points));
    }

    /**
     * 回到数学上，给定两个点可以唯一的确定一条直线，表达式为 y = kx + b。
     * 对于 (1,2),(1,3),(1,4) 这些点求出来的表达式都是唯一确定的。
     * <p>
     * 所以我们当考虑 1,2 两个点的时候，我们可以求出 k 和 b 把它存到 HashSet 中，然后当考虑 1,3 以及后边的点的时候，先求出 k 和 b，然后从 HashSet 中看是否存在即可。
     *
     * @param points
     * @return
     */
    public static int maxPoints(int[][] points) {
        if (points.length < 3) return points.length;
        int i = 0;
        for (; i < points.length - 1; i++) {
            if (points[i][0] != points[i + 1][0] || points[i][1] != points[i + 1][1]) {
                break;
            }
        }
        if (i == points.length - 1) return points.length;

        HashSet<String> set = new HashSet<>();
        int max = 0;
        for (int j = 0; j < points.length; j++) {
            int[] point1 = points[j];
            for (int k = i + 1; k < points.length; k++) {
                int[] point2 = points[k];
                if (point1[0] == point2[0] && point1[1] == point2[1]) {
                    continue;
                }

                String key = calK(point1, point2) + "#" + calB(point1, point2);
                if (set.contains(key)) continue;
                int temp_max = 0;
                for (int l = 0; l < points.length; l++) {
                    if (l != j && l != k) {
                        int[] point_temp = points[l];
                        if (test(point_temp, point1, point2)) {
                            temp_max++;
                        }
                    }
                }
                max = Math.max(max, temp_max);
                set.add(key);
            }
        }
        return max + 2;

    }

    private static boolean test(int[] point_temp, int[] point1, int[] point2) {
        return (long) (point2[1] - point1[1]) * (point_temp[0] - point2[0]) == (long) (point_temp[1] - point2[1]) * (point2[0] - point1[0]);
    }

    private static double calK(int[] point1, int[] point2) {
        if (point1[0] - point2[0] == 0) return Double.POSITIVE_INFINITY;
        return (double) Math.abs(point1[1] - point2[1]) / (double) Math.abs(point1[0] - point2[0]);
    }

    private static double calB(int[] point1, int[] point2) {
        if (point1[1] - point2[1] == 0) return Double.POSITIVE_INFINITY;
        return (double) (point2[0] - point1[0]) * (-point1[1]) / (point2[1] - point1[1]) + point1[0];
    }

    /**
     * 灵感应该来自于直线方程的另一种表示方式，「点斜式」，换句话，一个点加一个斜率即可唯一的确定一条直线。
     * 所以我们可以对「点」进行分类然后去求，问题转换成，经过某个点的直线，哪条直线上的点最多。
     *
     * 当确定一个点后，平面上的其他点都和这个点可以求出一个斜率，斜率相同的点就意味着在同一条直线上。
     * 所以我们可以用 HashMap 去计数，斜率作为 key，然后遍历平面上的其他点，相同的 key 意味着在同一条直线上。
     * 上边的思想解决了「经过某个点的直线，哪条直线上的点最多」的问题。接下来只需要换一个点，然后用同样的方法考虑完所有的点即可。
     * @param points
     * @return
     */
    public static int maxPoints2(int[][] points) {
        if (points.length < 3) return points.length;
        int res = 0;

        for (int i = 0; i < points.length; i++) {
            int dup = 0;
            HashMap<String, Integer> map = new HashMap<>();
            int max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];

                if (x == 0 && y == 0) {
                    dup++;
                    continue;
                }
                int gcd = gcd(x, y);
                x = x / gcd;
                y = y / gcd;

                String key = x + "#" + y;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            res = Math.max(res, max + dup + 1);
        }
        return res;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
