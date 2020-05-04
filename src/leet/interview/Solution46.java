package leet.interview;

public class Solution46 {
    int res = 0;
    StringBuilder one = new StringBuilder();
    String[] dict = new String[26];

    public static void main(String[] args) {
        int num = 506;
        Solution46 solution46 = new Solution46();
        int ans = solution46.translateNum(num);
        System.out.println(ans);
    }

    public int translateNum(int num) {
        char c = 'a';
        for (int i = 0; i < 26; i++) {
            dict[i] = String.valueOf(c);
            c++;
        }

        String str = String.valueOf(num);
        dfs(str);
        return res;
    }

    private void dfs(String num) {
        if (num.length() == 0) {
            res++;
            return;
        }

        for (int i = 1; i <= num.length(); i++) {
            String temp = num.substring(0, i);
            if (temp.length() > 2 || Integer.parseInt(temp) > 25 || (temp.length() == 2 && temp.charAt(0) == '0')) {
                break;
            }
            one.append(dict[Integer.parseInt(temp)]);
            dfs(num.substring(i));
            one.deleteCharAt(one.length() - 1);
        }
    }
}
