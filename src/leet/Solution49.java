package leet;

import java.util.*;

public class Solution49 {
    public static void main(String[] args){
        String[] strs = {};
        System.out.println(groupAnagrams(strs));
    }

    public static  List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> out = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs){
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String a = String.valueOf(arr);

            if (!map.containsKey(a)){
                map.put(a, new ArrayList<String>());
                map.get(a).add(str);
            }
            else {
                map.get(a).add(str);
            }

        }
        Set<String> keys = map.keySet();
        for (String key : keys){
            out.add(map.get(key));
        }
        return out;

    }

}
