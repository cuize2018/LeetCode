package leet;

import com.sun.org.apache.regexp.internal.RE;
import org.omg.CORBA.INTERNAL;

import javax.print.attribute.standard.NumberUp;
import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 */
public class Solution18 {
    public static void main(String[] args){
        int[] nums = {0,-1,-3,5,-5};
        int target = 1;
        System.out.println(fourSum(nums, target));
    }

    public static  List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> out = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();

        if (nums.length < 4)return out;
        if (nums.length == 4){
            if (nums[0] + nums[1] + nums[2] + nums[3] == target){
                out.add(Arrays.asList(nums[0], nums[1], nums[2], nums[3]));
            }
            return out;
        }
        Arrays.sort(nums);

        for (int i = 0;i<nums.length-3;i++){
            int second = i+1;
            for (int j = second;j<nums.length;j++){
                int left = j+1;
                int right = nums.length-1;

                while (left < right){
                    int val = nums[i] + nums[j] + nums[left] + nums[right];
                    if (val > target){
                        right--;
                    }
                    else if (val < target) {
                        left++;
                    }
                    else {
                        List<Integer> l = new ArrayList<Integer>(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        left++;
                        right--;
                        if (!set.contains(l)){
                            set.add(l);
                            out.add(l);
                        }
                    }
                }
            }

        }
        return out;
    }
}
