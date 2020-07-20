package leet.interview;

import leet.ListNode;

import java.util.*;

public class Solution57_II {

    public static void main(String[] args) {
        int target = 9;
        int[][] continuousSequence = findContinuousSequence4(target);
        for (int[] temp : continuousSequence) {
            System.out.println(Arrays.toString(temp));
        }
    }

    public static List<List<Integer>> findContinuousSequence2(int target) {
        List<List<Integer>> out = new ArrayList<>();
        for (int i = 2; i <= target - 1; i++) {
            Queue<Integer> one = new ArrayDeque<>();
            //滑动窗口大小i
            int window = i;
            //上界为 最多target个数字*（1/window）
            int sum = 0;
            int k = 1;
            while (k <= window) {
                sum += k;
                one.add(k);
                k++;
            }
            if (sum == target) {
                out.add(new ArrayList<>(one));
            }
            for (int j = 2; j <= Math.floor((double) (target / window)); j++) {
                sum -= (j - 1);
                one.remove();
                sum += (j + window - 1);
                one.add(j + window - 1);

                if (sum == target) {
                    out.add(new ArrayList<>(one));
                }
            }
        }
        Collections.reverse(out);
        return out;
    }

    /**
     * 可变滑动窗口
     *
     * @param target 目标值
     * @return
     */
    public static int[][] findContinuousSequence(int target) {
        List<List<Integer>> out = new ArrayList<>();
        Queue<Integer> one = new ArrayDeque<>();
        int left = 1;
        one.add(left);
        int right = 2;
        one.add(right);
        int sum = 3;

//        9 = 5 + x，x是不可能比5大的
//        15 = 8 + x，x是不可能比8大的
//        所以这就考验总结出一个规律：即一个大于1的正整数，总是小于，它的中值加上一个比中值大的数。
//        所以，当右边界跨过中值的那一刻，窗口内的和就永远也无法触及到target了。
        while (left < right && right <= (target / 2 + 1)) {
            if (sum == target) {
//                当窗口的和恰好等于 target 的时候，我们需要记录此时的结果。设此时的窗口为 [i, j)，那么我们已经找到了一个 i开头的序列，也是唯一一个 i 开头的序列，
//                接下来需要找 i+1开头的序列，所以窗口的左边界要向右移动
                out.add(new ArrayList<>(one));
                left++;
                int left_val = one.remove();
                sum -= left_val;
            } else if (sum < target) {
//                当窗口的和小于 target 的时候，窗口的和需要增加，所以要扩大窗口，窗口的右边界向右移动
                right++;
                one.add(right);
                sum += right;
            } else {
//                当窗口的和大于 target 的时候，窗口的和需要减少，所以要缩小窗口，窗口的左边界向右移动
                left++;
                int left_val = one.remove();
                sum -= left_val;
            }
        }
        int[][] res = new int[out.size()][];
        for (int i = 0; i < res.length; i++) {
            res[i] = new int[out.get(i).size()];
            for (int j = 0; j < out.get(i).size(); j++) {
                res[i][j] = out.get(i).get(j);
            }
        }
        return res;
    }

    /**
     * 求根法
     * 由于连续的正整数数列是一个相当标准的等差数列，我们可以考虑一下把窗口内的数组用等差数列的求和方式进行求和。根据求和公式，
     * x^2−x−y^2−y+2t=0
     * --->
     * x = sqrt(y^2+y-2t+1/4) + 1/2
     * <p>
     * 那么也就是说，根据x的公式，由于t是题目给定的，我们只要不断地枚举y就能求出相应的x来，
     * 只要把不符合条件的x，如复数，非整数等筛选掉，即可得到正确的x，由此形成的范围[x,y]就是我们要找的答案
     *
     * @param target
     * @return
     */
    public static int[][] findContinuousSequence3(int target) {
        List<int[]> out = new ArrayList<>();

        for (long y = 1; y <= target / 2 + 1; y++) {
            double sqrt = Math.sqrt(y * y + y - 2 * target + 0.25);
            double x = sqrt + 0.5;
            //判断x是否为整数
            if (x % 1 == 0) {
                int[] temp = new int[(int) (y - x + 1)];
                int k = 0;
                for (int i = (int) x; i <= y; i++) {
                    temp[k] = i;
                    k++;
                }
                out.add(temp);
            }
        }
        int[][] res = new int[out.size()][];
        for (int i = 0; i < res.length; i++) {
            res[i] = out.get(i);
        }
        return res;
    }

    public static int[][] findContinuousSequence4(int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> one = new ArrayDeque<>();

        int i = 1;
        int j = 2;
        int sum = i;
        one.addLast(i);

        while (i < j && j <= target - 1) {
            if (sum == target) {
                res.add(new ArrayList<>(one));
                i++;
                sum -= one.pollFirst();
            } else if (sum < target) {
                sum += j;
                one.offerLast(j);
                j++;
            } else {
                sum -= one.pollFirst();
                i++;
            }
        }
        int[][] ans = new int[res.size()][];
        for (int k = 0; k < ans.length; k++) {
            ans[k] = new int[res.get(k).size()];
            for (int l = 0; l < ans[k].length; l++) {
                ans[k][l] = res.get(k).get(l);
            }
        }
        return ans;

    }
}
