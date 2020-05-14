package leet.interview;

public class Solution53_II {
    public static void main(String[] args) {

    }

    public static int missingNumber(int[] nums) {
        if (nums.length == 1) return nums[0] == 0 ? 1 : 0;
        int n = nums.length + 1;

        if (nums[0] != 0) return 0;
        if (nums[nums.length - 1] != n - 1) return n - 1;

        int left = 0;
        int right = nums.length - 1;

        while (left < right - 1) {
            int mid = (left + right) >>> 1;
            if (mid - left == nums[mid] - nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[left] + 1;
    }
}
