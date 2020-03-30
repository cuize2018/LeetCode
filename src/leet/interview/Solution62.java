package leet.interview;

import java.util.ArrayList;
import java.util.List;

public class Solution62 {
    public static void main(String[] args) {

    }

    /**
     * 模拟法
     * 可以分析下纯暴力的做法，每次找到删除的那个数字，需要 O(m)的时间复杂度，然后删除了 n-1次。但实际上我们可以直接找到下一个要删除的位置的！
     * 假设当前删除的位置是 idx ,下一个删除的数字的位置是 idx+m 。
     * 但是，由于把当前位置的数字删除了，后面的数字会前移一位，所以实际的下一个位置是 idx + m - 1。
     * 由于数到末尾会从头继续数，所以最后取模一下，就是 (idx + m - 1) mod n。
     *
     * @param n
     * @param m
     * @return
     */
    public static int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }

        return list.get(0);
    }

    /**
     * 第一轮是 [0, 1, 2, 3, 4] ，所以是 [0, 1, 2, 3, 4] 这个数组的多个复制。这一轮 2 删除了。
     *
     * 第二轮开始时，从 3 开始，所以是 [3, 4, 0, 1] 这个数组的多个复制。这一轮 0 删除了。
     *
     * 第三轮开始时，从 1 开始，所以是 [1, 3, 4] 这个数组的多个复制。这一轮 4 删除了。
     *
     * 第四轮开始时，还是从 1 开始，所以是 [1, 3] 这个数组的多个复制。这一轮 1 删除了。
     *
     * 最后剩下的数字是 3.
     *
     * 然后我们从最后剩下的 3 倒着看，我们可以反向推出这个数字在之前每个轮次的位置。
     *
     * 最后剩下的 3 的下标是 0。
     *
     * 第四轮反推，补上 m 个位置，然后模上当时的数组大小 2，位置是(0 + 3) % 2 = 1。
     *
     * 第三轮反推，补上 m 个位置，然后模上当时的数组大小 3，位置是(1 + 3) % 3 = 1。
     *
     * 第二轮反推，补上 m 个位置，然后模上当时的数组大小 4，位置是(1 + 3) % 4 = 0。
     *
     * 第三轮反推，补上 m 个位置，然后模上当时的数组大小 5，位置是(0 + 3) % 5 = 3。
     *
     * 所以最终剩下的数字的下标就是3。因为数组是从0开始的，所以最终的答案就是3。
     *
     * 总结一下反推的过程，就是 (当前index + m) % 上一轮剩余数字的个数。
     * @param n
     * @param m
     * @return
     */
    public static int lastRemaining2(int n, int m) {
        //i表示上一轮的数组大小,当最后只剩一个元素时的上一轮数组大小为2
        int idx = 0;
        for (int i = 2; i <= n; i++) {
            idx = (idx + m) % i;
        }
        return idx;
    }


}
