package leet.interview;

public class Solution08_03 {
    public static void main(String[] args) {

    }

    public static int findMagicIndex(int[] nums) {
        if (nums.length == 0)return -1;

        for (int i = 0; i < nums.length; ) {
            if (i == nums[i])return i;
            i = Math.max(i + 1, nums[i]);
        }
        return -1;
    }
}
