package leet.interview;

import java.util.Arrays;

public class Solution56 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,5};
        int[] x = singleNumbers3(nums);
        System.out.println(Arrays.toString(x));
    }

    /**
     * 如果我们可以把所有数字分成两组，使得：
     * 两个只出现一次的数字在不同的组中；
     * 相同的数字会被分到相同的组中。
     * 那么对两个组分别进行异或操作，即可得到答案的两个数字。这是解决这个问题的关键。
     * <p>
     * 算法:
     * 先对所有数字进行一次异或，得到两个出现一次的数字的异或值。
     * 在异或结果中找到任意为 1 的位。
     * 根据这一位对所有的数字进行分组。
     * 在每个组内进行异或操作，得到两个数字。
     *
     * @param nums
     * @return
     */
    public static int[] singleNumbers(int[] nums) {
        int[] ans = new int[2];

        int xorNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xorNum = xorNum ^ nums[i];
        }

        int h = xorNum & (-xorNum);//选取的是「不为 0 的最低位」, 因为n的正数的原码的最低位1 和-n的补码的最低位1相同；
        //exp ： 3(0, 011)  -3(1, 101) -> (3) & (-3) = 001
        int a = 0;
        int b = 0;
        for (int num : nums) {
            if ((num & h) == 0) {//若最低位不为1，放入第一组求异或
                a = a ^ num;
            } else {//否则放入第二组求异或
                b = b ^ num;
            }
        }
        ans[0] = a;
        ans[1] = b;
        return ans;
    }

    public static int[] singleNumbers2(int[] nums) {
        if (nums.length == 2) return nums;
        int val = nums[0];
        for (int i = 1; i < nums.length; i++) {
            val ^= nums[i];
        }
        int h = val & (-val);

        int group1 = 0;
        int group2 = 0;
        for (int num : nums) {
            if ((num & h) == 0) {
                group1 ^= num;
            } else {
                group2 ^= num;
            }
        }
        return new int[]{group1, group2};
    }

    public static int[] singleNumbers3(int[] nums) {
        int all = 0;
        for (int num : nums) {
            all ^= num;
        }
        int temp = all & (-all);
        int a = 0;
        int b = 0;
        for (int num : nums) {
            if ((num & temp) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }


}
