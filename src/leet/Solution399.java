package leet;

import java.util.*;

public class Solution399 {
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(new ArrayList<>(Arrays.asList("a","b")));
        equations.add(new ArrayList<>(Arrays.asList("b","c")));

        double[] values = new double[]{2.0,3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(new ArrayList<>(Arrays.asList("a","c")));
//        queries.add(new ArrayList<>(Arrays.asList("b","b")));
        System.out.println(Arrays.toString(calcEquation(equations,values,queries)));
    }

    /**
     * BFS方法
     * 构造一个双向图，比如a/b=2.0，那么a->b权重2.0，b->a权重0.5，要查的时候就用BFS遍历，每到一个新节点，就乘以权重，找到目标节点时返回当前值即可。
     * 首先，我们分析一下要怎么判断。没有出现过的字母直接返回-1.0，然后一样的字母直接返回1.0。这时就可以计算两点距离了。
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] out = new double[queries.size()];
        Map<String, ArrayList<Pair2>> map = new HashMap<>();
        //构造双向邻接表
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            double weight = values[i];

            ArrayList<Pair2> neighbor = map.getOrDefault(equation.get(0), new ArrayList<>());
            neighbor.add(new Pair2(equation.get(1), weight));
            map.put(equation.get(0), neighbor);

            ArrayList<Pair2> neighbor2 = map.getOrDefault(equation.get(1), new ArrayList<>());
            neighbor2.add(new Pair2(equation.get(0), 1.0/weight));
            map.put(equation.get(1), neighbor2);
        }

        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String a = query.get(0);
            String b = query.get(1);

            double dis = getDist(map, a,b);
            out[i] = dis;
        }
        return out;
    }

    private static double getDist(Map<String, ArrayList<Pair2>> map, String a, String b) {
        //没出现过直接返回-1.0
        if (!map.containsKey(a) || !map.containsKey(b)){
            return -1.0;
        }
        //相等返回1
        if (a.equals(b))return 1.0;

        //BFS遍历
        Queue<Pair2> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(new Pair2(a, 1));
        visited.add(a);

        double res = -1.0;
        while (!queue.isEmpty()){
            Pair2 node = queue.remove();

            if (node.getX().equals(b)){
                res = node.getY();
                break;
            }

            for (Pair2 neighbor : map.get(node.getX())) {
                if (!visited.contains(neighbor.getX())){
                    visited.add(neighbor.getX());
                    //新节点的值乘上权重
                    queue.add(new Pair2(neighbor.getX(), node.getY()*neighbor.getY()));
                }
            }
        }
        return res;
    }
}

class Pair2{
    private String x;
    private Double y;

    public Pair2(String x, double y){
        this.x = x;
        this.y = y;
    }


    public int hashCode(){
        return x.hashCode() + y.hashCode();
    }

    public boolean equals(Object object){
        if (object instanceof Pair2) {
            return x.equals(((Pair2) object).getX()) && y.doubleValue()==((Pair2) object).getY().doubleValue();
        }
        return false;
    }

    public String getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}