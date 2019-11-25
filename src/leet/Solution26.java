package leet;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定数组 nums = [1,1,2],
 *
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class Solution26 {
    public static void main(String[] args){
        int[] a = {1,2,2,2,2};
        System.out.println("len:" + removeDuplicates2(a));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1)return nums.length;
        int m = 0;
        int len = nums.length;
        int last_index = nums.length-1;

        while (m < nums.length-1) {
            if (nums[m] > nums[m+1]){
                break;
            }

            int num = nums[m];
            for (int i = m + 1; i < nums.length; i++) {
                if (nums[i] != num && nums[i] >= num) {
                    int dis = i - m - 1;
                    len = len - dis;

                    int q = i;
                    for (int j = m + 1; j < len; j++) {
                        nums[j] = nums[q];
                        q++;
                    }

                    for (int j = 0;j<dis;j++){
                        nums[last_index-j]--;
                    }
                    last_index -= dis;
                    break;
                }
            }
            if (nums[m] == nums[m+1]){
                for (int i = m+1;i<nums.length;i++){
                    if (nums[i] == nums[m]){
                        nums[i]--;
                        len--;
                    }
                    else {
                        break;
                    }
                }
                break;
            }
            m++;
        }

        for (int i = 0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
        return len;
    }

    public static int removeDuplicates2(int[] nums) {
        if (nums.length <= 1)return nums.length;
        int i = 0;
        for (int j = 1;j<nums.length;j++){
            if (nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;

    }
}
