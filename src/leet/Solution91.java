package leet;

public class Solution91 {
    public static void main(String[] args) {
        String s = "12";
        System.out.println(numDecodings2(s));
    }

    public static int numDecodings(String s) {
        return getAns(s,0);
    }

    public static int getAns(String s , int start){
        if (start == s.length()){
            return 1;
        }

        if (s.charAt(start) == '0'){
            return 0;
        }

        int ans1 = getAns(s,start+1);
        int ans2 = 0;
        if (start < s.length()-1) {
            int ten = (s.charAt(start) - '0') * 10;
            int one = s.charAt(start + 1) - '0';
            if (ten + one <= 26) {
                ans2 = getAns(s, start + 2);
            }
        }
        return ans1 + ans2;
    }

    /**
     * 同样的，递归就是压栈压栈压栈，出栈出栈出栈的过程，我们可以利用动态规划的思想，省略压栈的过程，直接从 bottom 到 top。
     *
     * 用一个 dp 数组， dp [ i ] 代表字符串 s [ i, s.len-1 ]，也就是 s 从 i 开始到结尾的字符串的解码方式。
     *
     * 这样和递归完全一样的递推式。
     *
     * 如果 s [ i ] 和 s [ i + 1 ] 组成的数字小于等于 26，那么
     *
     * dp [ i ] = dp[ i + 1 ] + dp [ i + 2 ]
     * @param s
     * @return
     */
    public static int numDecodings2(String s) {
        int len = s.length();
        int[] dp = new int[s.length()+1];
        dp[len] = 1;
        if (s.charAt(len - 1) != '0') {
            dp[len - 1] = 1;
        }

        for (int i = len-2;i>=0;i--){
            int ans1 = dp[i+1];
            int ans2 = 0;

            String str = s.substring(i, i + 2);
            if (s.charAt(i) == '0') {
                continue;
            }
            int val = Integer.parseInt(str);

            if (val <= 26 && val > 0) {
                ans2 = dp[i + 2];
            }

            dp[i] = ans1 + ans2;
        }
        return dp[0];
    }
}