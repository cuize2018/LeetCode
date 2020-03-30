package leet;

import java.util.*;

public class Solution310 {
    public static void main(String[] args) {
        int n = 6;
        int[][]edges = {{1, 0}, {1, 2}, {1, 3}};
        int[][]edges2 = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        System.out.println(findMinHeightTrees(n,edges2));
    }

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {

        HashMap<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            List<Integer> neighborsA = adjacencyList.getOrDefault(a, new ArrayList<>());
            neighborsA.add(b);
            adjacencyList.put(a, neighborsA);

            List<Integer> neighborsB = adjacencyList.getOrDefault(b, new ArrayList<>());
            neighborsB.add(a);
            adjacencyList.put(b, neighborsB);
        }

        List<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int len = BFS(i, adjacencyList, min);
            if (len == min){
                list.add(i);
            }
            else if (len < min){
                list.clear();
                min = len;
                list.add(i);
            }
        }
        return list;
    }

    private static int BFS(int root, HashMap<Integer, List<Integer>> adjacencyList, int min) {
        Queue<Pair> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(new Pair(root, 0));
        visited.add(root);

        int max = 0;
        while (!queue.isEmpty()){
            Pair temp = queue.remove();
            int node = temp.getX();
            int len = temp.getY();
            max = Math.max(len, max);
            if (max > min)return max;

            for (Integer neighbor: adjacencyList.getOrDefault(node, new ArrayList<>())){
                if (!visited.contains(neighbor)){
                    visited.add(neighbor);
                    queue.add(new Pair(neighbor, len+1));
                }
            }
        }
        return max;
    }

    //拓扑排序：BFS+入度：每次使用“剔除边缘结点”的策略，这里的边缘结点就是指连接其它结点最少的结点，
    // 用专业的名词来说，就是指向它的结点最少的结点，“入度”最少的结点。
    // 结点最后只会剩下 1 个或者 2 个。
    public static List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }

        //构建邻接表和出度数组
        HashMap<Integer, List<Integer>> adjacencyList = new HashMap<>();
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            degree[a]++;
            degree[b]++;

            List<Integer> neighborsA = adjacencyList.getOrDefault(a, new ArrayList<>());
            neighborsA.add(b);
            adjacencyList.put(a, neighborsA);

            List<Integer> neighborsB = adjacencyList.getOrDefault(b, new ArrayList<>());
            neighborsB.add(a);
            adjacencyList.put(b, neighborsB);
        }

        //入队所有度为1的节点，即叶节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 1){
                queue.add(i);
            }
        }

        while (!queue.isEmpty()){
            res = new ArrayList<>();
            int size = queue.size();

            //剔除最外层节点，把这些节点当作ans
            for (int i = 0; i < size; i++) {
                int node = queue.remove();
                res.add(node);
                degree[node]--;
                for (Integer neighbor: adjacencyList.getOrDefault(node, new ArrayList<>())){
                    degree[neighbor]--;
                    if (degree[neighbor] == 1){
                        queue.add(neighbor);
                    }
                }
            }
        }
        return res;
    }
}
