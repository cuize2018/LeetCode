package leet;

public class Solution441 {
    public static void main(String[] args) {
        int n = 1804289383;
        System.out.println(arrangeCoins(n));
    }

    public static int arrangeCoins(int n) {
        if (n == 1) return 1;
        if (n == 0) return 0;

        long low = 0;
        long high = n / 2L + 1;

        while (high - low > 1) {
            long mid = (low + high) >>> 1;
            long sum = mid * (mid + 1);

            if (sum == 2L * n) {
                return (int) mid;
            }

            if (sum < 2L * n) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return (int) ((1 + high) * high == 2 * n ? high : low);
    }

}
