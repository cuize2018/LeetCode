package leet.interview;

public class Solution39 {
    public static void main(String[] args) {

    }

    /**
     * 摩尔投票法
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        if (nums.length == 1) return nums[0];

        int count = 0;
        int res = -1;

        for (int num : nums) {
            if (count == 0) res = num;
            count += (num == res) ? 1 : -1;
        }
        return res;
    }
}
