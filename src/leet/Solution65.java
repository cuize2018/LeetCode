package leet;

public class Solution65 {
    public static void main(String[] args) {
        int x = 4;
        System.out.println(mySqrt(x));
    }

    public static int mySqrt(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;

        long left = 0;
        long right = x / 2;

        while (left < right) {
            long middle = (left + right + 1) >>> 1;//取右边界
            if (x < middle * middle) {
                right = middle - 1;
            } else {
                left = middle;
            }
        }
        return (int) left;
    }

    public static int mySqrt2(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;

        long left = 0;
        long right = x;

        while (left <= right) {
            long middle = (left + right) >>> 1;//取右边界

            if (x < middle * middle) {
                right = middle - 1;
            } else if (x > middle * middle) {
                left = middle + 1;
            } else {
                return (int) middle;
            }
        }
        return (int) Math.min(left, right);
    }
}
