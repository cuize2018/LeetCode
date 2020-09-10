package leet;

import java.util.*;

public class Solution40 {
    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(new Solution40().combinationSum22(candidates, target));
    }

    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> stack = new LinkedList<>();

    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        boolean[] visited = new boolean[candidates.length];
        Arrays.sort(candidates);
        dfs(candidates, target, visited, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, boolean[] visited, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (!visited[i]) {
                if (target - candidates[i] < 0) break;
                if (i > start && candidates[i] == candidates[i - 1]) continue;

                stack.addLast(candidates[i]);
                visited[i] = true;

                dfs(candidates, target - candidates[i], visited, i + 1);

                visited[i] = false;
                stack.removeLast();
            }
        }
    }
}
