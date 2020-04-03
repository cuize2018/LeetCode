package leet;

public class Solution172 {
    public static void main(String[] args) {

    }

    /**
     * 对于一个数的阶乘，就如之前分析的，5 的因子一定是每隔 5 个数出现一次，也就是下边的样子。
     * n! = 1 * 2 * 3 * 4 * (1 * 5) * ... * (2 * 5) * ... * (3 * 5) *... * n
     * 因为每隔 5 个数出现一个 5，所以计算出现了多少个 5，我们只需要用 n/5 就可以算出来。
     * <p>
     * 但还没有结束，继续分析。
     * ... * (1 * 5) * ... * (1 * 5 * 5) * ... * (2 * 5 * 5) * ... * (3 * 5 * 5) * ... * n
     * 每隔 25 个数字，出现的是两个 5，所以除了每隔 5 个数算作一个 5，每隔 25 个数，还需要多算一个 5。
     * 也就是我们需要再加上 n / 25 个 5。
     * <p>
     * 同理我们还会发现每隔 5 * 5 * 5 = 125 个数字，会出现 3 个 5，所以我们还需要再加上 n / 125 。
     * 综上，规律就是每隔 5 个数，出现一个 5，每隔 25 个数，出现 2 个 5，每隔 125 个数，出现 3 个 5... 以此类推。
     * 最终 5 的个数就是 n / 5 + n / 25 + n / 125 ...
     * <p>
     * 写程序的话，如果直接按照上边的式子计算，分母可能会造成溢出。所以算 n / 25 的时候，我们先把 n 更新，n = n / 5，然后再计算 n / 5 即可。后边的同理。
     *
     * @param n
     * @return
     */
    public static int trailingZeroes(int n) {
        int count = 0;
        long k = 5;
        while (n / k != 0) {
            count += n / k;
            k *= 5;
        }
        return count;
    }

    public static int trailingZeroes2(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }

}
