package leet;

import java.util.*;

public class Solution785 {
    public static void main(String[] args) {

    }

    /**
     * 注意：题目中给定的无向图不一定保证连通，因此我们需要进行多次遍历，直到每一个节点都被染色，或确定答案为 False 为止。
     * 每次遍历开始时，我们任选一个未被染色的节点，将所有与该节点直接或间接相连的节点进行染色。
     *
     * @param graph
     * @return
     */
    public static boolean isBipartite(int[][] graph) {
        if (graph.length == 0) return false;

        int[] color = new int[graph.length];
        int red = 1;
        int green = 2;

        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                color[i] = red;

                while (!queue.isEmpty()) {
                    int node = queue.remove();
                    int nextColor = color[node] == red ? green : red;

                    int[] neighbors = graph[node];
                    for (int neighbor : neighbors) {
                        if (color[neighbor] == 0) {
                            color[neighbor] = nextColor;
                            queue.add(neighbor);
                        } else if (color[neighbor] != nextColor) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
