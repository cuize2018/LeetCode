package leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution139 {
    public static void main(String[] args){
        String s = "applepenapple";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("apple");wordDict.add("pen");

        System.out.println(wordBreak(s,wordDict));
    }

    /**
     * dp[0,len) =  dp[0,1) && wordDict.contains(s[1,len))
     *             || dp[0,2) && wordDict.contains(s[2,len))
     *             || dp[0,3) && wordDict.contains(s[3,len))
     *             ...
     *             || dp[0,len - 1) && wordDict.contains(s[len - 1,len))
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>();
        for (String word: wordDict){
            wordSet.add(word);
        }

        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;

        for (int i = 1;i<=s.length();i++){
            for (int j = 0;j < i;j++) {
                dp[i] = dp[j] && wordSet.contains(s.substring(j,i));
                if (dp[i])break;
            }
        }
        return dp[s.length()];

    }

}
