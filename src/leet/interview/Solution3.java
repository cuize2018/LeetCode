package leet.interview;

import java.util.HashSet;
import java.util.Set;

public class Solution3 {
    public static void main(String[] args) {

    }

    /**
     * 方法3: 鸽巢原理，因为出现的元素值 < nums.size();
     * 所以我们可以将见到的元素放到索引的位置，如果交换时，发现索引处已存在该元素，则重复
     * 时间O(N) 空间O(1)
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i){
                if (nums[nums[i]] == nums[i])return nums[i];

                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        return -1;
    }

    public static int findRepeatNumber3(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i){
                if (nums[nums[i]] == nums[i]){
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
