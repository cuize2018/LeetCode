package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution1106 {
    public static void main(String[] args) {
        String[] words = {"hello","world","leetcode"};
        String chars = "welldonehoneyr";
        System.out.println(countCharacters(words, chars));
    }

    public static int countCharacters(String[] words, String chars) {
        if (chars.length() == 0)return 0;

        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int out = 0;
        Map<Character, Integer> tempMap = new HashMap<>();
        for (String word : words) {
            if (word.length() > chars.length()) {
                continue;
            }
            boolean flag = true;
            for (char c : word.toCharArray()) {
                tempMap.put(c, tempMap.getOrDefault(c, 0) + 1);
                if (!map.containsKey(c) || tempMap.get(c) > map.get(c)){
                    flag = false;
                    break;
                }
            }
            if (flag){
                out += word.length();
            }
            tempMap.clear();
        }
        return out;
    }
}
