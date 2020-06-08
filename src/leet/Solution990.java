package leet;

import java.util.*;

public class Solution990 {
    public static void main(String[] args) {
        String[] strings = {"a==b", "e==c", "b==c", "a!=e"};
        System.out.println(equationsPossible2(strings));
    }

    public static boolean equationsPossible(String[] equations) {
        if (equations.length == 1) {
            if (equations[0].charAt(1) == '=') {
                return equations[0].charAt(0) == equations[0].charAt(3);
            } else {
                return equations[0].charAt(0) != equations[0].charAt(3);
            }
        }
        Map<Character, Set<Character>> equalMap = new HashMap<>();
        Set<Integer> notEqualIdxSet = new HashSet<>();

        for (int i = 0; i < equations.length; i++) {
            String equation = equations[i];
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            if (equation.charAt(1) == '=') {
                Set<Character> set = equalMap.getOrDefault(a, new HashSet<>());
                set.addAll(equalMap.getOrDefault(b, new HashSet<>()));
                set.add(b);
                equalMap.put(a, set);

                Set<Character> set2 = equalMap.getOrDefault(b, new HashSet<>());
                set2.addAll(equalMap.getOrDefault(a, new HashSet<>()));
                set2.add(a);
                equalMap.put(b, set2);

                for (Character c : set) {
                    equalMap.get(c).addAll(set);
                }
                for (Character c : set2) {
                    equalMap.get(c).addAll(set2);
                }
            } else {
                if (a == b) {
                    return false;
                }
                notEqualIdxSet.add(i);
            }
        }

        for (int i : notEqualIdxSet) {
            String equation = equations[i];
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            if (equalMap.containsKey(a) && equalMap.get(a).contains(b)) {
                return false;
            }
            if (equalMap.containsKey(b) && equalMap.get(b).contains(a)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 构建图，使用BFS判断是否可达
     *
     * @param equations
     * @return
     */
    public static boolean equationsPossible2(String[] equations) {
        Map<Character, Set<Character>> map = new HashMap<>();
        Set<Integer> setIdx = new HashSet<>();
        for (int i = 0; i < equations.length; i++) {
            String equation = equations[i];
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            if (equation.charAt(1) == '=') {
                Set<Character> neighbors = map.getOrDefault(a, new HashSet<>());
                neighbors.add(b);
                map.put(a, neighbors);

                Set<Character> neighbors2 = map.getOrDefault(b, new HashSet<>());
                neighbors2.add(a);
                map.put(b, neighbors2);
            } else {
                if (a == b) {
                    return false;
                }
                setIdx.add(i);
            }
        }

        for (int i : setIdx) {
            String equation = equations[i];
            if (reachAble(equation.charAt(0), equation.charAt(3), map)) {
                return false;
            }
        }
        return true;
    }

    /**
     * BFS判断是否可达
     *
     * @param begin
     * @param end
     * @param map
     * @return
     */
    private static boolean reachAble(char begin, char end, Map<Character, Set<Character>> map) {
        if (!map.containsKey(begin) || !map.containsKey(end)) {
            return false;
        }
        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        queue.add(begin);
        visited.add(begin);

        while (!queue.isEmpty()) {
            Character c = queue.remove();
            if (c == end) {
                return true;
            }
            for (Character neighbor : map.get(c)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }
}
