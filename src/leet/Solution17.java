package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution17 {
    StringBuilder stringBuilder = new StringBuilder();
    List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        String digits = "7";
        System.out.println(new Solution17().letterCombinations2(digits));
    }

    public List<String> letterCombinations2(String digits) {
        if (digits.length() == 0) return new ArrayList<>();

        Map<Character, char[]> map = new HashMap<>();
        char c = 'a';
        for (int i = 2; i <= 9; i++) {
            int m = (i == 7 || i == 9) ? 4 : 3;
            char[] t = new char[m];
            for (int j = 0; j < m; j++) {
                t[j] = c;
                c++;
            }
            map.put((char) ('0' + i), t);
        }
        char[] chars = digits.toCharArray();
        dfs(chars, map, 0);
        return res;
    }

    private void dfs(char[] chars, Map<Character, char[]> map, int idx) {
        if (stringBuilder.length() == chars.length) {
            res.add(stringBuilder.toString());
            return;
        }

        for (int i = idx; i < chars.length; i++) {
            char[] neighbors = map.get(chars[i]);
            for (char neighbor : neighbors) {
                stringBuilder.append(neighbor);
                dfs(chars, map, i + 1);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }
    }

}
