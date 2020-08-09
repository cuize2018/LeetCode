package leet;

import java.util.*;

public class Solution93 {
    private List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        String s = "25525511135";
        String s1 = "010010";
        System.out.println(new Solution93().restoreIpAddresses(s1));
    }

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4) return new ArrayList<>();

        List<String> str = new ArrayList<>();
        dfs(str, s);
        return res;
    }

    private void dfs(List<String> str, String s) {
        if (str.size() == 4 && s.length() > 0) return;

        if (s.length() == 0 && str.size() == 4) {
            res.add(String.join(".", str));
            return;
        }

        for (int i = 1; i <= Math.min(s.length(), 3); i++) {
            String curr = s.substring(0, i);
            int v = Integer.parseInt(curr);

            if (v != 0 && curr.charAt(0) == '0') return;
            if (v == 0 && curr.length() > 1) return;

            if (v <= 255 && v >= 0) {
                str.add(curr);
                dfs(str, s.substring(i));
                str.remove(str.size() - 1);
            }
        }
    }
}
