package leet;

import java.lang.reflect.Array;
import java.util.*;

public class Solution767 {
    public static void main(String[] args) {
        String s = "aaab";
        System.out.println(reorganizeString(s));
    }

    /**
     * 优先选择出现次数最多的字符
     * @param S
     * @return
     */
    public static String reorganizeString(String S) {
        StringBuilder str = new StringBuilder();
        Map<Character, Integer>map = new HashMap<>();
        for (char c : S.toCharArray()){
            if (map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }
            else {
                map.put(c, 1);
            }
        }
        PriorityQueue<Character> heap = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Integer.compare(map.get(o2), map.get(o1));
            }
        });
        char tmp = '#';

        heap.addAll(map.keySet());
        while (!map.isEmpty()){
            if (heap.isEmpty())return "";

            char c = heap.remove();
            if (str.length()==0 || c != str.charAt(str.length()-1)) {
                str.append(c);
                if (map.get(c) > 1){
                    map.put(c, map.get(c)-1);
                    heap.add(c);
                }
                else {
                    map.remove(c);
                }

                if (tmp != '#') {
                    heap.add(tmp);
                    tmp = '#';
                }
            }
            else {
                tmp = c;
            }
        }
        return str.toString();
    }
}
