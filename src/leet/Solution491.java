package leet;

import java.util.*;

public class Solution491 {
    public List<List<Integer>> out = new ArrayList<>();
    public Stack<Integer> idx = new Stack<>();
    public Stack<Integer> val = new Stack<>();

    public static void main(String[] args) {
        int[] exp = {4, 6, 7, 7};
        Solution491 sol = new Solution491();

        System.out.println(sol.findSubsequences(exp));
    }

    /**
     * 深度优先DFS
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        int n = nums.length;
        if (n == 0) return new ArrayList<>();
        boolean[] visited = new boolean[n];

        dfs3(nums, Integer.MIN_VALUE, 0);
        return new ArrayList<>(out);
    }

    private void dfs2(int[] nums, boolean[] visited) {
        if (idx.size() >= 2) {
            out.add(new ArrayList<>(val));
        }

        int i = idx.isEmpty() ? 0 : idx.peek() + 1;
        for (; i < nums.length; i++) {
            if (!visited[i]) {
                if (idx.isEmpty() || nums[i] >= nums[idx.peek()]) {
                    idx.push(i);
                    val.push(nums[i]);
                    visited[i] = true;

                    dfs2(nums, visited);

                    visited[i] = false;
                    val.pop();
                    idx.pop();
                }
            }
        }
    }

    private void dfs3(int[] nums, int last, int curr) {
        if (curr == nums.length) {
            if (val.size() >= 2) {
                out.add(new ArrayList<>(val));
            }
            return;
        }

        if (nums[curr] >= last) {
            val.push(nums[curr]);
            dfs3(nums, nums[curr], curr + 1);
            val.pop();
        }
        if (nums[curr] != last) {
            dfs3(nums, last, curr + 1);
        }
    }

}
