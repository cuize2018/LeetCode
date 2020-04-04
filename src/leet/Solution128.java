package leet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution128 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 1};
        System.out.println(longestConsecutive(nums));
    }

    /**
     * 先排序，然后找出所有连续的片段长度，取最大
     * 时间复杂度：O(nlgn)
     * 算法核心的 for循环恰好运行 n 次，所以算法的时间复杂度由 sort 函数的调用决定，通常会采用 O(nlgn) 时间复杂度的算法。
     *
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int len = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) continue;
            if (nums[i] - nums[i - 1] != 1) {
                max = Math.max(max, len);
                len = 1;
            } else {
                len++;
            }
        }
        return Math.max(max, len);
    }

    /**
     * 时间复杂度：O(n)
     * <p>
     * 尽管在 for 循环中嵌套了一个 while 循环，时间复杂度看起来像是二次方级别的。
     * 但其实它是线性的算法。
     * 因为只有当 currentNum 遇到了一个序列的开始， while 循环才会被执行（也就是 currentNum-1 不在数组 nums 里），
     * while 循环在整个运行过程中只会被迭代 n 次。这意味着尽管看起来时间复杂度为 O(n·n)，实际这个嵌套循环只会运行 O(n + n) = O(n)次。
     * 所有的计算都是线性时间的，所以总的时间复杂度是 O(n)的。
     *
     * @param nums
     * @return
     */
    public static int longestConsecutive2(int[] nums) {
        if (nums.length == 0) return 0;

        Set<Integer> set = new HashSet<>();
        for (int val : nums) set.add(val);
        int max = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currNum = num;
                int len = 1;
                while (set.contains(currNum + 1)) {
                    currNum++;
                    len++;
                }
                max = Math.max(max, len);
            }
        }
        return max;
    }
}
