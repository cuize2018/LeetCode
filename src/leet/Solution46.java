package leet;

import java.util.*;

public class Solution46 {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> one = new LinkedList<>();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution46 solution46 = new Solution46();

        System.out.println(solution46.permute(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0)return new ArrayList<>();
        int[] visited = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            one.add(nums[i]);
            visited[i] = 1;
            dfs2(nums, visited);
            visited[i] = 0;
            one.removeLast();
        }
        return res;
    }

    private void dfs2(int[] nums, int[] visited) {
        if (one.size() == nums.length){
            res.add(new ArrayList<>(one));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 0) {
                one.add(nums[i]);
                visited[i] = 1;
                dfs2(nums, visited);
                visited[i] = 0;
                one.removeLast();
            }
        }
    }
}
