package leet;

import java.util.*;

public class Solution77 {
    private List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> tmp = new LinkedList<>();
    boolean[] visited;

    public static void main(String[] args) {
        Solution77 s = new Solution77();
        List<List<Integer>> l = s.combine(4, 3);
        System.out.println(l);
    }

    public List<List<Integer>> combine(int n, int k) {
        if (n == 0) return new ArrayList<>();
        visited = new boolean[n + 1];
        dfs(n, k, 0);
        return res;
    }

    private void dfs(int n, int k, int last) {
        if (k == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = last + 1; i <= n; i++) {
            if (!visited[i]) {
                tmp.addLast(i);
                visited[i] = true;

                dfs(n, k - 1, i);

                visited[i] = false;
                tmp.removeLast();
            }
        }
    }
}
