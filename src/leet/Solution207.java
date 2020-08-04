package leet;

import java.util.*;

public class Solution207 {
    public static void main(String[] args) {

    }

    /**
     * 解题思路：
     * 本题可约化为： 课程安排图是否是 有向无环图(DAG)。即课程间规定了前置条件，但不能构成任何环路，否则课程前置条件将不成立。
     * 思路是通过 拓扑排序 判断此课程安排图是否是 有向无环图(DAG) 。
     * 拓扑排序原理： 对 DAG 的顶点进行排序，使得对每一条有向边 (u, v)，均有 u（在排序记录中）比 v 先出现。亦可理解为对某点 v 而言，只有当 v 的所有源点均出现了，v 才能出现。
     * 通过课程前置条件列表 prerequisites 可以得到课程安排图的 邻接表 adjacency，以降低算法时间复杂度，以下两种方法都会用到邻接表。
     *
     * 方法一：入度表（广度优先遍历）
     * 算法流程：
     *     统计课程安排图中每个节点的入度，生成 入度表 indegrees。
     *     借助一个队列 queue，将所有入度为 0 的节点入队。
     *     当 queue 非空时，依次将队首节点出队，在课程安排图中删除此节点 pre：
     *       并不是真正从邻接表中删除此节点 pre，而是将此节点对应所有邻接节点 cur 的入度 −1，即 indegrees[cur] -= 1。
     *       当入度 -1后邻接节点 cur 的入度为 0，说明 cur 所有的前驱节点已经被 “删除”，此时将 cur 入队。
     *     在每次 pre 出队时，执行 numCourses--；
     *     若整个课程安排图是有向无环图（即可以安排），则所有节点一定都入队并出队过，即完成拓扑排序。换个角度说，若课程安排图中存在环，一定有节点的入度始终不为 0。
     *     因此，拓扑排序出队次数等于课程个数，返回 numCourses == 0 判断课程是否可以成功安排。
     *

     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int inNode = prerequisite[0];
            int outNode = prerequisite[1];

            inDegree[inNode]++;
            graph.get(outNode).add(inNode);
        }

        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int temp = queue.remove();
            numCourses--;
            for (Integer neighbor : graph.get(temp)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return numCourses == 0;
    }


    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            int out = prerequisite[1];
            int in = prerequisite[0];

            List<Integer> neighbors = graph.getOrDefault(out, new ArrayList<>());
            neighbors.add(in);
            graph.put(out, neighbors);
            inDegree[in]++;
        }

        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0){
                queue.add(i);
            }
        }

        while (!queue.isEmpty()){
            numCourses--;
            int node = queue.poll();
            List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
            for (int neighbor : neighbors) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0){
                    queue.add(neighbor);
                }
            }
        }
        return numCourses == 0;
    }
}
