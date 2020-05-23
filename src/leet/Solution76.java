package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution76 {
    public static void main(String[] args) {
        String S = "cabwefgewcwaefgcf";
        String T = "cae";
        System.out.println(minWindow(S, T));
    }

    /**
     * 1、我们在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0，把索引闭区间 [left, right] 称为一个「窗口」。
     * 2、我们先不断地增加 right 指针扩大窗口 [left, right]，直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
     * 3、此时，我们停止增加 right，转而不断增加 left 指针缩小窗口 [left, right]，直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。同时，每次增加 left，我们都要更新一轮结果。
     * 4、重复第 2 和第 3 步，直到 right 到达字符串 S 的尽头。
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        int s_len = s.length();
        if (t.length() == 0) return "";
        if (s_len < t.length()) return "";

        Map<Character, Integer> target = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (Character character : t.toCharArray()) {
            target.put(character, target.getOrDefault(character, 0) + 1);
        }

        int left = 0;
        int right = 0;

        // 记录最短子串的开始位置和长度
        int min = Integer.MAX_VALUE;
        int start = left;

        // 记录 window 中已经有多少字符符合要求了
        int match = 0;

        while (right < s_len) {
            char rightChar = s.charAt(right);
            if (target.containsKey(rightChar)) {
                window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
                if (window.get(rightChar).equals(target.get(rightChar))) {
                    // 字符 rightChar 的出现次数符合要求了
                    match++;
                }
            }
            right++;
            // window 中的字符串已符合 target 的要求了
            while (match == target.size()) {
                //若当前窗口更小，更新
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }
                char leftChar = s.charAt(left);
                if (target.containsKey(leftChar)) {
                    window.put(leftChar, window.get(leftChar) - 1);
                    if (window.get(leftChar) < target.get(leftChar)) {
                        // 字符 leftChar 出现次数不再符合要求
                        match--;
                    }
                }
                left++;
            }
        }

        if (min == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(start, start + min);
    }

    public static String minWindow2(String s, String t) {
        int s_len = s.length();
        if (t.length() == 0) return "";
        if (s_len < t.length()) return "";

        Map<Character, Integer> target = new HashMap<>();
        Map<Character, Integer> origin = new HashMap<>();
        for (Character character : t.toCharArray()) {
            target.put(character, target.getOrDefault(character, 0) + 1);
        }

        int left = 0;
        int right = 0;

        int min = Integer.MAX_VALUE;
        int start = left;
        boolean isOk = false;

        while (right < s_len) {
            origin.put(s.charAt(right), origin.getOrDefault(s.charAt(right), 0) + 1);

            if (origin.keySet().containsAll(target.keySet())) {
                isOk = true;
                for (Character character : target.keySet()) {
                    if (origin.get(character) < target.get(character)) {
                        isOk = false;
                        break;
                    }
                }
            }
            while (isOk) {
                if (right - left < min) {
                    min = right - left + 1;
                    start = left;
                }

                if (origin.get(s.charAt(left)) > 1) {
                    origin.put(s.charAt(left), origin.get(s.charAt(left)) - 1);
                } else {
                    origin.remove(s.charAt(left));
                }
                left++;
                if (origin.keySet().containsAll(target.keySet())) {
                    for (Character character : target.keySet()) {
                        if (origin.get(character) < target.get(character)) {
                            isOk = false;
                            break;
                        }
                    }
                } else {
                    isOk = false;
                }
            }
            right++;
            isOk = false;
        }
        if (min == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(start, start + min);
    }

    public static String minWindow3(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> target = new HashMap<>();
        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        int match = 0;
        int min = Integer.MAX_VALUE;
        int start = 0;
        while (right < s.length()) {
            if (target.containsKey(chars[right])) {
                map.put(chars[right], map.getOrDefault(chars[right], 0) + 1);
                if (map.get(chars[right]).equals(target.get(chars[right]))) {
                    match++;
                }
            }
            right++;

            while (match == target.keySet().size()) {
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }

                if (target.containsKey(chars[left])) {
                    map.put(chars[left], map.get(chars[left]) - 1);
                    if (map.get(chars[left]) < target.get(chars[left])) {
                        match--;
                    }
                }
                left++;
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
    }
}
