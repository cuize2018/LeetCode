package leet;

public class Solution467 {
    public static void main(String[] args) {
        String a = "zab";
        String b = "cac";
        String c = "zaba";
        String f = "abaab";
        String g = "abcdefghijklmnopqrstuvwxyzab";
        String d = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";

        System.out.println(findSubstringInWraproundString(d));
    }

    /**
     * 1，记录以每个字符结尾字串的最长长度
     * 2，然后最终累加即可得结果
     * @param p
     * @return
     */
    public static int findSubstringInWraproundString(String p) {
        int length = p.length();
        if (length == 0)return 0;

        int[] dp = new int[26];
        int k = 0;

        for (int i = 0;i<length;i++){
            if (i > 0 && isContinous(p.charAt(i-1), p.charAt(i))){
                k++;
            }
            else {
                k = 1;
            }

            int curr_idx = p.charAt(i)-'a';
            dp[curr_idx] = Math.max(dp[curr_idx], k);
        }
        int sum = 0;
        for (int len : dp)sum+=len;

        return sum;
    }

    public static boolean isContinous(char prev, char curr){
        if (prev == 'z')return curr=='a';
        else return (curr-prev) == 1;
    }
}
