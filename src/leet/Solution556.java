package leet;

import java.util.Arrays;

public class Solution556 {
    public static void main(String[] args) {
        int n = 1999999999;
        System.out.println(nextGreaterElement2(n));
    }

    public static int nextGreaterElement(int n) {
        String val = String.valueOf(n);
        char[] nums = val.toCharArray();
        Arrays.sort(nums);
        if (nums[nums.length - 1] == val.charAt(0)) {
            return -1;
        }

        long mov = n + 1;
        while (mov <= Integer.MAX_VALUE) {
            String val_temp = String.valueOf(mov);
            if (val_temp.length() > val.length()) {
                break;
            }

            char[] nums_temp = val_temp.toCharArray();

            Arrays.sort(nums_temp);
            if (Arrays.equals(nums, nums_temp)) {
                return (int) mov;
            }
            mov++;
        }
        return -1;
    }

    /**
     * 首先我们观察到任意降序的序列，不会有更大的排列出现。
     * 比方说，下面数列就没有下一排列：9,6,3,1
     * <p>
     * 我们需要从右往左找到第一对连续的数字 a[i] 和 a[i+1]满足 a[i] < a[i+1]。
     * 到当前位置i，a[i]右边的数字没办法产生一个更大的排列，因为右边的数字是降序的。所以我们需要重新排布a[i]到最右边的数字来得到下一个排列。
     * <p>
     * 那么怎样排布能得到下一个更大的数字呢？
     * 我们想得到恰好大于当前数字的下一个排列，所以我们需要用恰好大于a[i]的数字去替换掉a[i]，比方说我们让这个数字为 a[j]。
     * 我们将 a[i] 和 a[j] 交换，我们现在在下标为i的地方得到了正确的数字，
     * 但当前的结果还不是一个正确的排列。我们需要使得从i开始到最右边数字剩下来的数字为升序排列，来得到它们中的最小排列。
     * 所以反转[i+1,end]的数组即可。
     *
     * @param n
     * @return
     */
    public static int nextGreaterElement2(int n) {
        String val = String.valueOf(n);
        char[] nums = val.toCharArray();

        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }

        if (i < 0) return -1;

        for (int j = nums.length - 1; j > i; j--) {
            if (nums[j] > nums[i]) {
                //swap nums[j-1],nums[i]
                char temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                break;
            }
        }
        StringBuilder str = new StringBuilder();
        for (int k = 0; k <= i; k++) {
            str.append(nums[k]);
        }
        int reverse_index = nums.length - 1;
        for (int k = i + 1; k < nums.length; k++) {
            str.append(nums[reverse_index]);
            reverse_index--;
        }

        try {
            return Integer.parseInt(str.toString());
        } catch (Exception e) {
            return -1;
        }
    }
}
