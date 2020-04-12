package leet;

import java.util.Arrays;

public class Solution581 {
    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(findUnsortedSubarray3(nums));
    }

    /**
     * 另一个简单的想法是：我们将数组 numsnums 进行排序，记为nums_sorted 。
     * 然后我们比较 nums 和 nums_sorted 的元素来决定最左边和最右边不匹配的元素。
     * 它们之间的子数组就是要求的最短无序子数组。
     *
     * @param nums
     * @return
     */
    public static int findUnsortedSubarray(int[] nums) {
        int[] numsTemp = nums.clone();
        Arrays.sort(numsTemp);

        int start = nums.length;
        int end = 0;
        for (int i = 0; i < numsTemp.length; i++) {
            if (nums[i] != numsTemp[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        if (end - start >= 0) return end - start + 1;
        return 0;
    }

    /**
     * 1、先进行排序，让数组升序；
     * 2、从前向后，寻找第一个与之前序列不同的元素所在位置begin
     * 3、从后向前遍历寻找end
     * 4、相减返回begin-end+1。如果已经有序就返回0
     *
     * @param nums
     * @return
     */
    public static int findUnsortedSubarray3(int[] nums) {
        int[] numsTemp = nums.clone();
        Arrays.sort(numsTemp);

        int start = nums.length;
        int end = 0;
        for (int i = 0; i < numsTemp.length; i++) {
            if (nums[i] != numsTemp[i]) {
                start = i;
                break;
            }
        }

        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] != numsTemp[j]) {
                end = j;
                break;
            }
        }

        if (end - start == 0) return 0;
        return end - start + 1;
    }

    /**
     * 同时从前往后和从后往前遍历，分别得到要排序数组的右边界和左边界；
     * <p>
     * 寻找右边界：
     * 从前往后遍历的过程中，用max记录遍历过的最大值，
     * 如果max大于当前的nums[i]，说明nums[i]的位置不正确，属于需要排序的数组，因此将右边界更新为i，然后更新max；
     * 这样最终可以找到需要排序的数组的右边界，右边界之后的元素都大于max；
     * <p>
     * 寻找左边界：
     * 从后往前遍历的过程中，用min记录遍历过的最小值，
     * 如果min小于当前的nums[j]，说明nums[j]的位置不正确，应该属于需要排序的数组，因此将左边界更新为j，然后更新min；
     * 这样最终可以找到需要排序的数组的左边界，左边界之前的元素都小于min；
     * <p>
     * <p>
     * 如果max大于nums[i]，就将右边界更新为i
     * 如果min小于nums[j]，就将左边界更新为j
     *
     * @param nums
     * @return
     */
    public static int findUnsortedSubarray2(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = -1;//如果array是已经sorted的话，begin和end还是初始值，确保返回的数值是0。

        //从左往右应该是升序
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < max) {
                right = i;
            } else {
                max = nums[i];
            }
        }

        //从右往左应该是降序
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > min) {
                left = i;
            } else {
                min = nums[i];
            }
        }

        return right - left + 1;
    }
}
