package leet.interview;

public class Solution20 {
    int i = 0;
    public static void main(String[] args) {
        String s = "0.8";
        Solution20 solution20 = new Solution20();
        System.out.println(solution20.isNumber(s));
    }

    public boolean isNumber(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) return false;

        while (i < chars.length && chars[i] == ' ') i++;

        boolean num = scanInteger(chars);

        if (i < chars.length && chars[i] == '.') {
            i++;
            num = scanUnsignedInteger(chars) || num;
        }
        if (i < chars.length && (chars[i] == 'E' || chars[i] == 'e')) {
            i++;
            num = num && scanInteger(chars);
        }
        while (i < chars.length && chars[i] == ' ') i++;
        return num && i == s.length();

    }

    private boolean scanInteger(char[] chars) {
        if (i < chars.length && chars[i] == '+' || chars[i] == '-') i++;

        return scanUnsignedInteger(chars);
    }

    private boolean scanUnsignedInteger(char[] chars) {
        int before = i;
        while (i < chars.length && chars[i] >= '0' && chars[i] <= '9') {
            i++;
        }
        return i > before;
    }
}
