package leet.interview;

import java.util.*;

public class Solution38 {
    List<String> res = new ArrayList<>();
    Set<String> set = new HashSet<>();
    StringBuilder str = new StringBuilder();
    StringBuilder str2 = new StringBuilder();

    public static void main(String[] args) {
        String s = "aba";
        Solution38 solution38 = new Solution38();
        String[] permutation = solution38.permutation(s);
        System.out.println(Arrays.toString(permutation));
    }


    public String[] permutation(String s) {
        if (s.length() == 0)return new String[0];
        str2 = new StringBuilder(s);

        for (int i = 0; i < s.length(); i++) {
            str.append(s.charAt(i));
            str2.deleteCharAt(i);
            dfs2(str2);
            str2.insert(i, s.charAt(i));
            str.deleteCharAt(str.length()-1);
        }
        String[] ans = new String[res.size()];
        return  res.toArray(ans);
    }

    private void dfs2(StringBuilder s) {
        if (s.length() == 0){
            if (!set.contains(str.toString())){
                res.add(str.toString());
                set.add(str.toString());
            }
            return;
        }

        for (int j = 0; j < s.length(); j++) {
            str.append(s.charAt(j));
            char temp = str2.charAt(j);
            str2.deleteCharAt(j);
            dfs2(str2);
            str2.insert(j, temp);
            str.deleteCharAt(str.length()-1);
        }
    }
}
