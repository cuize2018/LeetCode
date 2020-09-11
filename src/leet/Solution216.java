package leet;

import java.util.*;

public class Solution216 {
    private List<List<Integer>> out = new ArrayList<>();
    private Deque<Integer> one = new LinkedList<>();

    public static void main(String[] args) {
        int k = 1;
        int n = 7;
        Solution216 s = new Solution216();
        System.out.println(s.combinationSum3(k, n));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k == 0) return new ArrayList<>();
        boolean[] visited = new boolean[10];
        dfs(k, n, visited, 1);
        return out;
    }

    private void dfs(int k, int n, boolean[] visited, int start) {
        if (k == 0 && n == 0) {
            out.add(new ArrayList<>(one));
            return;
        }
        //if (k <= 0 || n <= 0) return;

        for (int i = start; i <= 9; i++) {
            if (!visited[i]) {
                if (n - i < 0)break;

                one.addLast(i);
                visited[i] = true;

                dfs(k - 1, n - i, visited, i + 1);

                visited[i] = false;
                one.removeLast();
            }
        }
    }
}
