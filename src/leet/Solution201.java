package leet;

public class Solution201 {
    public static void main(String[] args) {
        int m = 5;
        int n = 7;
        System.out.println(rangeBitwiseAnd2(m, n));
    }

    public static int rangeBitwiseAnd(int m, int n) {
        int res = 0;
        for (int i = 0; i <= 31; i++) {
            int mask = Integer.MAX_VALUE << i;

            if ((mask & m) == (mask & n)) {
                res = mask & m;
                break;
            }
        }
        return res;
    }

    /**
     * 对所有数字执行按位与运算的结果是所有对应二进制字符串的公共前缀再用零补上后面的剩余位。
     * 一定存在连续的两个数x和x+1，满足 x 的第 i+1 位为 0，后面全为 1，x+1 的第 i+1 位为 1，后面全为 0
     * 其实假若m和n的前x位完全一样的话，那么中间的数即使变化也是在后面剩下那几位变化，
     * 也就是[m, n]区间的所有数前x位都相同，那么我们只要把m和n的类似公共前缀位找出来即为答案。
     */
    public static int rangeBitwiseAnd2(int m, int n) {
        int i = 0;
        while (m != n) {
            m = m >> 1;
            n = n >> 1;
            i++;
        }

        return m << i;
    }

    public static int rangeBitwiseAnd3(int m, int n) {
        if (m == 0) return 0;
        int i = 0;
        while (m != n) {
            m = m >>> 1;
            n = n >>> 1;
            i++;
        }
        return m << i;
    }

}
