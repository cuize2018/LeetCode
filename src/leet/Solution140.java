package leet;

import java.util.*;

public class Solution140 {
    Map<Integer, List<String>> map = new HashMap<>();

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

        Solution140 solution140 = new Solution140();
        System.out.println(solution140.wordBreak(s, wordDict));
    }

    /**
     * 记忆化回溯
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (!wordBreak139(s, wordDict)) return new ArrayList<>();
        return dfs(s, new HashSet<>(wordDict), 0);
    }

    private List<String> dfs(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }

        List<String> res = new ArrayList<>();
        if (start == s.length()) {
            res.add("");
            return res;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (wordDict.contains(word)) {
                List<String> temp = dfs(s, wordDict, end);
                for (String str : temp) {
                    if (str.equals("")) {
                        res.add(word);
                        break;
                    } else res.add(word + " " + str);
                }
            }
        }
        map.put(start, res);
        return res;
    }

    public boolean wordBreak139(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];

        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
