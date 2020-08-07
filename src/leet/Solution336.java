package leet;

import java.util.*;

public class Solution336 {
    public static void main(String[] args) {
        String[] w = {"abcd","dcba","lls","s","sssll"};
        System.out.println(palindromePairs(w));
    }

    /**
     * 假设存在两个字符串 s_1,s_2s, s_1+s_2是一个回文串，记这两个字符串的长度分别为 len_1 和len_2，我们分三种情况进行讨论：
     *
     * 1. len1 = len2, 这种情况下s1是s2的翻转
     * 2. len1 > len2, 这种情况下将s1拆分成两部分，t1和t2， 其中t1是s2的翻转，t2是回文串
     * 3. len1 < len2, 这种情况下将s2拆分成两部分，t1和t2， 其中t2是s1的翻转，t1是回文串
     * 这样，对于每一个字符串，我们令其为 s_1和 s_2中较长的那一个，然后找到可能和它构成回文串的字符串即可。
     *
     * 具体地说，我们枚举每一个字符串 k，令其为 s_1和 s_2中较长的那一个，那么 k 可以被拆分为两部分，t_1和 t_2。
     * 当 t_1是回文串时，符合情况 3，我们只需要查询给定的字符串序列中是否包含 t_2的翻转。
     * 当 t_2是回文串时，符合情况 2，我们只需要查询给定的字符串序列中是否包含 t_1的翻转。
     * @param words
     * @return
     */
    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> revMap = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            revMap.put(new StringBuilder(words[i]).reverse().toString(), i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = word.length();
            if (len == 0) continue;

            for (int j = 0; j <= len; j++) {
                if (isValid(word, j, len - 1)) {
                    int idx = revMap.getOrDefault(word.substring(0, j), -1);
                    if (idx != -1 && idx != i) {
                        res.add(Arrays.asList(i, idx));
                    }
                }

                if (j != 0 && isValid(word, 0, j - 1)) {
                    int idx = revMap.getOrDefault(word.substring(j, len), -1);
                    if (idx != -1 && idx != i) {
                        res.add(Arrays.asList(idx, i));
                    }
                }
            }
        }
        return res;
    }

    private static boolean isValid(String word, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (word.charAt(left + i) != word.charAt(right - i)) {
                return false;
            }
        }
        return true;
    }

}
