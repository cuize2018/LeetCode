package leet;

import java.util.Arrays;

public class Solution283 {
    public static void main(String[] args) {
        int[] nums = {0,0};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {
        if (nums.length <= 1)return;

        int idx = nums.length -1;
        int lastNoneZero = nums.length-1;

        while (idx >= 0) {
            if (nums[idx] == 0 && idx < nums.length-1) {
                System.arraycopy(nums, idx+1, nums, idx, lastNoneZero - idx);

                lastNoneZero--;
                nums[lastNoneZero+1] = 0;
            }
            idx--;
        }
    }

    /**
     * 慢指针（j）之前的所有元素都是非零的。
     * 当前指针i和慢速指针j之间的所有元素都是零。
     * @param nums
     */
    public static void moveZeroes2(int[] nums) {
        if (nums.length <= 1)return;

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){//当前元素!=0，就把其交换到左边，等于0的交换到右边
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                j++;
            }
        }

    }
}
