package leet;

public class Solution793 {
    public static void main(String[] args) {
        int K = 5;
        System.out.println(preimageSizeFZF(K));
    }

    /**
     * zeta(x) 是一个单调递增函数，因此可以使用二分查找求解。
     * 使用二分查找找出满足 zeta(x) = K 的最大 x 和最小 x。
     * 由于一定存在 zeta(5a-1) < zeta(5a) = zeta(5a+1) = zeta(5a+2) = zeta(5a+3) = zeta(5a+4) < zeta(5a+5)，
     * 即如果存在某个 x 使得 zeta(x) = K，那么一定存在连续 5 个数的阶乘末尾零的个数都为 K；
     * 如果不存在这样的 x，那么阶乘末尾零的个数为 K 的数字只有 0 个。
     *
     * @param K
     * @return
     */
    public static int preimageSizeFZF(int K) {
        long low = 0;//x >= 0
        long high = 10 * K + 1;//x <= 10*K+1是个很宽泛的的上界，+1是为了考虑K=0的情况

        while (low < high) {
            long mid = (low + high) >>> 1;
            int nums = trailingZeroes(mid);//计算出mid的阶乘的0的个数
            if (nums == K) return 5;
            else if (nums < K) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return 0;
    }

    public static int trailingZeroes(long n) {
        if (n == 0) return 0;
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }
}
