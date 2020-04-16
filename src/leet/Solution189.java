package leet;

import java.util.Arrays;

public class Solution189 {
    public static void main(String[] args) {
        int[] nums = {-1, 2};
        int k = 3;
        rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 如果我们直接把每一个数字放到它最后的位置，但这样的后果是遗失原来的元素。
     * 因此，我们需要把被替换的数字保存在变量 temp 里面。然后，我们将被替换数字（temp）放到它正确的位置，并继续这个过程 n 次， n 是数组的长度。
     * 这是因为我们需要将数组里所有的元素都移动。
     *
     * 这种情况下，我们会发现在没有遍历所有数字的情况下回到出发数字。此时，我们应该从下一个数字开始再重复相同的过程。
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        if (k == 0) return;
        int count = 0;
        int n = nums.length;
        k = k % n;//因为如果 k 大于 n ，移动 k 次实际上相当于移动 k%n 次）

        for (int start = 0; count < n; start++) {
            int curr = start;
            int prev = nums[curr];

            do {
                int next = (curr + k) % n;

                int temp = nums[next];
                nums[next] = prev;
                prev = temp;

                curr = next;
                count++;
            } while (curr != start);
        }
    }

    /**
     * 这个方法基于这个事实：当我们旋转数组 k 次， k%n 个尾部元素会被移动到头部，剩下的元素会被向后移动。
     * @param nums
     * @param k
     */
    public static void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;end--;
        }
    }
}
