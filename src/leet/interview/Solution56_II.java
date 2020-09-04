package leet.interview;

public class Solution56_II {
    public static void main(String[] args) {
        int[] nums = {3, 4, 3, 3};
        System.out.println(singleNumber3(nums));
    }

    /**
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     *
     * 如下图所示，考虑数字的二进制形式，对于出现三次的数字，各二进制位 出现的次数都是 3 的倍数。
     * 因此，统计所有数字的各二进制位中 1 的出现次数，并对 3 求余，结果则为只出现一次的数字。
     * <p>
     * 使用 与运算 ，可获取二进制数字 num 的最右一位 n1: num & 1
     * 配合 无符号右移操作 ，可获取 num 所有位的值(n1 ~ n32): num = num >>> 1
     * <p>
     * 建立一个长度为 32 的数组 counts ，通过以上方法可记录所有数字的各二进制位的 1 的出现次数。
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int[] count = new int[32];
        for (int num : nums) {
            for (int j = 0; j < 32; j++) {
                int bit = num & 1;
                count[j] += bit;
                num = num >>> 1;
            }
        }
        //count数组[0,31]保存着最终结果的1-32位
        //利用 左移操作 和 或运算 ，可将 counts 数组中各二进位的值恢复到数字 res 上
        int res = 0;
        for (int j = 0; j < count.length; j++) {
            res <<= 1;
            res = res | count[31 - j] % 3;
        }
        return res;
    }

    public static int singleNumber2(int[] nums) {
        int[] count = new int[32];

        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                int bit = num & 1;
                count[i] += bit;
                num = num >>> 1;
            }
        }
        int res = 0;
        int v = 1;
        for (int i = 0; i < 32; i++) {
            count[i] = count[i] % 3;
            if (count[i] > 0) {
                res += v;
            }
            v = v << 1;
        }
        return res;
    }

    public static int singleNumber3(int[] nums) {
        int[] bits = new int[32];
        for (int num : nums) {
            int i = 31;
            while (num != 0) {
                int bit = num & 1;
                num = num >> 1;
                bits[i--] += bit;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            bits[i] %= 3;
            res = res * 2 + bits[i];
        }
        return res;
    }
}
