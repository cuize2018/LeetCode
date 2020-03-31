package leet;

import java.util.*;

public class Solution679 {
    public static void main(String[] args) {
        int[] nums = {1, 9, 1, 2};
        Solution679 solution679 = new Solution679();
        System.out.println(solution679.judgePoint24(nums));
    }

    /**
     * 只有 4 张牌，且只能执行 4 种操作。即使所有运算符都不进行交换，最多也只有 12 * 6 * 2 * 4 * 4 * 4 =9216 种可能性，这使得我们可以尝试所有这些可能。
     *
     * 具体来说，我们有 12 种方式先选出两个数字（有序），并执行 4 种操作之一（12 * 4）。然后，剩下 3 个数字，我们从中选择 2 个并执行 4 种操作之一（6 * 4）。
     *
     * 最后我们剩下两个数字，并在 2 * 4 种可能之中作出最终选择。
     *
     * 我们将对我们的数字或结果数字执行 3 次二元运算（+，-，*，/ 是运算）。因为 - 和 / 不满足交换律，我们必须仔细考虑 a / b 和 b / a。
     *
     * 对于在我们的列表中移除 a, b 这两个数字的每一种方法，以及它们可能产生的每种结果，如 a + b、a / b等，我们将采用递归的方法解决这个较小的数字列表上的问题。
     * @param nums
     * @return
     */
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        return dfs2(list);
    }

    public boolean dfs2(List<Double> nums) {
        if (nums.size() == 0) return false;
        if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 1e-6;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i != j) {
                    List<Double> nums2 = new ArrayList<>();
                    for (int k = 0; k < nums.size(); k++) {
                        if (k != i && k != j) nums2.add(nums.get(k));
                    }

                    for (int k = 0; k < 4; k++) {
                        // k < 2的运算都符合交换律，无需重复计算
                        if (k < 2 && j > i) continue;
                        if (k == 0) nums2.add(nums.get(i) + nums.get(j));
                        if (k == 1) nums2.add(nums.get(i) * nums.get(j));
                        if (k == 2) nums2.add(nums.get(i) - nums.get(j));
                        if (k == 3) {
                            if (nums.get(j) != 0) {
                                nums2.add(nums.get(i) / nums.get(j));
                            } else {
                                continue;
                            }
                        }
                        if (dfs2(nums2)) return true;
                        nums2.remove(nums2.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}
