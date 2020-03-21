package leet;

import java.util.*;

public class Solution133 {

    public static void main(String[] args) {

    }

    public static GraphicNode cloneGraph(GraphicNode node) {
        return BFS(node);
    }

    /**
     * 使用 HashMap 存储所有访问过的节点和克隆节点。HashMap 的 key 存储原始图的节点，value 存储克隆图中的对应节点。visited 用于防止陷入死循环，和获得克隆图的节点。
     * 将第一个节点添加到队列。克隆第一个节点添加到名为 visited 的 HashMap 中。
     * BFS 遍历:
     *  从队列首部取出一个节点。
     *  遍历该节点的所有邻接点。
     *  如果某个邻接点已被访问，则该邻接点一定在 visited 中，那么从 visited 获得该邻接点。
     *  否则，创建一个新的节点存储在 visited 中。
     *  将克隆的邻接点添加到克隆图对应节点的邻接表中。
     * @param node
     * @return
     */
    public static GraphicNode BFS(GraphicNode node) {
        if (node == null) return null;

        Queue<GraphicNode> queue = new ArrayDeque<>();
        HashMap<GraphicNode, GraphicNode> visited = new HashMap<>();
        queue.add(node);
        visited.put(node, new GraphicNode(node.val, new ArrayList<>()));

        while (!queue.isEmpty()) {
            GraphicNode temp = queue.remove();

            for (GraphicNode neighborNode : temp.neighbors) {
                if (!visited.containsKey(neighborNode)) {
                    queue.add(neighborNode);
                    visited.put(neighborNode, new GraphicNode(neighborNode.val, new ArrayList<>()));
                }
                visited.get(temp).neighbors.add(visited.get(neighborNode));
            }
        }
        return visited.get(node);
    }
}

class GraphicNode {
    public int val;
    public List<GraphicNode> neighbors;

    public GraphicNode() {
        val = 0;
        neighbors = new ArrayList<GraphicNode>();
    }

    public GraphicNode(int _val) {
        val = _val;
        neighbors = new ArrayList<GraphicNode>();
    }

    public GraphicNode(int _val, ArrayList<GraphicNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
