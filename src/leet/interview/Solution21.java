package leet.interview;

import java.util.Arrays;
import java.util.Comparator;

public class Solution21 {
    public static void main(String[] args) {

    }

    public int[] exchange(int[] nums) {
        nums = Arrays.stream(nums).boxed().sorted((a, b) -> {
            if ((a % 2 == 1) && (b % 2 == 0)) return -1;
            else if ((a % 2 == 0) && (b % 2 == 1)) return 1;
            else return Integer.compare(a, b);
        }).mapToInt(i -> i).toArray();

        return nums;
    }

    /**
     * 快排的思路
     * @param nums
     * @return
     */
    public static int[] exchange2(int[] nums) {
        int left = 0;
        int right = nums.length-1;

        while (left < right) {
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return nums;
    }

}
