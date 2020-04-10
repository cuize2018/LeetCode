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


    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] out = new double[queries.size()];
        Map<String, ArrayList<Pair2>> map = new HashMap<>();
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
        if (!map.containsKey(a) || !map.containsKey(b)){
            return -1.0;
        }
        if (a.equals(b))return 1.0;

        Queue<Pair2> queue = new ArrayDeque<>();
        Map<String, Integer> visited = new HashMap<>();
        queue.add(new Pair2(a, 1));
        visited.put(a,1);

        double res = -1.0;
        while (!queue.isEmpty()){
            Pair2 node = queue.remove();
            //visited.put(node.getX(), 1);

            if (node.getX().equals(b)){
                res = node.getY();
                break;
            }

            for (Pair2 neighbor : map.get(node.getX())) {
                if (!visited.containsKey(neighbor.getX())){
                    visited.put(neighbor.getX(), 1);
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