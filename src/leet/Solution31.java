package leet;

import java.util.Arrays;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */

/**
 * 二、字典序算法
 * 字典序算法用来解决这样一个问题：给定其中一种排列，求基于字典序的下一种排列。
 *
 * 比如给定一种排列为 abc，则其基于字典序的下一种排列为 acb。
 *
 * 要求下一种排列既要比原排列大，又不能有第三种排列位于他俩之间。即下一种排列为大于原排列的最小排列。
 *
 * 以输入为 358764 为例，字典序算法的步骤：
 * 1、从原排列中，从右至左，找到第一个左邻小于右邻的字符，记左邻位置为 a。
 * 示例中 a=1，list[a] = 5。
 * 2、重新从右至左，找到第一个比 list[a] 大的字符，记为位置为 b。
 * 示例中 b=4，list[b] = 6。
 * 3、交换 a 和 b 两个位置的值。
 * 示例变为了 368754。
 * 4、将 a 后面的数，由小到大排列。
 * 示例变为了 364578。
 *
 * 算法结束，输出 364578。
 *
 * 注意：
 * 1、第1步中，如果找不到左邻小于右邻的数，则说明给定的排列已经是全排列的最后一个排列了，则直接返回全排列的第一个排列，即所有排列中最小的排列，形成一个循环。
 * 2、在第3步交换前，a 后面的数是按照从大到小进行排列（否则第1步中就可以找到左邻小于右邻的数了）。
 * 3、在交换之后，a 后面的数仍然是按照从大到小排列的，尽管 b 位置的值变成了 list[a]，但是由于 b 位置是第一个比 list[a] 大的，因此交换之后 list[a] 仍然比左邻小，比右邻大。
 * 4、既然 a 后面的数是从大到小排列的，那么第4步的排序，直接将 a 后面的数倒序即可。
 *
 * 算法的时间复杂度为 O(n) + O(n) + O(n) = O(n)。
 */
public class Solution31 {
    public static void main(String[] args){
        int[] nums = {3,2,1};
        nextPermutation(nums);
        for (int ele:nums){
            System.out.print(ele + ",");
        }
    }

    public static void nextPermutation(int[] nums) {
        if (nums.length <= 1)return;
        boolean flag = false; //标志位，是否可以找到左边比右边小的元素
        int smaller_index = 0;
        int bigger_index = 0;

        for (int i = nums.length-2;i>=0;i--){
            if (nums[i] < nums[i+1]){
                smaller_index = i;
                flag = true;
                break;
            }
        }
        if (!flag){
            //若不可以，则为字典序中最后一种排列，需返回第一种，即升序排列
            Arrays.sort(nums);
            return;
        }

        for (int i = nums.length-1;i>=0;i--){
            if (nums[i] > nums[smaller_index]){
                bigger_index = i;
                break;
            }
        }
        //交换
        int temp = nums[smaller_index];
        nums[smaller_index] = nums[bigger_index];
        nums[bigger_index] = temp;

        int[] tmp_arr = Arrays.copyOfRange(nums, smaller_index+1, nums.length);
        Arrays.sort(tmp_arr);
        System.arraycopy(tmp_arr, 0, nums, smaller_index+1, tmp_arr.length);
    }
}
