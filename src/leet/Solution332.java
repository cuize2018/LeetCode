package leet;

import java.util.*;

public class Solution332 {
    public static void main(String[] args) {
//        String[][] t = {{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
        String[][] t = {{"EZE", "AXA"}, {"TIA", "ANU"}, {"ANU", "JFK"}, {"JFK", "ANU"}, {"ANU", "EZE"}, {"TIA", "ANU"}, {"AXA", "TIA"}, {"TIA", "JFK"}, {"ANU", "TIA"}, {"JFK", "TIA"}};
        List<List<String>> tickets = new ArrayList<>();
        for (String[] strings : t) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList(strings));
            tickets.add(list);
        }

        Solution332 solution332 = new Solution332();
        List<String> res = solution332.findItinerary(tickets);
        System.out.println(res);
    }
    //一个连通有向图 G 有欧拉路径，指存在一个顶点，从它出发，沿着有向边的方向，可以不重复地遍历图中所有的边。
    public List<String> findItinerary(List<List<String>> tickets) { ;
        List<String> res = new LinkedList<>();
        if (tickets == null || tickets.size() == 0)
            return res;
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> pair : tickets) {
            // 因为涉及删除操作，我们用链表
            PriorityQueue<String> nbr = graph.computeIfAbsent(pair.get(0), k -> new PriorityQueue<>());
            nbr.add(pair.get(1));
        }
        dfs(graph, "JFK", res);
        return res;
    }
    // DFS方式遍历图，当走到不能走为止，再将节点加入到答案
    private void dfs(Map<String, PriorityQueue<String>> graph, String start, List<String> res) {
        PriorityQueue<String> neighbors = graph.get(start);
        while (neighbors != null && !neighbors.isEmpty()){
            String dest = neighbors.poll();
            dfs(graph, dest, res);
        }
        res.add(0, start);// 逆序插入第一个走不通的节点
    }
}
