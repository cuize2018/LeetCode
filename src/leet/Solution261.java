package leet;

import java.util.LinkedList;
import java.util.Queue;

public class Solution261 {
    public static void main(String[] args) {

    }

    public static boolean validTree(int n, int[][] edges) {
        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            int node = queue.remove();
            visited[node] = true;
            for (int i = 0; i < n; i++) {
                if (graph[node][i] == 1) {
                    if (visited[i]) return false;

                    graph[node][i] = 0;
                    graph[i][node] = 0;
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }
}
