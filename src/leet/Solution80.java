package leet;

import java.util.Arrays;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定 nums = [1,1,1,2,2,3],
 *
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 *
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class Solution80 {
    public static void main(String[] args){
        int[] a = {2,2,2};
        System.out.println(removeDuplicates(a));
        System.out.println(Arrays.toString(a));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0)return 0;
        boolean flag = false;
        int last_num = nums[nums.length-1];
        int final_len = nums.length;
        int last_idx = nums.length-1;
        int one_num_start_idx = 0;
        int one_num_count = 0;
        int one_num = nums[0];

        for (int i = 0;i<nums.length;i++){
            if (nums[i] != one_num){
                if (one_num_count > 2){
                    flag = true;
                    int next_num_idx = i;
                    for (int j = one_num_start_idx+2;j <= last_idx-(one_num_count-2);j++){
                        nums[j] = nums[next_num_idx];
                        next_num_idx++;
                    }
                    last_idx = last_idx-(one_num_count-2);

                    for (int j = last_idx+1;j<nums.length;j++){
                        nums[j] = last_num-1;
                    }
                    final_len = final_len - (one_num_count-2);

                    one_num_start_idx = i-(one_num_count-2);
                    i = one_num_start_idx;
                    one_num_count = 1;
                    one_num = nums[one_num_start_idx];
                }
                else {
                    one_num_start_idx = i;
                    one_num = nums[i];
                    one_num_count = 1;
                }
            }
            else {
                one_num_count++;
            }
        }
        if (!flag){
            if (one_num_count > 2){
                for (int i = one_num_start_idx+2;i<nums.length;i++){
                    nums[i] = last_num-1;
                    final_len--;
                }
            }
        }
        return final_len;
    }
}
