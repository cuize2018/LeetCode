package leet;

import java.util.*;

public class Solution46 {
    List<List<Integer>> res = new ArrayList<>();
    Stack<Integer> one = new Stack<>();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution46 solution46 = new Solution46();

        System.out.println(solution46.permute(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];

        dfs(nums, visited);
        return res;
    }

    private void dfs(int[] nums, boolean[] visited) {
        int n = nums.length;
        if (one.size() == n){
            res.add(new ArrayList<>(one));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                one.push(nums[i]);
                visited[i] = true;
                dfs(nums, visited);
                visited[i] = false;
                one.pop();
            }
        }
    }
}
