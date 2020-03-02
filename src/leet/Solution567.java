package leet;

import java.util.Arrays;

public class Solution567 {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "a";
        System.out.println(checkInclusion2(s1, s2));
    }

    public static boolean checkInclusion2(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int window = s1.length();
        char[] s1_c = s1.toCharArray();
        Arrays.sort(s1_c);
        for (int i = 0; i <= s2.length() - window; i++) {
            String tmp = s2.substring(i, i + window);
            char[] tmp_c = tmp.toCharArray();
            Arrays.sort(tmp_c);

            if (Arrays.equals(s1_c, tmp_c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 滑动窗口法，使用charsS1数组储存s1里面的字符数目;使用charsS2数组储存s2滑动窗口字串里面的字符数目,若两数组相等则为true;
     * 更新charsS2数组时仅需使得i-1处字符数目减1，i+window-1处字符加1即可
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int window = s1.length();
        int[] charsS1 = new int[26];
        int[] charsS2 = new int[26];

        for (char c : s1.toCharArray()) {
            charsS1[c - 'a']++;
        }

        String tmp = s2.substring(0, window);
        for (char c : tmp.toCharArray()) {
            charsS2[c - 'a']++;
        }
        if (Arrays.equals(charsS1, charsS2)) {
            return true;
        }

        for (int i = 1; i <= s2.length() - window; i++) {
            charsS2[s2.charAt(i - 1) - 'a']--;
            charsS2[s2.charAt(i + window - 1) - 'a']++;

            if (Arrays.equals(charsS1, charsS2)) {
                return true;
            }
        }
        return false;
    }
}
