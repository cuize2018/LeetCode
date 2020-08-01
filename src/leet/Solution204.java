package leet;

import java.util.Arrays;

public class Solution204 {
    public static void main(String[] args) {

    }

    /**
     * 首先从 2 开始，我们知道 2 是一个素数，那么 2 × 2 = 4, 3 × 2 = 6, 4 × 2 = 8... 都不可能是素数了。
     * 然后我们发现 3 也是素数，那么 3 × 2 = 6, 3 × 3 = 9, 3 × 4 = 12... 也都不可能是素数了。
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                // 比如 n = 25，i = 4 时算法会标记 4 × 2 = 8，4 × 3 = 12 等等数字，
                // 但是这两个数字已经被 i = 2 和 i = 3 的 2 × 4 和 3 × 4 标记了。
                // 我们可以稍微优化一下，让 j 从 i 的平方开始遍历，而不是从 2 * i 开始：
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            cnt += isPrime[i] ? 1 : 0;
        }
        return cnt;
    }
}
