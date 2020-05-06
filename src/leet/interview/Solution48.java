package leet.interview;

import java.util.HashMap;
import java.util.Map;

public class Solution48 {
    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        if (s.length() == 1) return 1;

        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 1;
        map.put(chars[left], 1);
        int res = 1;

        while (right < s.length()) {
            map.put(chars[right], map.getOrDefault(chars[right], 0) + 1);

            while (left < right && map.get(chars[right]) > 1) {
                if (map.get(chars[left]) == 1) {
                    map.remove(chars[left]);
                } else {
                    map.put(chars[left], map.get(chars[left]) - 1);
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    public static int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0) return 0;
        if (s.length() == 1) return 1;

        char[] chars = s.toCharArray();
        int[] map = new int[256];
        int left = 0;
        int right = 1;
        map[chars[left]] = 1;
        int res = 1;

        while (right < s.length()) {
            map[chars[right]]++;
            while (left < right && map[chars[right]] > 1) {
                map[chars[left]]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
