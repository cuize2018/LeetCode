package leet.interview;

public class Solution44 {
    public static void main(String[] args) {

    }

    public static int findNthDigit(int n) {
        if (n < 10) return n;
        int i = 1;
        while (n > i * 9 * Math.pow(10, i - 1)) {
            n -= i * 9 * Math.pow(10, i - 1);
            i++;
        }
        int num = (int) ((n - 1) / i + Math.pow(10, i - 1));

        String v = String.valueOf(num);
        if (n % i == 0) return v.charAt(v.length() - 1) - '0';
        return v.charAt(n % i - 1) - '0';
    }
}
