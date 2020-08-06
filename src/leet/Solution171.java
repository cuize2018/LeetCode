package leet;

public class Solution171 {
    public static void main(String[] args) {

    }

    public static int titleToNumber(String s) {
        int res = 0;
        int k = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            res += ((c - 'A') + 1) * Math.pow(26, k++);
        }
        return res;
    }
}
