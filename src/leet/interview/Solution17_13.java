package leet.interview;

public class Solution17_13 {
    public static void main(String[] args) {
        String[] dictionary = {"looked", "just", "like", "her", "brother"};
        String sentence = "jesslookedjustliketimherbrother";

        Solution17_13 solution17_13 = new Solution17_13();
        int respace = solution17_13.respace(dictionary, sentence);
        System.out.println(respace);
    }

    public int respace(String[] dictionary, String sentence) {
        int n = sentence.length();
        if (sentence.length() == 0) return 0;
        if (dictionary.length == 0) return n;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (String word : dictionary) {
                int wLen = word.length();
                if (i - wLen >= 0 && sentence.substring(i - wLen, i).equals(word)) {
                    dp[i] = Math.min(dp[i], dp[i - wLen]);
                }
            }
        }
        return dp[n];
    }
}
