package leet.interview;

public class Solution17 {
    public static void main(String[] args) {
        int n = 3;
        printNumbers2(n);
    }

    public static int[] printNumbers(int n) {
        int len = (int) (Math.pow(10, n) - 1);
        int[] res = new int[len];

        for (int i = 1; i <= len; i++) {
            res[i - 1] = i;
        }
        return res;
    }

    /**
     * 大数解法
     *
     * @param n
     */
    public static void printNumbers2(int n) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            str.append('0');
        }

        while (increase(str)) {
            int idx = 0;
            while (idx < str.length() && str.charAt(idx) == '0') idx++;
            System.out.print(str.toString().substring(idx) + " , ");
        }
    }

    private static boolean increase(StringBuilder str) {
        boolean add = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            int val = c - '0';

            if (i == str.length() - 1) val++;
            if (add) val++;

            if (val < 10) {
                add = false;
            } else {
                val -= 10;
                add = true;
            }
            str.setCharAt(i, (char) ('0' + val));
            if (add && i == 0) return false;
            if (!add) return true;
        }
        return true;
    }
}
