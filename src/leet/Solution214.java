package leet;

public class Solution214 {
    public static void main(String[] args) {
        String s = "1234321789";
        System.out.println(shortestPalindrome2(s));
    }

    public static String shortestPalindrome(String s) {
        if (s.length() == 1) return s;
        StringBuilder rev = new StringBuilder(s).reverse();
        int n = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(0, n - i).equals(rev.substring(i))) {
                return rev.substring(0, i) + s;
            }
        }
        return "";
    }

    /**
     * 若字符串全部为回文，则i会自增n次。若结尾有其他字符，i 会自增回文子串的长度。
     * 于是，即使[0,i) 并不总是紧界，但它总包含从开头开始的最长回文子串。
     *
     *
     * @param s
     * @return
     */
    public static String shortestPalindrome2(String s) {
        if (s.length() == 1) return s;
        int n = s.length();

        int i = 0;
        int j = n - 1;

        while (j >= 0) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
            }
            j--;
        }
        //此时代表整个字符串是回文串
        if (i == n) return s;
        String substring = s.substring(i);
        String rev = new StringBuilder(substring).reverse().toString();
        return rev + shortestPalindrome2(s.substring(0, i)) + substring;
    }
}
