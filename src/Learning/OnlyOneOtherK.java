package Learning;

public class OnlyOneOtherK {
    public static void main(String[] args) {
        int[] nums = {3, 4, 3, 3};
        System.out.println(singleNumber(nums));
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
