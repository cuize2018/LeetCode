package leet;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class Solution47 {
    public static void main(String[] args) {
        int[] a = {1, 1, 2};
        System.out.println(new Solution47().permuteUnique(a));
    }

    List<List<Integer>> res = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) return res;
        boolean[] visited = new boolean[nums.length];

        Arrays.sort(nums);
        dfs(nums, visited, 0);
        return res;
    }

    private void dfs(int[] nums, boolean[] visited, int depth) {
        int n = nums.length;
        if (depth == n) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;

            stack.push(nums[i]);
            visited[i] = true;

            dfs(nums, visited, depth + 1);

            visited[i] = false;
            stack.pop();
        }
    }
}
