package leet;
/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */

import java.util.*;

public class Solution46 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> one = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution46 solution46 = new Solution46();

        System.out.println(solution46.permute2(nums));
    }

    public List<List<Integer>> permute2(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        Set<Integer> alreadyVisited = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            alreadyVisited.add(i);
            one.add(nums[i]);

            dfs(nums, alreadyVisited);

            alreadyVisited.remove(i);
            one.remove(one.size() - 1);
        }
        return res;
    }

    private void dfs(int[] nums, Set<Integer> alreadyVisited) {
        if (alreadyVisited.size() == nums.length) {
            res.add(new ArrayList<>(one));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!alreadyVisited.contains(i)) {
                one.add(nums[i]);
                alreadyVisited.add(i);

                dfs(nums, alreadyVisited);

                alreadyVisited.remove(i);
                one.remove(one.size() - 1);
            }
        }
    }
}
