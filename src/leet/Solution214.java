package leet;

public class Solution214 {
    public static void main(String[] args) {
        String s = "abcd";
        System.out.println(new Solution214().shortestPalindrome3(s));
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
     * 有可能此时我们并没有找到最长回文串，但是我们可以肯定最长回文串一定在 0 到 i 之间，所以我们只需要递归的从s[0, i) 中继续寻找最长回文串即可。
     * exp :
     * [a b a b b c e f b b a b a], 最后 i = 'e', j = -1， 此时[0,i)并不是回文串
     *
     * 若字符串全部为回文，则i会自增n次。若结尾有其他字符，i 会自增回文子串的长度。
     * 于是，即使[0,i) 并不总是紧界，但它总包含从开头开始的最长回文子串。
     *
     * @param s
     * @return
     */
    public String shortestPalindrome2(String s) {
        int i = 0;
        int j = s.length() - 1;
        char[] chars = s.toCharArray();
        while (j >= 0) {
            if (chars[i] == chars[j]) i++;
            j--;
        }
        //此时代表整个字符串是回文串
        if (i == s.length()) return s;

        String substring = s.substring(i);
        StringBuilder stringBuilder = new StringBuilder(substring).reverse();
        stringBuilder.append(shortestPalindrome2(s.substring(0, i))).append(substring);

        return stringBuilder.toString();
    }

    public String shortestPalindrome3(String s) {
        int i = s.length() - 1;
        while (i > 0 && !isPalind(s, i)) {
            i--;
        }
        return new StringBuilder(s.substring(i + 1)).reverse().toString() + s;
    }

    private boolean isPalind(String s, int j) {
        int i = 0;
        char[] chars = s.toCharArray();
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
