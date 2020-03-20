package leet;

import java.util.Arrays;

public class Solution280 {
    public static void main(String[] args) {
        int[] nums = {3, 5, 2, 1, 6,4};
        wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 先使得数组正向有序
     * 当数组元素个数为偶数的时候，最后一个元素一定要比倒数第二个元素大，所以可以不移动最后一个元素，从倒数第二个开始
     * 当数组元素为奇数的时候，最后一个元素一定比倒数第二个元素小，所以可以从最后一个元素开始
     * 每次移动步长为2， 交换当前元素和前一个元素
     * @param nums
     */
    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        int i = nums.length - 1;
        if (nums.length % 2 == 0) {
            i--;
        }

        for (; i >= 2; i -= 2) {
            swap(nums, i, i - 1);
        }
    }

    public void wiggleSort2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            swap(nums, i, i + 1);
        }
    }

    /**
     * 当我们遍历整个数组，比较当前元素与下一个元素。若顺序不正确，则交换之。
     * @param nums
     */
    public void wiggleSort3(int[] nums) {
        boolean less =true;
        for (int i = 0; i <= nums.length-2; i++) {
            if (less){
                if (nums[i] > nums[i+1]){
                    swap(nums, i,i+1);
                }
            }
            else {
                if (nums[i] < nums[i+1]){
                    swap(nums, i, i+1);
                }
            }
            less = !less;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
