package leet;

import java.util.*;

public class Solution39 {
    public static void main(String[] args) {
        int[] a = {3, 4, 7, 8};
        int target = 11;

        Solution39 solution39 = new Solution39();
        System.out.println(solution39.combinationSum(a, target));
    }

    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> stack = new LinkedList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) return new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, int begin) {
        if (target == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if (target - candidates[i] < 0) break;

            stack.addLast(candidates[i]);
            dfs(candidates, target - candidates[i], i);
            stack.removeLast();
        }
    }
}
