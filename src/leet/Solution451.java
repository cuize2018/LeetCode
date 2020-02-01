package leet;

import java.util.*;

public class Solution451 {
    public static void main(String[] args) {
        String s = "raaeaedere";
        System.out.println(frequencySort(s));
    }

    public static String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return map.get(o2).compareTo(map.get(o1));
            }
        });

        for (char c : s.toCharArray()){
            if (!map.containsKey(c)){
                map.put(c, 1);
            }
            else {
                map.put(c, map.get(c) + 1);
            }
        }

        queue.addAll(map.keySet());
        StringBuilder str = new StringBuilder();
        while (!queue.isEmpty()){
            char c = queue.remove();
            for (int i = 0;i<map.get(c);i++){
                str.append(c);
            }
        }
        return str.toString();
    }
}
