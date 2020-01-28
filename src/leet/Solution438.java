package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution438 {
    public static void main(String[] args) {
        String s = "abab";
        String p = "ab";
        System.out.println(findAnagrams(s,p));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> out = new ArrayList<>();
        Map<Character, Integer> key_map = new HashMap<>();
        for (char c : p.toCharArray()){
            if (key_map.containsKey(c)){
                key_map.put(c, key_map.get(c)+1);
            }
            else {
                key_map.put(c, 1);
            }
        }

        int window = p.length();
        Map<Character, Integer> tmp_map = new HashMap<>();

        for (int i = 0; i <= s.length()-window;i++){
            String tmp = s.substring(i, i+window);
            if (i == 0) {
                for (char c : tmp.toCharArray()) {
                    if (tmp_map.containsKey(c)) {
                        tmp_map.put(c, tmp_map.get(c) + 1);
                    } else {
                        tmp_map.put(c, 1);
                    }
                }
            }
            else {
                char past = s.charAt(i-1);
                char latest = s.charAt(i+window-1);


                tmp_map.put(past, tmp_map.get(past)-1);
                if (tmp_map.get(past) == 0){
                    tmp_map.remove(past);
                }

                if (tmp_map.containsKey(latest)){
                    tmp_map.put(latest, tmp_map.get(latest)+1);
                }
                else {
                    tmp_map.put(latest,1);
                }
            }

            if (tmp_map.equals(key_map)){
                out.add(i);
            }
        }

        return out;
    }
}
