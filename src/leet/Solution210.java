package leet;

import java.util.*;

public class Solution210 {
    public static void main(String[] args) {
        int numCourses = 4;
        int[][] b = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(findOrder(numCourses, b)));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int outNode = prerequisite[1];
            int inNode = prerequisite[0];

            inDegree[inNode]++;
            graph.get(outNode).add(inNode);
        }

        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int[] out = new int[numCourses];
        int i = 0;
        while (!queue.isEmpty()) {
            int temp = queue.remove();
            numCourses--;

            out[i] = temp;
            i++;

            for (int neighbor : graph.get(temp)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (numCourses == 0) {
            return out;
        }
        return new int[0];
    }

    public static int[] findOrder2(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] degree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        for (int[] prerequisite : prerequisites) {
            int outNode = prerequisite[1];
            int inNode = prerequisite[0];

            List<Integer> neighbor = graph.getOrDefault(outNode, new ArrayList<>());
            neighbor.add(inNode);
            graph.put(outNode, neighbor);

            degree[inNode]++;
        }

        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) queue.add(i);
        }

        int[] res = new int[numCourses];
        int i = 0;

        while (!queue.isEmpty()) {
            int node = queue.remove();
            numCourses--;
            res[i] = node;
            i++;

            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                degree[neighbor]--;
                if (degree[neighbor] == 0) queue.add(neighbor);
            }
        }
        if (numCourses == 0) return res;
        return new int[0];
    }
}
