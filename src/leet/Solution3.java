package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 1;
        int res = 1;

        map.put(s.charAt(left), map.getOrDefault(s.charAt(left), 0) + 1);
        while (right < s.length()) {
            if (map.containsKey(s.charAt(right))) {
                map.put(s.charAt(right), map.get(s.charAt(right)) + 1);

                while (left <= right && map.get(s.charAt(right)) != 1) {
                    if (map.get(s.charAt(left)) == 1) {
                        map.remove(s.charAt(left));
                    } else {
                        map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                    }
                    left++;
                }
            } else {
                map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    //滑动窗口
    public static int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int[] map = new int[128];

        int left = 0;
        int right = 1;
        int res = 1;

        map[chars[left]]++;
        while (right < s.length()) {
            map[chars[right]]++;
            if (map[chars[right]] > 1) {
                while (left <= right && map[chars[right]] > 1) {
                    map[chars[left]]--;
                    left++;
                }
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

}
